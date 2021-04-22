package com.antartyca.proyecto.servicesImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.antartyca.proyecto.model.FederacionModel;
import com.antartyca.proyecto.repository.FederacionRepository;
import com.antartyca.proyecto.services.FederacionService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Service
public class FederacionServiceImp implements FederacionService{

	@Autowired
	FederacionRepository federacionRepo;
	
	@Override
	public FederacionModel saveFederation(FederacionModel federacion) {
		FederacionModel result = new FederacionModel();
		try {
			result = federacionRepo.save(federacion);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<FederacionModel> getAllFederations() {
		List<FederacionModel> result = new ArrayList<>();
		try {
			result = federacionRepo.findAll();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public FederacionModel getById(Integer id) {
		FederacionModel result = new FederacionModel();
		try {
			result = federacionRepo.findById(id).get();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public List<FederacionModel> getByFilter(FederacionModel filter) {
		List<FederacionModel> result = new ArrayList<>();
		try {
			Example<FederacionModel> exmple = Example.of(filter);
			result = federacionRepo.findAll(exmple);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	@Override
	public FederacionModel updateFederation(FederacionModel federacion) {
		FederacionModel result = new FederacionModel();
		try {
			if(federacionRepo.existsById(federacion.getCod_federacion())) {
				result = federacionRepo.findById(federacion.getCod_federacion()).get();
				if(federacion.getDireccion()!= null){
					result.setDireccion(federacion.getDireccion());
				}
				if(federacion.getCif()!= null){
					result.setCif(federacion.getCif());
				}
				if(federacion.getTelefono()!= null){
					result.setTelefono(federacion.getTelefono());
				}
				result = federacionRepo.save(result);
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
				federacionRepo.deleteById(id);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
