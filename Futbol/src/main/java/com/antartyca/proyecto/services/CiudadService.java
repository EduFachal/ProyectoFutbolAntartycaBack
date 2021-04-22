package com.antartyca.proyecto.services;

import java.util.List;

import com.antartyca.proyecto.model.CiudadModel;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

public interface CiudadService {

	public CiudadModel saveCity(CiudadModel ciudad);
	public List<CiudadModel> getAllCities();
	public CiudadModel getById(Integer id);
	public List<CiudadModel> getByFilter(CiudadModel filter);
	public CiudadModel updateCity(CiudadModel ciudad);
	public void deleteById(Integer id);
}
