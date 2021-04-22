package com.antartyca.proyecto.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JugadorSearchRequestModel {
	/**
	 * JsonProperty garantiza que se usará en el objeto Json 
	 * y no en el nombre de la propiedad.
	 */
	
	@JsonProperty(value ="cod_jugador")
	private int cod_jugador;
	
	@JsonProperty(value ="nombre")
	private String nombre;
	
	@JsonProperty(value ="telefono")
	private String telefono;
	
	@JsonProperty(value ="puesto")
	private String puesto;
	
	@JsonProperty(value ="goles")
	private int goles;
	
	@JsonProperty(value ="altura")
	private int altura;
	
	@JsonProperty(value ="tarjetas")
	private int tarjetas;
	
	@JsonProperty(value ="activo")
	private boolean activo = true;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	
	
	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
	}
	
	public int getCod_jugador() {
		return cod_jugador;
	}
	
	public void setCod_jugador(int cod_jugador) {
		this.cod_jugador = cod_jugador;
	}
	
	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(int tarjetas) {
		this.tarjetas = tarjetas;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
