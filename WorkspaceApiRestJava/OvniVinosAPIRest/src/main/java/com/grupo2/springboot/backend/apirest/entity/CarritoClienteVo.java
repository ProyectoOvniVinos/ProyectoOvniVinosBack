package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="carrito_cliente")
public class CarritoClienteVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_carrito;
	
	@Column(name="precio_carrito")
	private double precio_carrito;
	
	@Column(name="cantidad_producto")
	private int cantidad_carrito;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnoreProperties(value={"carrito","hibernateLazyInitializer","handler"},allowSetters=true)
	@JoinColumn(name="cliente")
	private ClienteVo cliente;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "carrito")
	private List<ItemCarritoVo> iitem_carrito;

	public int getId_carrito() {
		return id_carrito;
	}

	public void setId_carrito(int id_carrito) {
		this.id_carrito = id_carrito;
	}

	public double getPrecio_carrito() {
		return precio_carrito;
	}

	public void setPrecio_carrito(double precio_carrito) {
		this.precio_carrito = precio_carrito;
	}

	public int getCantidad_carrito() {
		return cantidad_carrito;
	}

	public void setCantidad_carrito(int cantidad_carrito) {
		this.cantidad_carrito = cantidad_carrito;
	}

	public ClienteVo getCliente() {
		return cliente;
	}

	public void setCliente(ClienteVo cliente) {
		this.cliente = cliente;
	}

	public List<ItemCarritoVo> getIitem_carrito() {
		return iitem_carrito;
	}

	public void setIitem_carrito(List<ItemCarritoVo> iitem_carrito) {
		this.iitem_carrito = iitem_carrito;
	}
	
	
	
}
