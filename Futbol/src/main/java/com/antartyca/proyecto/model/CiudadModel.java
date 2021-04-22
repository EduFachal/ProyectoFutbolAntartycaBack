package com.antartyca.proyecto.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Entity
@Table(name="ciudades")
public class CiudadModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ciudad_seq")
	@SequenceGenerator(name="ciudad_seq")
	private Integer cod_ciudad;
	
	@Column
	private String nombre;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JsonIgnoreProperties("ciudad")
	@JoinColumn(name="cod_ciudad")
	private List<TorneoModel> torneos;
	
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="cod_ciudad")
    @JsonIgnoreProperties("departamentos")
    private List<DepartamentoModel> departamentos;
	

	public Integer getCod_ciudad() {
		return cod_ciudad;
	}

	public void setCod_ciudad(Integer cod_ciudad) {
		this.cod_ciudad = cod_ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<TorneoModel> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<TorneoModel> torneos) {
		this.torneos = torneos;
	}

	public List<DepartamentoModel> getDepartamento() {
		return departamentos;
	}

	public void setDepartamento(List<DepartamentoModel> departamentos) {
		this.departamentos = departamentos;
	}

}
