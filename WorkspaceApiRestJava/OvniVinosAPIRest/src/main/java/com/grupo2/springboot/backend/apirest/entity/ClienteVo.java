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

	public String getCorreoCliente() {
		return correo_cliente;
	}

	public void setCorreoCliente(String correo_cliente) {
		this.correo_cliente = correo_cliente;
	}

	public String getNombreCliente() {
		return nombre_cliente;
	}

	public void setNombreCliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getApellidoCliente() {
		return apellido_cliente;
	}

	public void setApellidoCliente(String apellido_cliente) {
		this.apellido_cliente = apellido_cliente;
	}

	public String getDireccionCliente() {
		return direccion_cliente;
	}

	public void setDireccionCliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}

	public String getTelefonoCliente() {
		return telefono_cliente;
	}

	public void setTelefonoCliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
	}

	public String getPasswordCliente() {
		return password_cliente;
	}

	public void setPasswordCliente(String password_cliente) {
		this.password_cliente = password_cliente;
	}

	@Override
	public String toString() {
		return "ClienteVo [correo_cliente=" + correo_cliente + ", nombre_cliente=" + nombre_cliente
				+ ", apellido_cliente=" + apellido_cliente + ", direccion_cliente=" + direccion_cliente
				+ ", telefono_cliente=" + telefono_cliente + ", password_cliente=" + password_cliente + "]";
	}
	
	
	
}
