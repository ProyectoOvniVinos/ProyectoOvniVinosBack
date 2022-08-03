package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "compra_admin")
public class CompraAdminVo implements Serializable {

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
	
	@Column(name = "precio_compra_detalle")
	private double precio_compra_detalle;

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

	public double getPrecio_compra_detalle() {
		return precio_compra_detalle;
	}

	public void setPrecio_compra_detalle() {
		double total = this.getCodigo_producto().getPrecio_producto()*this.cantidad_producto;
		this.precio_compra_detalle = total;
	}

	@Override
	public String toString() {
		return "CompraAdminVo [id_puente=" + id_puente + ", codigo_producto=" + codigo_producto + ", cantidad_producto="
				+ cantidad_producto + "]";
	}

}
