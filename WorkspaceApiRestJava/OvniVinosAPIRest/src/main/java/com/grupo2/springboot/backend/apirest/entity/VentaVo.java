package com.grupo2.springboot.backend.apirest.entity;

import javax.persistence.*;

@Entity
@Table(name="venta")
public class VentaVo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_venta;
	
	@Column(name="correo_cliente")
	private String correo_cliente;
	
	@Column(name="precio_venta")
	private double precio_venta;
	
	@Column(name="fecha")
	private String fecha;

	public int getCodigo_venta() {
		return codigo_venta;
	}

	public void setCodigo_venta(int codigo_venta) {
		this.codigo_venta = codigo_venta;
	}

	public String getCorreo_cliente() {
		return correo_cliente;
	}

	public void setCorreo_cliente(String correo_cliente) {
		this.correo_cliente = correo_cliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
