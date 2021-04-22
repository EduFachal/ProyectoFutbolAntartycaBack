package com.antartyca.proyecto.controllers;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * Ejemplo para poder implementar el HTML a los controller 
 */

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.antartyca.proyecto.services.EquipoService;
import com.antartyca.proyecto.services.JugadorService;

@Controller
@RequestMapping( value = "/show")
public class MainController {

	@Value("${title.page.equipo}")
	String titleTeam;
	
	@Value("${title.page.jugador}")
	String titlePlayer;
	
	@Autowired
	JugadorService jugadorServ;
	
	@Autowired
	EquipoService equipoServ;
	
	@RequestMapping(value = "/equipos")
	public String getAllEquipos(Model model){
		model.addAttribute("title", titleTeam);
		model.addAttribute("teams", equipoServ.getAllTeams());
		return "equipos";
	}
	
	@PostMapping(value = "/id")
	public String getByIdTeam(@RequestParam("team_id") Integer id, Model model){
		model.addAttribute("teamId", equipoServ.getById(id));
		return "equipos";
	}
	@RequestMapping(value = "/jugadores")
	public String getAllPlayers(Model model){
		model.addAttribute("title", titlePlayer);
		model.addAttribute("players", jugadorServ.getAllPlayers());
		return "jugadores";
	}
	
	@PostMapping(value = "/jugadoresid")
	public String getByIdFormPlayer(@RequestParam("player_id") Integer id,Model model){
		if(id != null) {
			model.addAttribute("playerId", jugadorServ.getById(id));	
		}
		return "jugadores";
	}
	
	@GetMapping(value = "/equipo/{id}")
	public String getById(@PathVariable("id") Integer id,Model model){
		model.addAttribute("playerId", jugadorServ.getById(id));
		return "jugadores";
	}
}
