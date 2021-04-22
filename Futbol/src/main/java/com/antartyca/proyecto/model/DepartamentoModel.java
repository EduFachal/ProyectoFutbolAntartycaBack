package com.antartyca.proyecto.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="departamentos")
public class DepartamentoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="departamento_seq")
	@SequenceGenerator(name="departamento_seq")
	private Integer cod_depart;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;
	
	@ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="cod_ciudad")
 //   @JsonIgnoreProperties("departamentos")
    private CiudadModel ciudad;
		
		
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_depart")
	@Column(nullable = false)
	@JsonIgnoreProperties("departamentos")
	private List<EmpleadoModel> empleados;
	
	@ManyToOne/*(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)*/
	@JoinColumn(name="cod_federacion")
	@JsonIgnoreProperties("departamentos")
	private FederacionModel federacion;
	
	public Integer getCod_depart() {
		return cod_depart;
	}

	public void setCod_depart(Integer cod_depart) {
		this.cod_depart = cod_depart;
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

	public List<EmpleadoModel> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<EmpleadoModel> empleados) {
		this.empleados = empleados;
	}

	public FederacionModel getFederacion() {
		return federacion;
	}

	public void setFederacion(FederacionModel federacion) {
		this.federacion = federacion;
	}
	
}
