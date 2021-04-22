package com.antartyca.proyecto.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Entity
@Table(name="torneos")
public class TorneoModel {
	
	@Id
	@SequenceGenerator(sequenceName="torneo_sequen" , name="torneo_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="torneo_seq")
	private Integer cod_torneo;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="cod_ciudad")
	@JsonIgnoreProperties("torneos")
	private CiudadModel ciudad;
	
	@ManyToMany(cascade = CascadeType.ALL,
			mappedBy="lst_torneos")
	@JsonIgnoreProperties("lst_torneos")
	private List<EquipoModel> lst_equipos;

	public Integer getCod_torneo() {
		return cod_torneo;
	}

	public void setCod_torneo(Integer cod_torneo) {
		this.cod_torneo = cod_torneo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CiudadModel getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadModel ciudad) {
		this.ciudad = ciudad;
	}

	public List<EquipoModel> getLst_equipos() {
		return lst_equipos;
	}

	public void setLst_equipos(List<EquipoModel> lst_equipos) {
		this.lst_equipos = lst_equipos;
	}
	
}
