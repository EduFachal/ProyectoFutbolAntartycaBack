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

import com.antartyca.proyecto.model.FederacionModel;
import com.antartyca.proyecto.services.FederacionService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping( value = "/federacion")
public class FederacionController {

    @Autowired
    FederacionService federacionServ;

    @RequestMapping(method =  RequestMethod.POST , value = "/save")
    public FederacionModel guardarEquipo(@RequestBody FederacionModel federacion) {
        return federacionServ.saveFederation(federacion);

    }

    @GetMapping(value = "/federaciones")
    public List<FederacionModel> getAllEquipos(){
        return federacionServ.getAllFederations();
    }

    @GetMapping(value = "/federaciones/{id}")
    public FederacionModel getById(@PathVariable("id") Integer id){
        return federacionServ.getById(id);
    }
    
	@PostMapping(value = "/filter")
	public List<FederacionModel> getByFilter(@RequestBody FederacionModel federacion){
		return federacionServ.getByFilter(federacion);
	}

    @GetMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id){
    	federacionServ.deleteById(id);
    }

    @PostMapping(value = "/update")
    public FederacionModel updateTeam(@RequestBody FederacionModel federacion){
        return federacionServ.updateFederation(federacion);
    }
}
