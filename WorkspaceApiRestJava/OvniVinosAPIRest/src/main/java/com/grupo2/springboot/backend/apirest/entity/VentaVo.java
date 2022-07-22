package com.grupo2.springboot.backend.apirest.entity;

import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="venta")
public class VentaVo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_venta;
	
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"},allowSetters=true)
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private ClienteVo correo_cliente;
	
	@Column(name="precio_venta")
	private double precio_venta;
	
	@Column(name="fecha")
	private Date fecha;

	public int getCodigo_venta() {
		return codigo_venta;
	}

	public void setCodigo_venta(int codigo_venta) {
		this.codigo_venta = codigo_venta;
	}

	public ClienteVo getCorreo_cliente() {
		return correo_cliente;
	}

	public void setCorreo_cliente(ClienteVo correo_cliente) {
		this.correo_cliente = correo_cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
