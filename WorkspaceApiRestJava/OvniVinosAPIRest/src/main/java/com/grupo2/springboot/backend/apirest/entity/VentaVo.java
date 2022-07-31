package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "venta")
public class VentaVo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_venta;

	@Column(name = "precio_venta")
	private double precio_venta;

	@Column(name = "cantidad_venta")
	private int cantidad_venta;

	@Column(name = "fecha_venta")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd-HH:mm:ss")
	private LocalDateTime fecha_venta;

	@JsonIgnoreProperties(value = { "ventas", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "correo_cliente")
	private ClienteVo correo_cliente;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_registro_contabilidad_diaria")
	private ContabilidadDiariaVo id_registro_contabilidad_diaria;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "venta")
	private List<VentaClienteVo> ventas;

	public double getPrecio_venta() {
		return precio_venta;
	}

	public void setPrecio_venta(double precio_venta) {
		this.precio_venta = precio_venta;
	}

	public List<VentaClienteVo> getVentas() {
		return ventas;
	}

	public void setVentas(List<VentaClienteVo> ventas) {
		this.ventas = ventas;
	}

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

	public int getCantidad_venta() {
		return cantidad_venta;
	}

	public void setCantidad_venta(int cantidad_venta) {
		this.cantidad_venta = cantidad_venta;
	}

	public LocalDateTime getFecha_venta() {
		return fecha_venta;
	}

	public void setFecha_venta(LocalDateTime fecha_venta) {
		this.fecha_venta = fecha_venta;
	}

	public ContabilidadDiariaVo getId_registro_contabilidad_diaria() {
		return id_registro_contabilidad_diaria;
	}

	public void setId_registro_contabilidad_diaria(ContabilidadDiariaVo id_registro_contabilidad_diaria) {
		this.id_registro_contabilidad_diaria = id_registro_contabilidad_diaria;
	}



	private static final long serialVersionUID = 1L;

}
