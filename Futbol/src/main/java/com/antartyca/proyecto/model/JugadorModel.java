package com.antartyca.proyecto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Authors: Eduardo Fachal and Aitor Gonzalez
 * Date: 21/3/2021
 * 
 */

@Entity
@Component
@Table(name = "jugadores")
public class JugadorModel {
	
	@Id
	@Column(name="cod_jugador")
	@SequenceGenerator(sequenceName="jugadores_sequen" , name="jugador_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="jugador_seq")
	@JsonProperty(value ="cod_jugador")
	private int cod_jugador;
	
	@Column
	@JsonProperty(value ="nombre")
	private String nombre;
	
	@Column
	@JsonProperty(value ="telefono")
	private String telefono;
	
	@Column
	@JsonProperty(value ="puesto")
	private String puesto;
	
	@Column(nullable = false)
	@JsonProperty(value ="goles")
	private Integer goles;
	
	@Column(nullable = false)
	@JsonProperty(value ="altura")
	private Integer altura;
	
	@Column(nullable = false)
	@JsonProperty(value ="tarjetas")
	private Integer tarjetas;
	
	@Column(nullable = false)
	@JsonProperty(value ="activo")
	private Boolean activo = true;
	
	@Column
    @JsonProperty(value ="fecha_nacimiento")
    private Date fecha_nacimiento;
	
	public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
	
	@ManyToOne//(cascade = CascadeType.ALL)
	@JoinColumn(name="cod_equipo")
	@JsonIgnoreProperties("jugadores")
	private EquipoModel equipo;
	
	public JugadorModel() {
		
	}
	
	public JugadorModel(String nom, String tlf, String puest, EquipoModel equi) {
		this.nombre = nom;
		this.telefono  = tlf;
		this.puesto = puest;
		this.equipo = equi;
	}

	public Integer getCod_jugador() {
		return cod_jugador;
	}

	public void setCod_jugador(Integer cod_jugador) {
		this.cod_jugador = cod_jugador;
	}

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

	public EquipoModel getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoModel equipo) {
		this.equipo = equipo;
	}
	
	public int getGoles() {
		return goles;
	}

	public void setGoles(int goles) {
		this.goles = goles;
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

	@Override
	public String toString() {
	      return "JugadorModel [cod_jugador=" + cod_jugador + ", nombre=" + nombre + ", telefono=" + telefono
	                + ", puesto=" + puesto + ", goles=" + goles + ", altura=" + altura + ", fecha_nacimiento="
	                + fecha_nacimiento + ", tarjetas=" + tarjetas + ", activo=" + activo + ", equipo=" + equipo + "]";
	}
	
}
