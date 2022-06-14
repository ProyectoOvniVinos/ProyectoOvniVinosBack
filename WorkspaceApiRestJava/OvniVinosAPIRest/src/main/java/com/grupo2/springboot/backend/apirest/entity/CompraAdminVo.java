package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="compra_admin")
public class CompraAdminVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_puente;
	
	@Column(name="codigo_compra")
	private int codigo_compra;
	
	@Column(name="codigo_producto")
	private int codigo_producto;
	
	@Column(name="cantidad_producto")
	private int cantidad_producto;
	
	@Column(name="id_registro_contabilidad_diaria")
	private int id_registro_contabilidad_diaria;

	public int getId_puente() {
		return id_puente;
	}

	public void setId_puente(int id_puente) {
		this.id_puente = id_puente;
	}

	public int getCodigo_compra() {
		return codigo_compra;
	}

	public void setCodigo_compra(int codigo_compra) {
		this.codigo_compra = codigo_compra;
	}

	public int getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(int codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public int getCantidad_producto() {
		return cantidad_producto;
	}

	public void setCantidad_producto(int cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}

	public int getId_registro_contabilidad_diaria() {
		return id_registro_contabilidad_diaria;
	}

	public void setId_registro_contabilidad_diaria(int id_registro_contabilidad_diaria) {
		this.id_registro_contabilidad_diaria = id_registro_contabilidad_diaria;
	}
	
	
	
}
