package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;


public class CompraVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo_compra;
	
	@Column(name="precio_compra")
	private double precio_compra;
	
	@Column(name="cantidad_compra")
	private int cantidad_compra;
	
	@Column(name="fecha_compra")
	private String fecha_compra;
	
	@Column(name="correo_admin")
	private String correo_admin;

	public int getCodigo_compra() {
		return codigo_compra;
	}

	public void setCodigo_compra(int codigo_compra) {
		this.codigo_compra = codigo_compra;
	}

	public double getPrecio_compra() {
		return precio_compra;
	}

	public void setPrecio_compra(double precio_compra) {
		this.precio_compra = precio_compra;
	}

	public int getCantidad_compra() {
		return cantidad_compra;
	}

	public void setCantidad_compra(int cantidad_compra) {
		this.cantidad_compra = cantidad_compra;
	}

	public String getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(String fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public String getCorreo_admin() {
		return correo_admin;
	}

	public void setCorreo_admin(String correo_admin) {
		this.correo_admin = correo_admin;
	}
	
	
	
}
