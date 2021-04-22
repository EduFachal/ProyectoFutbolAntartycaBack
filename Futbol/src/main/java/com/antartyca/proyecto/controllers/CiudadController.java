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

import com.antartyca.proyecto.model.CiudadModel;
import com.antartyca.proyecto.model.DepartamentoModel;
import com.antartyca.proyecto.services.CiudadService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping( value = "/ciudad")
public class CiudadController {

	
	@Autowired
	CiudadService ciudadServ;
	
	@RequestMapping(method =  RequestMethod.POST , value = "/save")
	public CiudadModel guardarEquipo(@RequestBody CiudadModel ciudad) {
		return ciudadServ.saveCity(ciudad);
		
	}
	
	@RequestMapping(value = "/ciudades")
	public List<CiudadModel> getAllEquipos(){
		return ciudadServ.getAllCities();
	}
	
	@GetMapping(value = "/ciudades/{id}")
	public CiudadModel getById(@PathVariable("id") Integer id){
		return ciudadServ.getById(id);
	}
	
	@PostMapping(value = "/filter")
	public List<CiudadModel> getByFilter(@RequestBody CiudadModel ciudad){
		return ciudadServ.getByFilter(ciudad);
	}
	
	@GetMapping(value = "/delete/{id}")
	public void deleteById(@PathVariable("id") Integer id){
		ciudadServ.deleteById(id);
	}

	@PostMapping(value = "/update")
	public CiudadModel updateTeam(@RequestBody CiudadModel ciudad){
		return ciudadServ.updateCity(ciudad);
	}
	
}
