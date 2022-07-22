package com.grupo2.springboot.backend.apirest.entity;

import javax.persistence.*;

@Entity
@Table(name="producto")
public class ProductoVo {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_producto;
	
	@Column(name="nombre_producto")
	private String nombre_producto;
	
	@Column(name="precio_producto")
	private double precio_producto;
	
	@Column(name="precio_productoProveedor")
	private double precio_productoProveedor;
	
	@Column(name="descripcion_producto")
	private String descripcion_producto;

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

	public double getPrecio_producto() {
		return precio_producto;
	}

	public void setPrecio_producto(double precio_producto) {
		this.precio_producto = precio_producto;
	}

	public double getPrecio_productoProveedor() {
		return precio_productoProveedor;
	}

	public void setPrecio_productoProveedor(double precio_productoProveedor) {
		this.precio_productoProveedor = precio_productoProveedor;
	}

	public String getDescripcion_producto() {
		return descripcion_producto;
	}

	public void setDescripcion_producto(String descripcion_producto) {
		this.descripcion_producto = descripcion_producto;
	}
	
}
