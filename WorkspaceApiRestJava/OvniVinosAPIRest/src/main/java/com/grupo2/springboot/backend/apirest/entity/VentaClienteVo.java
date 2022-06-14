package com.grupo2.springboot.backend.apirest.entity;

import javax.persistence.*;

@Entity
@Table(name="venta_cliente")
public class VentaClienteVo {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_puente;
	
	@Column(name="codigo_venta")
	private int codigo_venta;
	
	@Column(name="codigo_producto")
	private int codigo_producto;
	
	@Column(name="cantidad_producto")
	private int cantidad_producto;
	
	@Column(name="id_registro_contabilidad_diaria")
	private int id_registro_contabilidad_diaria;
	
}
