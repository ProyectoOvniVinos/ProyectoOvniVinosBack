package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="inventario_general")
public class InventarioGeneralVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_registro; 
	
	@Column(name="codigo_producto")
	private int codigo_producto;
	
	@Column(name="cantidad_producto")
	private int cantidad_producto;
	
	@Column(name="fecha_ultimo_ingreso_inventario")
	private String fecha_ultimo_ingreso_inventario;

	public int getId_registro() {
		return id_registro;
	}

	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
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

	public String getFecha_ultimo_ingreso_inventario() {
		return fecha_ultimo_ingreso_inventario;
	}

	public void setFecha_ultimo_ingreso_inventario(String fecha_ultimo_ingreso_inventario) {
		this.fecha_ultimo_ingreso_inventario = fecha_ultimo_ingreso_inventario;
	}
	
	
}
