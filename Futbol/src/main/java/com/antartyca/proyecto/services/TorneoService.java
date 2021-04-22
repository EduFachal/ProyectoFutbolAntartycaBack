package com.antartyca.proyecto.services;

import java.util.List;

import com.antartyca.proyecto.model.TorneoModel;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

public interface TorneoService {
	public TorneoModel saveTournament(TorneoModel torneo);
	public List<TorneoModel> getAllTournaments();
	public TorneoModel getById(Integer id);
	public List<TorneoModel> getByFilter(TorneoModel filter);
	public TorneoModel updateTournament(TorneoModel torneo);
	public void deleteById(Integer id);
}
