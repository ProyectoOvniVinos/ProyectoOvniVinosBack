package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd-HH:mm:ss")
	private LocalDateTime fecha_compra;

	@JsonIgnoreProperties(value = {"compras", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "correo_admin")
	private AdministradorVo correo_admin;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_registro_contabilidad_diaria_compra")
	private ContabilidadDiariaVo id_registro_contabilidad_diaria;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
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

	public void setPrecio_compra() {
		
		double precio = 0;
		
		for(CompraAdminVo detallesCompra : this.getCompras()) {
			detallesCompra.setPrecio_compra_detalle();
			precio += detallesCompra.getPrecio_compra_detalle();
		}
		this.precio_compra = precio;
	}

	public int getCantidad_compra() {
		return cantidad_compra;
	}

	public void setCantidad_compra() {
		
		int cantidad = 0;
		for(CompraAdminVo detallesCompra : this.getCompras()) {
			cantidad += detallesCompra.getCantidad_producto();
		}
		
		this.cantidad_compra = cantidad;
	}

	public LocalDateTime getFecha_compra() {
		return fecha_compra;
	}

	public void setFecha_compra(LocalDateTime fecha_compra) {
		this.fecha_compra = fecha_compra;
	}

	public AdministradorVo getCorreo_admin() {
		return correo_admin;
	}

	public void setCorreo_admin(AdministradorVo correo_admin) {
		this.correo_admin = correo_admin;
	}

	public ContabilidadDiariaVo getId_registro_contabilidad_diaria() {
		return id_registro_contabilidad_diaria;
	}

	public void setId_registro_contabilidad_diaria(ContabilidadDiariaVo id_registro_contabilidad_diaria) {
		this.id_registro_contabilidad_diaria = id_registro_contabilidad_diaria;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "CompraVo [codigo_compra=" + codigo_compra + ", precio_compra=" + precio_compra + ", cantidad_compra="
				+ cantidad_compra + ", fecha_compra=" + fecha_compra + ", correo_admin=" + correo_admin
				+ ", id_registro_contabilidad_diaria=" + id_registro_contabilidad_diaria + ", compras=" + compras + "]";
	}

	

}
