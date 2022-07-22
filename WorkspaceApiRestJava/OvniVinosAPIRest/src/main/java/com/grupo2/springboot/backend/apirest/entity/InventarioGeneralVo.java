package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="inventario_general")
public class InventarioGeneralVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_registro; 
	
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"},allowSetters=true)
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private ProductoVo codigo_producto;
	
	@Column(name="cantidad_producto")
	private int cantidad_producto;
	
	@Column(name="fecha_ultimo_ingreso_inventario")
	private Date fecha_ultimo_ingreso_inventario;

	public int getId_registro() {
		return id_registro;
	}

	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}

	public ProductoVo getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(ProductoVo codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public int getCantidad_producto() {
		return cantidad_producto;
	}

	public void setCantidad_producto(int cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}

	public Date getFecha_ultimo_ingreso_inventario() {
		return fecha_ultimo_ingreso_inventario;
	}

	public void setFecha_ultimo_ingreso_inventario(Date fecha_ultimo_ingreso_inventario) {
		this.fecha_ultimo_ingreso_inventario = fecha_ultimo_ingreso_inventario;
	}
	
	
}
