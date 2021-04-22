package com.antartyca.proyecto.services;

import java.util.List;

import com.antartyca.proyecto.model.DepartamentoModel;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

public interface DepartamentoService {

	public DepartamentoModel saveDepartment(DepartamentoModel depart);
	public List<DepartamentoModel> getAllDepartments();
	public DepartamentoModel getById(Integer id);
	public List<DepartamentoModel> getByFilter(DepartamentoModel filter);
	public DepartamentoModel updateDepartment(DepartamentoModel depart);
	public void deleteById(Integer id);
}
