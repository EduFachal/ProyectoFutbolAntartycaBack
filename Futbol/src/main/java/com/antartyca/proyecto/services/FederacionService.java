package com.antartyca.proyecto.services;

import java.util.List;

import com.antartyca.proyecto.model.FederacionModel;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

public interface FederacionService {
	
	public FederacionModel saveFederation(FederacionModel federacion);
	public List<FederacionModel> getAllFederations();
	public FederacionModel getById(Integer id);
	public List<FederacionModel> getByFilter(FederacionModel filter);
	public FederacionModel updateFederation(FederacionModel federacion);
	public void deleteById(Integer id);
	
}
