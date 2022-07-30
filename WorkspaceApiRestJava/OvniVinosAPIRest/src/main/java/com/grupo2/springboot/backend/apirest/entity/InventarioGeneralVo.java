package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="inventario_general")
public class InventarioGeneralVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_registro; 
	
	@Column(name="cantidad_producto")
	private int cantidad_producto;
	
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"},allowSetters=true)
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="codigo_producto")
	private ProductoVo codigo_producto;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_registro")
	private List<InventarioDetallesVo> detalles;

	public int getId_registro() {
		return id_registro;
	}

	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}


	public int getCantidad_producto() {
		return cantidad_producto;
	}

	public void setCantidad_producto() {
		int cantidadTotal = 0;
		if(this.detalles.size()>0) {
			for (InventarioDetallesVo inventarioDetallesVo : this.detalles) {
				cantidadTotal += inventarioDetallesVo.getCantidad_producto();
			}
		}
		this.cantidad_producto = cantidadTotal;
	}

	public List<InventarioDetallesVo> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<InventarioDetallesVo> detalles) {
		this.detalles = detalles;
	}

	public ProductoVo getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(ProductoVo codigo_producto) {
		this.codigo_producto = codigo_producto;
	}
	
}
