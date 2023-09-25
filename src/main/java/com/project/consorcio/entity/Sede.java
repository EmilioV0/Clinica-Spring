package com.project.consorcio.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_sede")
public class Sede {
	@Id
	@Column(name="cod_sede")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(name = "nom_sede")
	private String nombre;
	
	@OneToMany(mappedBy = "sede")
	private List<Medico> listaSede;

	public Sede(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Sede() {}

	public Integer getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Medico> getListaSede() {
		return listaSede;
	}

	public void setListaSede(List<Medico> listaSede) {
		this.listaSede = listaSede;
	}
	
	
}
