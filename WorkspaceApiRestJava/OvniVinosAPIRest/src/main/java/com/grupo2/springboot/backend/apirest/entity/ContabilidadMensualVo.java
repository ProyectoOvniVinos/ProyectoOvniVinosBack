package com.grupo2.springboot.backend.apirest.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ContabilidadMensualVo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_registro_contabilidad_mensual;
	
	@Column(name="ventas_contabilidad_mensual")
	private double ventas_contabilidad_mensual;
	
	@Column(name="egresos_contabilidad_mensual")
	private double egresos_contabilidad_mensual;
	
	@Column(name="ingresos_contabilidad_mensual")
	private double ingresos_contabilidad_mensual;
	
	@Column(name="id_registro_contabilidad_anual")
	private int id_registro_contabilidad_anual;
	
	@Column(name="fecha")
	private String fecha;

	public int getId_registro_contabilidad_mensual() {
		return id_registro_contabilidad_mensual;
	}

	public void setId_registro_contabilidad_mensual(int id_registro_contabilidad_mensual) {
		this.id_registro_contabilidad_mensual = id_registro_contabilidad_mensual;
	}

	public double getVentas_contabilidad_mensual() {
		return ventas_contabilidad_mensual;
	}

	public void setVentas_contabilidad_mensual(double ventas_contabilidad_mensual) {
		this.ventas_contabilidad_mensual = ventas_contabilidad_mensual;
	}

	public double getEgresos_contabilidad_mensual() {
		return egresos_contabilidad_mensual;
	}

	public void setEgresos_contabilidad_mensual(double egresos_contabilidad_mensual) {
		this.egresos_contabilidad_mensual = egresos_contabilidad_mensual;
	}

	public double getIngresos_contabilidad_mensual() {
		return ingresos_contabilidad_mensual;
	}

	public void setIngresos_contabilidad_mensual(double ingresos_contabilidad_mensual) {
		this.ingresos_contabilidad_mensual = ingresos_contabilidad_mensual;
	}

	public int getId_registro_contabilidad_anual() {
		return id_registro_contabilidad_anual;
	}

	public void setId_registro_contabilidad_anual(int id_registro_contabilidad_anual) {
		this.id_registro_contabilidad_anual = id_registro_contabilidad_anual;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
