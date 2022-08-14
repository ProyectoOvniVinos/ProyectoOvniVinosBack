package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "venta_cliente")
public class VentaClienteVo implements Serializable {

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
	
	@Column(name = "precio_venta_detalle")
	private double precio_venta_detalle;

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

	public double getPrecio_venta_detalle() {
		return precio_venta_detalle;
	}

	public void setPrecio_venta_detalle() {
		double total;
		total = this.cantidad_producto * this.codigo_producto.getPrecio_producto();
		this.precio_venta_detalle = total;
	}
	
	

	public void setPrecio_venta_detalle(double precio_venta_detalle) {
		this.precio_venta_detalle = precio_venta_detalle;
	}

	@Override
	public String toString() {
		return "VentaClienteVo [id_puente=" + id_puente + ", codigo_producto=" + codigo_producto
				+ ", cantidad_producto=" + cantidad_producto + ", id_registro_contabilidad_diaria="
				+ "]";
	}
	
	

}
