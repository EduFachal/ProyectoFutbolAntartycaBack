package com.antartyca.proyecto.services;

import java.util.List;

import com.antartyca.proyecto.model.JugadorModel;
import com.antartyca.proyecto.model.JugadorSearchRequestModel;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

public interface JugadorService {
	public JugadorModel savePlayer(JugadorModel jugador);
	public List<JugadorModel> getAllPlayers();
	public JugadorModel getById(Integer id);
	public List<JugadorModel> getByFilter(JugadorModel filter);
	public JugadorModel updatePlayer(JugadorModel jugador);
	public void deleteById(Integer id);
	
	List<JugadorModel> searchPlayer(JugadorSearchRequestModel jugadorSearchRequestModel);
	//public List<JugadorModel> busquedaPorGoles(int goles);
	
	List<JugadorModel> buscarPorPuestoYGoles(String puesto, int goles);
}
