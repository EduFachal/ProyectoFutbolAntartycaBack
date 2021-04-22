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

import com.antartyca.proyecto.model.DepartamentoModel;
import com.antartyca.proyecto.services.DepartamentoService;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping( value = "/departamento")
public class DepartamentoController {
	
	@Autowired
	DepartamentoService departamentoServ;
	
	@RequestMapping(method =  RequestMethod.POST , value = "/save")
	public DepartamentoModel saveDepartment(@RequestBody DepartamentoModel departamento) {
		return departamentoServ.saveDepartment(departamento);
		
	}
	
	@RequestMapping(value = "/departamentos")
	public List<DepartamentoModel> getAllDepartments(){
		return departamentoServ.getAllDepartments();
	}
	
	@GetMapping(value = "/departamentos/{id}")
	public DepartamentoModel getById(@PathVariable("id") Integer id){
		return departamentoServ.getById(id);
	}
	
	@PostMapping(value = "/filter")
	public List<DepartamentoModel> getByFilter(@RequestBody DepartamentoModel departamento){
		return departamentoServ.getByFilter(departamento);
	}
	
	@GetMapping(value = "/delete/{id}")
	public void deleteById(@PathVariable("id") Integer id){
		departamentoServ.deleteById(id);
	}

	@PostMapping(value = "/update")
	public DepartamentoModel updateTeam(@RequestBody DepartamentoModel departamento){
		return departamentoServ.updateDepartment(departamento);
	}


}
