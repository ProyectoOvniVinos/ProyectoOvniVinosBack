package com.grupo2.springboot.backend.apirest.entity;

import javax.persistence.*;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="carrito_cliente")
public class CarritoClienteVo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_registro;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JsonIgnoreProperties(value={"carrito","hibernateLazyInitializer","handler"},allowSetters=true)
	@JoinColumn(name="cliente")
	private ClienteVo cliente;
	
	@Column(name="codigo_producto")
	private int codigo_producto;
	
	
	@Column(name="nombre_producto")
	private String nombre_producto;
	
	
	@Column(name="cantidad_producto")
	private int cantidad_producto;
	
	
	@Column(name="precio_producto")
	private double precio_producto;


	public int getId_registro() {
		return id_registro;
	}


	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}


	public ClienteVo getId_carrito() {
		return cliente;
	}


	public void setId_carrito(ClienteVo cliente) {
		this.cliente = cliente;
	}


	public int getCodigo_producto() {
		return codigo_producto;
	}


	public void setCodigo_producto(int codigo_producto) {
		this.codigo_producto = codigo_producto;
	}


	public String getNombre_producto() {
		return nombre_producto;
	}


	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}


	public int getCantidad_producto() {
		return cantidad_producto;
	}


	public void setCantidad_producto(int cantidad_producto) {
		this.cantidad_producto = cantidad_producto;
	}


	public double getPrecio_producto() {
		return precio_producto;
	}


	public void setPrecio_producto(double precio_producto) {
		this.precio_producto = precio_producto;
	}
	
	
}
