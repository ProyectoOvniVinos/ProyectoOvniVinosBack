package com.grupo2.springboot.backend.apirest.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="contabilidad_diaria")
public class ContabilidadDiariaVo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_registro_contabilidad_diaria;
	
	@Column(name="ventas_contabilidad_diaria")
	private double ventas_contabilidad_diaria;
	
	@Column(name="egresos_contabilidad_diaria")
	private double egresos_contabilidad_diaria;
	
	@Column(name="ingresos_contabilidad_diaria")
	private double ingresos_contabilidad_diaria;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_registro_contabilidad_mensual")
	private ContabilidadMensualVo id_registro_contabilidad_mensual;
	
	@Column(name="fecha")
	private String fecha;

	public int getId_registro_contabilidad_diaria() {
		return id_registro_contabilidad_diaria;
	}

	public void setId_registro_contabilidad_diaria(int id_registro_contabilidad_diaria) {
		this.id_registro_contabilidad_diaria = id_registro_contabilidad_diaria;
	}

	public double getVentas_contabilidad_diaria() {
		return ventas_contabilidad_diaria;
	}

	public void setVentas_contabilidad_diaria(double ventas_contabilidad_diaria) {
		this.ventas_contabilidad_diaria = ventas_contabilidad_diaria;
	}

	public double getEgresos_contabilidad_diaria() {
		return egresos_contabilidad_diaria;
	}

	public void setEgresos_contabilidad_diaria(double egresos_contabilidad_diaria) {
		this.egresos_contabilidad_diaria = egresos_contabilidad_diaria;
	}

	public double getIngresos_contabilidad_diaria() {
		return ingresos_contabilidad_diaria;
	}

	public void setIngresos_contabilidad_diaria(double ingresos_contabilidad_diaria) {
		this.ingresos_contabilidad_diaria = ingresos_contabilidad_diaria;
	}

	public ContabilidadMensualVo getId_registro_contabilidad_mensual() {
		return id_registro_contabilidad_mensual;
	}

	public void setId_registro_contabilidad_mensual(ContabilidadMensualVo id_registro_contabilidad_mensual) {
		this.id_registro_contabilidad_mensual = id_registro_contabilidad_mensual;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "ContabilidadDiariaVo [id_registro_contabilidad_diaria=" + id_registro_contabilidad_diaria
				+ ", ventas_contabilidad_diaria=" + ventas_contabilidad_diaria + ", egresos_contabilidad_diaria="
				+ egresos_contabilidad_diaria + ", ingresos_contabilidad_diaria=" + ingresos_contabilidad_diaria
				+ ", id_registro_contabilidad_mensual=" + id_registro_contabilidad_mensual + ", fecha=" + fecha + "]";
	}
	
	
	
}
