package com.grupo2.springboot.backend.apirest.entity;

import javax.persistence.*;

@Entity
@Table(name="contabilidad_anual")
public class ContabilidadAnualVo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_registro_contabilidad_anual;
	
	@Column(name="ventas_contabilidad_anual")
	private double ventas_contabilidad_anual;
	
	@Column(name="egresos_contabilidad_anual")
	private double egresos_contabilidad_anual;
	
	@Column(name="ingresos_contabilidad_anual")
	private double ingresos_contabilidad_anual;
	
	@Column(name="fecha")
	private String fecha;

	public int getId_registro_contabilidad_anual() {
		return id_registro_contabilidad_anual;
	}

	public void setId_registro_contabilidad_anual(int id_registro_contabilidad_anual) {
		this.id_registro_contabilidad_anual = id_registro_contabilidad_anual;
	}

	public double getVentas_contabilidad_anual() {
		return ventas_contabilidad_anual;
	}

	public void setVentas_contabilidad_anual(double ventas_contabilidad_anual) {
		this.ventas_contabilidad_anual = ventas_contabilidad_anual;
	}

	public double getEgresos_contabilidad_anual() {
		return egresos_contabilidad_anual;
	}

	public void setEgresos_contabilidad_anual(double egresos_contabilidad_anual) {
		this.egresos_contabilidad_anual = egresos_contabilidad_anual;
	}

	public double getIngresos_contabilidad_anual() {
		return ingresos_contabilidad_anual;
	}

	public void setIngresos_contabilidad_anual(double ingresos_contabilidad_anual) {
		this.ingresos_contabilidad_anual = ingresos_contabilidad_anual;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "ContabilidadAnualVo [id_registro_contabilidad_anual=" + id_registro_contabilidad_anual
				+ ", ventas_contabilidad_anual=" + ventas_contabilidad_anual + ", egresos_contabilidad_anual="
				+ egresos_contabilidad_anual + ", ingresos_contabilidad_anual=" + ingresos_contabilidad_anual
				+ ", fecha=" + fecha + "]";
	}
	
	
	
}
