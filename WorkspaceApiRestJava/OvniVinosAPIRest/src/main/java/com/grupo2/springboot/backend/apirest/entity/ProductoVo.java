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
	
	@Column(name="precio_producto_proveedor")
	private double precio_producto_proveedor;
	
	@Column(name="descripcion_producto")
	private String descripcion_producto;
	
	@Column(name="foto_producto")
	private String foto_producto;
	
	@Column(name="estado")
	private String estado;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	public double getPrecio_producto() {
		return precio_producto;
	}

	public void setPrecio_producto(double precio_producto) {
		this.precio_producto = precio_producto;
	}

	public double getPrecio_producto_proveedor() {
		return precio_producto_proveedor;
	}

	public void setPrecio_producto_proveedor(double precio_producto_proveedor) {
		this.precio_producto_proveedor = precio_producto_proveedor;
	}

	public String getDescripcion_producto() {
		return descripcion_producto;
	}

	public void setDescripcion_producto(String descripcion_producto) {
		this.descripcion_producto = descripcion_producto;
	}
	
	

	public String getFoto_producto() {
		return foto_producto;
	}

	public void setFoto_producto(String foto_producto) {
		this.foto_producto = foto_producto;
	}

	@Override
	public String toString() {
		return "ProductoVo [codigo_producto=" + codigo_producto + ", nombre_producto=" + nombre_producto
				+ ", precio_producto=" + precio_producto + ", precio_producto_proveedor=" + precio_producto_proveedor
				+ ", descripcion_producto=" + descripcion_producto + "]";
	}
	
}
