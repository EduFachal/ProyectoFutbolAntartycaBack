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

import com.antartyca.proyecto.model.EmpleadoModel;
import com.antartyca.proyecto.services.EmpleadoService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping( value = "/empleado")
public class EmpleadoController {
	
	@Autowired
	EmpleadoService empleadoServ;
	
	@RequestMapping(method =  RequestMethod.POST , value = "/save")
	public EmpleadoModel guardarEquipo(@RequestBody EmpleadoModel empleado) {
		return empleadoServ.saveEmployer(empleado);
		
	}
	
	@RequestMapping(value = "/empleados")
	public List<EmpleadoModel> getAllEquipos(){
		return empleadoServ.getAllEmployees();
	}
	
	@GetMapping(value = "/empleados/{id}")
	public EmpleadoModel getById(@PathVariable("id") Integer id){
		return empleadoServ.getById(id);
	}
	
	@PostMapping(value = "/filter")
	public List<EmpleadoModel> getByFilter(@RequestBody EmpleadoModel empleado){
		return empleadoServ.getByFilter(empleado);
	}
	
	@GetMapping(value = "/delete/{id}")
	public void deleteById(@PathVariable("id") Integer id){
		empleadoServ.deleteById(id);
	}

	@PostMapping(value = "/update")
    public EmpleadoModel updateTeam(@RequestBody EmpleadoModel empleado){
        return empleadoServ.updateEmployer(empleado);
    }
}