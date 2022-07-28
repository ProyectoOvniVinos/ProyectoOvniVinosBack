package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "compra")
public class CompraVo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_compra;

	@Column(name = "precio_compra")
	private double precio_compra;

	@Column(name = "cantidad_compra")
	private int cantidad_compra;

	@Column(name = "fecha_compra")
	private Date fecha_compra;

	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "correo_admin")
	private AdministradorVo correo_admin;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "compra")
	private List<CompraAdminVo> compras;

	public List<CompraAdminVo> getCompras() {
		return compras;
	}

	public void setCompras(List<CompraAdminVo> compras) {
		this.compras = compras;
	}

	public int getCodigo_compra() {
		return codigo_compra;
	}

	public void setCodigo_compra(int codigo_compra) {
		this.codigo_compra = codigo_compra;
	}

	public double getPrecio_compra() {
		return precio_compra;
	}

	public void setPrecio_compra(double precio_compra) {
		this.precio_compra = precio_compra;
	}

	public int getCantidad_compra() {
		return cantidad_compra;
	}

	public void setCantidad_compra(int cantidad_compra) {
		this.cantidad_compra = cantidad_compra;
	}

	public Date getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(Date fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public AdministradorVo getCorreo_admin() {
		return correo_admin;
	}

	public void setCorreo_admin(AdministradorVo correo_admin) {
		this.correo_admin = correo_admin;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "CompraVo [codigo_compra=" + codigo_compra + ", precio_compra=" + precio_compra + ", cantidad_compra="
				+ cantidad_compra + ", fecha_compra=" + fecha_compra + ", correo_admin=" + correo_admin + ", compras="
				+ compras + "]";
	}

}
