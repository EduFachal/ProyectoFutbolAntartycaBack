package com.antartyca.proyecto.servicesImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.antartyca.proyecto.model.CiudadModel;
import com.antartyca.proyecto.repository.CiudadRepository;
import com.antartyca.proyecto.services.CiudadService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Service
public class CiudadServiceImp implements CiudadService{

	@Autowired
	CiudadRepository ciudadRepo;
	
	@Override
	public CiudadModel saveCity(CiudadModel ciudad) {
		CiudadModel result = new CiudadModel();
		try {
			result = ciudadRepo.save(ciudad);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<CiudadModel> getAllCities() {
		List<CiudadModel> result = new ArrayList<>();
		try {
			result = ciudadRepo.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public CiudadModel getById(Integer id) {
		CiudadModel result = new CiudadModel();
		try {
			result = ciudadRepo.findById(id).get();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<CiudadModel> getByFilter(CiudadModel filter) {
		List<CiudadModel> result = new ArrayList<>();
		try {
			Example<CiudadModel> exmple = Example.of(filter);
			result = ciudadRepo.findAll(exmple);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/* 	Se recoge a través de un JSON la información de una ciudad, primero se comprueba
 	si existe la ciudad o no.
	En caso de existir buscará el torneo cuya id coincida y modificara los campos de nombre, descripcion,...
	En caso contrario mostrará por la consola un mensaje de equipo no encontrado*/
	
	@Override
	public CiudadModel updateCity(CiudadModel ciudad) {
		CiudadModel result = new CiudadModel();
		try {
			if(ciudadRepo.existsById(ciudad.getCod_ciudad())) {
				result = ciudadRepo.findById(ciudad.getCod_ciudad()).get();
				if(ciudad.getNombre()!=null) {
					result.setNombre(ciudad.getNombre());
				}
				result = ciudadRepo.save(result);
			}else {
				System.out.println("No existe la ciudad introducida");
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
				ciudadRepo.deleteById(id);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
