package com.antartyca.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.antartyca.proyecto.model.TorneoModel;
import com.antartyca.proyecto.services.TorneoService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping( value = "/torneo")
public class TorneoController {

	@Autowired
	TorneoService torneoServ;
	
	@RequestMapping(method =  RequestMethod.POST , value = "/save")
	public TorneoModel saveTournament(@RequestBody TorneoModel torneo) {
		return torneoServ.saveTournament(torneo);
		
	}
	
	@RequestMapping(value = "/torneos")
	public List<TorneoModel> getAllTournaments(){
		return torneoServ.getAllTournaments();
	}
	
	@GetMapping(value = "/torneos/{id}")
	public TorneoModel getById(@PathVariable("id") Integer id){
		return torneoServ.getById(id);
	}
	
	@GetMapping(value = "/delete/{id}")
	public void deleteById(@PathVariable("id") Integer id){
		torneoServ.deleteById(id);
	}
	
	@PostMapping(value = "/filter")
	public List<TorneoModel> getByFilter(@RequestBody TorneoModel torneo){
		return torneoServ.getByFilter(torneo);
	}

	@PostMapping(value = "/update")
	public TorneoModel updateTeam(@RequestBody TorneoModel torneo){
		return torneoServ.updateTournament(torneo);
	}
	
}
