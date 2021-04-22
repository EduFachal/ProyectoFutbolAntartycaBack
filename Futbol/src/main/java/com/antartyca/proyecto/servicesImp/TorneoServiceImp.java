package com.antartyca.proyecto.servicesImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.antartyca.proyecto.model.TorneoModel;
import com.antartyca.proyecto.repository.TorneoRepository;
import com.antartyca.proyecto.services.TorneoService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Service
public class TorneoServiceImp implements TorneoService{

	@Autowired
	TorneoRepository torneoRepo;

	@Override
	public TorneoModel saveTournament(TorneoModel torneo) {
		TorneoModel result = new TorneoModel();
		try {
			result = torneoRepo.save(torneo);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<TorneoModel> getAllTournaments() {
		List<TorneoModel> result = new ArrayList<>();
		try {
			result = torneoRepo.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public TorneoModel getById(Integer id) {
		TorneoModel result = new TorneoModel();
		try {
			result = torneoRepo.findById(id).get();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<TorneoModel> getByFilter(TorneoModel filter) {
		List<TorneoModel> result = new ArrayList<>();
		try {
			Example<TorneoModel> exmple = Example.of(filter);
			result = torneoRepo.findAll(exmple);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/* 	Se recoge a través de un JSON la información de un torneo, primero se comprueba
 	si existe el torneo o no.
	En caso de existir buscará el torneo cuya id coincida y modificara los campos de nombre, descripcion,...
	En caso contrario mostrará por la consola un mensaje de equipo no encontrado 
	Además en caso que la lista de equipos se vaya a modificar, se comprueba previamente si tiene datos metidos
	y se comprobara si la ciudad no es nula*/
	
	@Override
	public TorneoModel updateTournament(TorneoModel torneo) {
		TorneoModel result = new TorneoModel();
		try {
			if(torneoRepo.existsById(torneo.getCod_torneo())) {
				result = torneoRepo.findById(torneo.getCod_torneo()).get();
				if(torneo.getNombre()!=null) {
					result.setNombre(torneo.getNombre());
					}
				if(torneo.getDescripcion()!=null) {
					result.setDescripcion(torneo.getDescripcion());
				}
				if(torneo.getCiudad()!=null) {
					result.setCiudad(torneo.getCiudad());
				}
				if(torneo.getLst_equipos().size()>0) {
					result.setLst_equipos(torneo.getLst_equipos());
				}
				result = torneoRepo.save(result);
			}else {
				System.out.println("No existe el torneo a modificar");
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
				torneoRepo.deleteById(id);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
}
