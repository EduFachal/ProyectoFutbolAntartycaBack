package com.antartyca.proyecto.servicesImp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.antartyca.proyecto.model.EquipoModel;
import com.antartyca.proyecto.model.JugadorModel;
import com.antartyca.proyecto.repository.EquipoRepository;
import com.antartyca.proyecto.services.EquipoService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Service
public class EquipoServiceImp implements EquipoService{
	
	@Autowired
	EquipoRepository equipoRepo;

	@Override
	public EquipoModel saveTeam(EquipoModel equipo) {
		EquipoModel result = new EquipoModel();
		try {
			equipo.setCod_equipo(equipoRepo.getNextSeriesId());
			equipo.setJugadores(new ArrayList<JugadorModel>());
			result = equipoRepo.save(equipo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<EquipoModel> getAllTeams() {
		List<EquipoModel> result = new ArrayList<>();
		try {
			result = equipoRepo.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public EquipoModel getById(Integer id) {
		EquipoModel result = new EquipoModel();
		try {
			result = equipoRepo.findById(id).get();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<EquipoModel> getByFilter(EquipoModel filter) {
		List<EquipoModel> result = new ArrayList<>();
		try {
			Example<EquipoModel> exmple = Example.of(filter);
			result = equipoRepo.findAll(exmple);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	/* 	Se recoge a través de un JSON la información de un equipo, primero se comprueba
	 	si existe el equipo o no.
		En caso de existir buscará el equipo cuya id coincida y modificara los campos de nombre, descripcion,...
		En caso contrario mostrará por la consola un mensaje de equipo no encontrado 
		Además en caso que la lista de jugadores o torneos se vaya a modificar, se comprueba previamente si tiene datos metidos*/		
	@Override
	public EquipoModel updateTeam(EquipoModel equipo) {
		EquipoModel result = new EquipoModel();
		try {
			if(equipoRepo.existsById(equipo.getCod_equipo())) {
				result = equipoRepo.findById(equipo.getCod_equipo()).get();
				if(!equipo.getNombre_equipo().equals(result.getNombre_equipo())) {
					result.setNombre_equipo(equipo.getNombre_equipo());
				}
				if(equipo.getDireccion()!= null) {
					result.setDireccion(equipo.getDireccion());
				}
				if(equipo.getFecha_fundacion()!= null) {
					result.setFecha_fundacion(equipo.getFecha_fundacion());
				}
				if(equipo.getJugadores().size()>0) {
					result.setJugadores(equipo.getJugadores());
				}
				if(equipo.getLst_torneos().size()>0) {
					result.setLst_torneos(equipo.getLst_torneos());
				}
				result = equipoRepo.save(result);
			}else {
				System.out.println("No existe el equipo buscado");
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
				equipoRepo.deleteById(id);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}


}
