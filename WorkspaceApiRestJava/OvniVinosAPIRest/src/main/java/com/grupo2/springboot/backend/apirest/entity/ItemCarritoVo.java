package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "item_carrito")
public class ItemCarritoVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_puente;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_producto")
	private ProductoVo codigo_producto;
	
	@Column(name = "cantidad_producto")
	private int cantidad_producto;
	
	@Column(name="precio_item")
	private double precio_item;

	public int getId_puente() {
		return id_puente;
	}

	public void setId_puente(int id_puente) {
		this.id_puente = id_puente;
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

	public double getPrecio_item() {
		return precio_item;
	}

	public void setPrecio_item() {
		double total;
		total = this.cantidad_producto * this.codigo_producto.getPrecio_producto();
		this.precio_item = total;
	}
	
	

}
