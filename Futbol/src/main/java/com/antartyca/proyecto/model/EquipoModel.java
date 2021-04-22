package com.antartyca.proyecto.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Entity
@Component
@Table(name="equipo")
public class EquipoModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="equipo_seq")
	@SequenceGenerator(name="equipo_seq")
	private int cod_equipo;
	
	@Column
	private String nombre_equipo;
	
	@Column
	private String direccion;
	
	@Column
	private String fecha_fundacion;
	
	@OneToMany(cascade=CascadeType.ALL/*,mappedBy="equipo"*/)
	@JsonIgnoreProperties("equipo")
	@JoinColumn(name="cod_equipo")
	@Column(nullable = false)
	private List<JugadorModel> jugadores;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name="EQUIPO_TORNEO_TABLA",
			joinColumns=@JoinColumn(name="cod_equipo"),
			inverseJoinColumns=@JoinColumn(name="cod_torneo")
			)
	@JsonIgnoreProperties("lst_equipos")
	private List<TorneoModel> lst_torneos;
	
	public EquipoModel() {
		
	}

	public Integer getCod_equipo() {
		return cod_equipo;
	}

	public void setCod_equipo(Integer cod_equipo) {
		this.cod_equipo = cod_equipo;
	}

	public String getNombre_equipo() {
		return nombre_equipo;
	}

	public void setNombre_equipo(String nombre_equipo) {
		this.nombre_equipo = nombre_equipo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFecha_fundacion() {
		return fecha_fundacion;
	}

	public void setFecha_fundacion(String fecha_fundacion) {
		this.fecha_fundacion = fecha_fundacion;
	}

	public List<JugadorModel> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<JugadorModel> jugadores) {
		this.jugadores = jugadores;
	}

	public List<TorneoModel> getLst_torneos() {
		return lst_torneos;
	}

	public void setLst_torneos(List<TorneoModel> lst_torneos) {
		this.lst_torneos = lst_torneos;
	}
	
}
