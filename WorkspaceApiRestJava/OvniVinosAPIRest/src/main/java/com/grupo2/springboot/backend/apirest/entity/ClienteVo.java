package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="cliente")
public class ClienteVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String correo_cliente;
	
	@Column(name="nombre_cliente")
	private String nombre_cliente;
	
	@Column(name="apellido_cliente")
	private String apellido_cliente;
	
	@Column(name="direccion_cliente")
	private String direccion_cliente;
	
	@Column(name="telefono_cliente")
	private String telefono_cliente;
	
	@Column(name="password_cliente")
	private String password_cliente;
	
	@JsonIgnoreProperties(value={"cliente","hibernateLazyInitializer","handler"},allowSetters = true)
	@OneToOne(fetch=FetchType.LAZY, mappedBy="cliente", cascade=CascadeType.ALL)
	private CarritoClienteVo carrito;
	
	@JsonIgnoreProperties(value={"cliente","hibernateLazyInitializer","handler"},allowSetters = true)
	@OneToMany(fetch=FetchType.LAZY, mappedBy="correo_cliente", cascade=CascadeType.ALL)
	private List<VentaVo> ventas;
	
	public CarritoClienteVo getCarrito() {
		return carrito;
	}

	public void setCarrito(CarritoClienteVo carrito) {
		this.carrito = carrito;
	}

	public List<VentaVo> getVentas() {
		return ventas;
	}

	public void setVentas(List<VentaVo> ventas) {
		this.ventas = ventas;
	}

	public String getCorreo_cliente() {
		return correo_cliente;
	}

	public void setCorreo_cliente(String correo_cliente) {
		this.correo_cliente = correo_cliente;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getApellido_cliente() {
		return apellido_cliente;
	}

	public void setApellido_cliente(String apellido_cliente) {
		this.apellido_cliente = apellido_cliente;
	}

	public String getDireccion_cliente() {
		return direccion_cliente;
	}

	public void setDireccion_cliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}

	public String getTelefono_cliente() {
		return telefono_cliente;
	}

	public void setTelefono_cliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
	}

	public String getPassword_cliente() {
		return password_cliente;
	}

	public void setPassword_cliente(String password_cliente) {
		this.password_cliente = password_cliente;
	}
	
}
