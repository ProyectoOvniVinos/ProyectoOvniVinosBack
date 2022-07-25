package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="inventario_detalles")
public class InventarioDetallesVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_detalles;
	
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"},allowSetters=true)
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="id_registro")
	private InventarioGeneralVo id_registro;
	
	@Column(name="codigo_producto")
	private int codigo_producto;
	
	@Column(name="cantidad_producto")
	private int cantidad_producto;

	public int getIdDetalles() {
		return id_detalles;
	}

	public void setIdDetalles(int idDetalles) {
		this.id_detalles = idDetalles;
	}

	public InventarioGeneralVo getId_registro() {
		return id_registro;
	}

	public void setId_registro(InventarioGeneralVo id_registro) {
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

}
