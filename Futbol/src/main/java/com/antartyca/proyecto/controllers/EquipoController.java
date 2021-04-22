package com.antartyca.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;

import com.antartyca.proyecto.model.EquipoModel;
import com.antartyca.proyecto.services.EquipoService;

import antlr.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Api
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping( value = "/equipo")

public class EquipoController {
	
	
	@Autowired
	EquipoService equipoServ;
	
	/*@RequestMapping(method =  RequestMethod.POST , value = "/equiposave")
	public EquipoModel saveTeam(@RequestBody EquipoModel equipo) {
		
		return equipoServ.saveTeam(equipo);
		
	}*/
	
	@RequestMapping(method =  RequestMethod.POST , value = "/save")
	public ResponseEntity<EquipoModel> saveTeam(@RequestBody EquipoModel equipo) {
		 equipoServ.saveTeam(equipo);
		 return new ResponseEntity<EquipoModel>(HttpStatus.OK);
		
	}
	
    @ApiOperation(value = "Lista de los equipos",response = Iterable.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Realizado con exito"),
            @ApiResponse(code = 401, message = "No esta autorizado"), 
            @ApiResponse(code = 403, message = "Prohibido el paso"),
            @ApiResponse(code = 404, message = "No encontrado") })
	
	@GetMapping(value = "/equipos" , produces = "application/json")
	public List<EquipoModel> getAllTeams(){
		return equipoServ.getAllTeams();
	}
		
	
	/*@GetMapping(value = "/equipo/{id}")
	public EquipoModel getById(@PathVariable("id") Integer id){
		return equipoServ.getById(id);
	}*/
	
	//TODO: Borrar esto si no sirve
	//XXX: Hola
	//FIXME:
//	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/equipo/{id}")
	public ResponseEntity<EquipoModel> getById(@PathVariable("id") Integer id){
		EquipoModel equipoId =equipoServ.getById(id);
		ResponseEntity<EquipoModel> responseEquipo;
		if(equipoId == null || equipoId.getCod_equipo() == 0) {
			responseEquipo = new ResponseEntity<EquipoModel>(equipoId,HttpStatus.BAD_REQUEST);
		}else {
			responseEquipo = new ResponseEntity<EquipoModel>(equipoId,HttpStatus.OK);
		}
		return responseEquipo;
	}
	
	
	@Query("select * from equipo where nombre_equipo in :equipo")
	@PostMapping(value = "/equipofilter")
	public List<EquipoModel> getByFilter(@RequestBody EquipoModel equipo){
		return equipoServ.getByFilter(equipo);
	}
	
	@GetMapping(value = "/delete/{id}")
	public void deleteById(@PathVariable("id") Integer id){
		equipoServ.deleteById(id);
	}

	@PostMapping(value = "/update")
	public EquipoModel updateTeam(@RequestBody EquipoModel equipo){
		return equipoServ.updateTeam(equipo);
	}
}
