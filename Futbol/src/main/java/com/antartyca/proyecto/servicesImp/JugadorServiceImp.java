package com.antartyca.proyecto.servicesImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.antartyca.proyecto.model.JugadorModel;
import com.antartyca.proyecto.model.JugadorSearchRequestModel;
import com.antartyca.proyecto.repository.JugadorRepository;
import com.antartyca.proyecto.services.JugadorService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Service
public class JugadorServiceImp implements JugadorService{

	@Autowired
	JugadorRepository jugadorRepo;
	
	@Autowired
	EntityManager em; // Creamos el entity manager, necesario para las consultas CriteriaQuer

	@Override
	public JugadorModel savePlayer(JugadorModel jugador) {
		JugadorModel result = new JugadorModel();
		try {
			jugador.setCod_jugador(jugadorRepo.getNextSeriesId());
			result = jugadorRepo.save(jugador);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<JugadorModel> getAllPlayers() {
		List<JugadorModel> result = new ArrayList<>();
		try {
			result = jugadorRepo.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public JugadorModel getById(Integer id) {
		JugadorModel result = new JugadorModel();
		try {
			result = jugadorRepo.findById(id).get();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<JugadorModel> getByFilter(JugadorModel filter) {
		List<JugadorModel> result = new ArrayList<>();
		try {
			Example<JugadorModel> exmple = Example.of(filter);
			result = jugadorRepo.findAll(exmple);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/* 	Se recoge a través de un JSON la información de un jugador, primero se comprueba
 	si existe el jugador o no.
	En caso de existir buscará el equipo cuya id coincida y modificara los campos de nombre, descripcion,...
	En caso contrario mostrará por la consola un mensaje de jugador no encontrado 
	Además en caso de que se modificara un equipo, se comprobaria previamente si fuera on nulo*/
	
	@Override
	public JugadorModel updatePlayer(JugadorModel jugador) {
		JugadorModel result = new JugadorModel();
		try {
			if(jugadorRepo.existsById(jugador.getCod_jugador())) {
				result = jugadorRepo.findById(jugador.getCod_jugador()).get();
				if(jugador.getNombre()!= null) {
					result.setNombre(jugador.getNombre());
				}
				if(jugador.getPuesto()!= null) {
					result.setPuesto(jugador.getPuesto());
				}
				if(jugador.getTelefono()!= null) {
					result.setTelefono(jugador.getTelefono());
				}
				if(jugador.getEquipo()!=null) {
					result.setEquipo(jugador.getEquipo());
				}
				if(jugador.getGoles()!=0) {
					result.setGoles(jugador.getGoles());
				}
				if(jugador.getAltura()!=0) {
					result.setAltura(jugador.getAltura());
				}
				if(jugador.getTarjetas()!=0) {
					result.setTarjetas(jugador.getTarjetas());
				}
				result.setActivo(jugador.isActivo());
				result = jugadorRepo.save(result);
			}else {
				System.out.println("No existe el jugador buscado");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	@Override
	public void deleteById(Integer id) {
		try {
			if(id != null) {
				jugadorRepo.deleteById(id);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	/**
	 * Metodo para buscar jugadores por distintos filtros, por ejemplo el codigo, el
	 * nombre, la altura, posicion..
	 */
	public List<JugadorModel> searchPlayer(JugadorSearchRequestModel jugadorSearchRequestModel) {

		// Creamos el builder
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		// Creamos la query
		CriteriaQuery<JugadorModel> criteriaQuery = criteriaBuilder.createQuery(JugadorModel.class);

		// Creamos el root
		Root<JugadorModel> root = criteriaQuery.from(JugadorModel.class);

		// Creamos y obtenemos el valor de las variables
		int cod_jugador = jugadorSearchRequestModel.getCod_jugador();
		String nombre = jugadorSearchRequestModel.getNombre();
		String puesto = jugadorSearchRequestModel.getPuesto();
		String telefono = jugadorSearchRequestModel.getTelefono();
		int goles = jugadorSearchRequestModel.getGoles();
		int altura = jugadorSearchRequestModel.getAltura();
		int tarjetas = jugadorSearchRequestModel.getTarjetas();
		boolean activo = jugadorSearchRequestModel.isActivo();

		// Creamos una lista de predicados para guardar ahi el filtrado
		List<Predicate> searchCriterias = new ArrayList<>();

		if ((cod_jugador > 0)) {

			searchCriterias.add(criteriaBuilder.equal(root.get("cod_jugador"), cod_jugador));
		}

		if ((nombre != "") && (nombre != null)) {

			searchCriterias.add(criteriaBuilder.equal(root.get("nombre"), nombre));
		}
		if ((puesto != "") && (puesto != null)) {

			searchCriterias.add(criteriaBuilder.equal(root.get("puesto"), puesto));
		}

		if ((telefono != "") && (telefono != null)) {

			searchCriterias.add(criteriaBuilder.equal(root.get("telefono"), telefono));
		}

		if ((goles != 0)) {

			searchCriterias.add(criteriaBuilder.equal(root.get("goles"), goles));
		}
		if ((altura != 0)) {

			searchCriterias.add(criteriaBuilder.equal(root.get("altura"), altura));
		}
		if (activo) {

			searchCriterias.add(criteriaBuilder.equal(root.get("activo"), activo));
		} else {

			jugadorSearchRequestModel.setActivo(false);

			searchCriterias.add(criteriaBuilder.equal(root.get("activo"), activo));
		}

		criteriaQuery.select(root)
				.where(criteriaBuilder.and(searchCriterias.toArray(new Predicate[searchCriterias.size()])));
		return em.createQuery(criteriaQuery).getResultList();

	}

	public List<JugadorModel> buscarPorPuestoYGoles(String puesto, int goles) {

		// Creamos el builder
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		// Creamos la query
		CriteriaQuery<JugadorModel> criteriaQuery = criteriaBuilder.createQuery(JugadorModel.class);

		// Creamos el root
		Root<JugadorModel> root = criteriaQuery.from(JugadorModel.class);
		
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("puesto"), puesto), criteriaBuilder.equal(root.get("goles"),(Integer) goles));
		
		TypedQuery<JugadorModel> q = em.createQuery(criteriaQuery);
		return q.getResultList();
	}


	/*
	 * public void selectAllPlayers() {
	 * 
	 * EntityManager em = emf.createEntityManager(); em.getTransaction().begin();
	 * 
	 * Query q =
	 * em.createNativeQuery("SELECT a.nombre, a.puesto from JugadorModel a");
	 * List<Object[]> jugadores = q.getResultList();
	 * 
	 * for (Object[] a : jugadores) {
	 * 
	 * Log.info("Jugador " + a[0] + " " + a[1]);
	 * 
	 * }
	 * 
	 * em.getTransaction().commit(); em.close(); }
	 */

	/*
	@Override
	public List<JugadorModel> busquedaPorGoles(int goles) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("JPA_PU");
		EntityManager entitymanager = emfactory.createEntityManager();
		// Between
		Query query = entitymanager
				.createQuery("Select j " + "from JugadorModel e " + "where j.goles " + "Between 5 and 50");
		List<JugadorModel> jugadores = (List<JugadorModel>) query.getResultList();
		return jugadores;
	}
	*/
	
	public List<JugadorModel> busquedaPorGoles(int goles) {

        TypedQuery<JugadorModel> query = em.createQuery("SELECT j from JugadorModel j where j.goles >= '" +  goles + "' ", JugadorModel.class);
           List<JugadorModel> golesJugadores= query.getResultList();
           return golesJugadores;
   }

	public List<JugadorModel> buscarJugadoresNombre(String nombre) {
        TypedQuery<JugadorModel> query = em.createQuery("SELECT j from JugadorModel j where j.nombre like '" +  nombre + "%' ", JugadorModel.class);
        return query.getResultList();
	}
	
	// Consulta CriteriaQuery
    public List<JugadorModel> buscarEntreFechas(Date fechaIn, Date fechaFin) {
    	System.out.println(fechaIn);
    	System.out.println(fechaFin);
        // Creamos el builder
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        // Creamos la query
        CriteriaQuery<JugadorModel> criteriaQuery = criteriaBuilder.createQuery(JugadorModel.class);

        // Creamos el root
        Root<JugadorModel> root = criteriaQuery.from(JugadorModel.class);
        
        criteriaQuery.select(root).where(criteriaBuilder.between(root.get("fecha_nacimiento"), fechaIn, fechaFin));

        TypedQuery<JugadorModel> q = em.createQuery(criteriaQuery);
        
        return q.getResultList();
    }
}
