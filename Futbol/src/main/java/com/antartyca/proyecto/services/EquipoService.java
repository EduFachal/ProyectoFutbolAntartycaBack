package com.antartyca.proyecto.services;

import java.util.List;

import com.antartyca.proyecto.model.EquipoModel;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */


public interface EquipoService {

	public EquipoModel saveTeam(EquipoModel equipo);
	public List<EquipoModel> getAllTeams();
	public EquipoModel getById(Integer id);
	public List<EquipoModel> getByFilter(EquipoModel filter);
	public EquipoModel updateTeam(EquipoModel equipo);
	public void deleteById(Integer id);
	
}
