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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Entity
@Table(name="federaciones")
public class FederacionModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer cod_federacion;
	
	@Column
	private String cif;
	
	@Column
	private String direccion;
	
	@Column
	private String telefono;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_federacion")
	@Column(nullable = false)
	@JsonIgnoreProperties("federaciones")
	private List<DepartamentoModel> departamentos;

	public List<DepartamentoModel> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<DepartamentoModel> departamentos) {
		this.departamentos = departamentos;
	}

	public Integer getCod_federacion() {
		return cod_federacion;
	}

	public void setCod_federacion(Integer cod_federacion) {
		this.cod_federacion = cod_federacion;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
