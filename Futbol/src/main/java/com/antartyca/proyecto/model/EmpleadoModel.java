package com.antartyca.proyecto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Entity
@Table(name="empleados")
public class EmpleadoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer cod_emp;
	
	@Column
	private String dni;
	
	@Column
	private String nombre;
	
	@Column
	private String direccion;
	
	@Column
	private String telefono;
	
	@ManyToOne/*(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)*/
	@JoinColumn(name="cod_depart")
	@JsonIgnoreProperties("empleados")
	private DepartamentoModel departamento;

	public Integer getCod_emp() {
		return cod_emp;
	}

	public void setCod_emp(Integer cod_emp) {
		this.cod_emp = cod_emp;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public DepartamentoModel getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepartamentoModel departamento) {
		this.departamento = departamento;
	}
	
}
