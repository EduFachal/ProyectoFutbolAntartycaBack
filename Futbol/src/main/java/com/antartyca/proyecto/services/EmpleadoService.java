package com.antartyca.proyecto.services;

import java.util.List;

import com.antartyca.proyecto.model.EmpleadoModel;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

public interface EmpleadoService {
	public EmpleadoModel saveEmployer(EmpleadoModel empleado);
	public List<EmpleadoModel> getAllEmployees();
	public EmpleadoModel getById(Integer id);
	public List<EmpleadoModel> getByFilter(EmpleadoModel filter);
	public EmpleadoModel updateEmployer(EmpleadoModel empleado);
	public void deleteById(Integer id);
	
}
