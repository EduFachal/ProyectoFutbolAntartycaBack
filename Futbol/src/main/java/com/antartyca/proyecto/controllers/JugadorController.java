package com.antartyca.proyecto.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antartyca.proyecto.model.EquipoModel;
import com.antartyca.proyecto.model.JugadorModel;
import com.antartyca.proyecto.model.JugadorSearchRequestModel;
import com.antartyca.proyecto.services.JugadorService;

import io.swagger.annotations.ApiOperation;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping( value = "/jugador")
public class JugadorController {
	
	
	@Autowired
	JugadorService jugadorServ;
		
	/*@PostMapping( value = "/save")
	public JugadorModel savePlayer(@RequestBody JugadorModel jugador) {
		System.out.println(jugador.getNombre());
		if(jugador.getCod_jugador()==null) {
				int cod = jugadorServ.getAllPlayers().get(jugadorServ.getAllPlayers().size()).getCod_jugador();
				jugador.setCod_jugador(cod);
		}
	
		return jugadorServ.savePlayer(jugador);
		
	}*/
	@PostMapping( value = "/save")
	public ResponseEntity<JugadorModel> savePlayer(@RequestBody JugadorModel jugador) {
		jugadorServ.savePlayer(jugador);
		return  new ResponseEntity<JugadorModel>(HttpStatus.OK); 
		
	}
	
	@RequestMapping(value = "/jugadores")
	public List<JugadorModel> getAllEquipos(){
		return jugadorServ.getAllPlayers();
	}
		
	@GetMapping(value = "/jugadores/{id}")
	public JugadorModel getById(@PathVariable("id") Integer id){
		return jugadorServ.getById(id);
	}
	
	@PostMapping(value = "/jugadorfilter")
	public List<JugadorModel> getByFilter(@RequestBody JugadorModel jugador){
		return jugadorServ.getByFilter(jugador);
	}
	
	@RequestMapping(value = "/delete/{id}" , method =  RequestMethod.GET )
	public void deleteById(@PathVariable("id") Integer id){
		jugadorServ.deleteById(id);
	}

	@PostMapping(value = "/update")
	public JugadorModel updatePlayer(@RequestBody JugadorModel jugador){
		return jugadorServ.updatePlayer(jugador);
	}
	
	@ApiOperation(value="SEARCH PLAYERS")
	@PostMapping( value="/search",
			      consumes=MediaType.APPLICATION_JSON_VALUE,
			      produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JugadorModel>> searchPersons( @RequestBody JugadorSearchRequestModel jugadorSearchRequestModel ) {
		List<JugadorModel> jugadores = jugadorServ.searchPlayer(jugadorSearchRequestModel);
		
		//Nos devuelve la lista de los jugadores encontrados y el OK del servidor
		return new ResponseEntity<List<JugadorModel>>( jugadores , HttpStatus.OK); 
	}
	
	@GetMapping(value = "/buscarPorPuestoYGoles/{puesto}/{goles}")
	public List<JugadorModel> buscarPuestoGoles(@PathVariable(value = "puesto") String puesto,@PathVariable(value = "goles") int goles){
		List <JugadorModel> jugadores = new ArrayList<JugadorModel>();
		
		jugadores = jugadorServ.buscarPorPuestoYGoles(puesto, goles);
		return jugadores;
	}
}
