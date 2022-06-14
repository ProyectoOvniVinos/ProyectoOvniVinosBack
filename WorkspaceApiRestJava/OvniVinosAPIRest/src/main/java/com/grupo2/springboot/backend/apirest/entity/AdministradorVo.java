package com.grupo2.springboot.backend.apirest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
public class AdministradorVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	// Al no ser autogenerado no se le coloca la anotacion
	// @GeneratedValue(strategy=GenerationType=?)
	private String correo_admin;

	@Column(name = "nombre_admin")
	private String nombre_admin;

	@Column(name = "apellido_admin")
	private String apellido_admin;

	@Column(name = "direccion_admin")
	private String direccion_admin;

	@Column(name = "telefono_admin")
	private String telefono_admin;

	@Column(name = "password_admin")
	private String password_admin;

	public String getCorreoAdmin() {
		return correo_admin;
	}

	public void setCorreoAdmin(String correo_admin) {
		this.correo_admin = correo_admin;
	}

	public String getNombreAdmin() {
		return nombre_admin;
	}

	public void setNombreAdmin(String nombre_admin) {
		this.nombre_admin = nombre_admin;
	}

	public String getApellidoAdmin() {
		return apellido_admin;
	}

	public void setApellidoAdmin(String apellido_admin) {
		this.apellido_admin = apellido_admin;
	}

	public String getDireccionAdmin() {
		return direccion_admin;
	}

	public void setDireccionAdmin(String direccion_admin) {
		this.direccion_admin = direccion_admin;
	}

	public String getTelefonoAdmin() {
		return telefono_admin;
	}

	public void setTelefonoAdmin(String telefono_admin) {
		this.telefono_admin = telefono_admin;
	}

	public String getPasswordAdmin() {
		return password_admin;
	}

	public void setPasswordAdmin(String password_admin) {
		this.password_admin = password_admin;
	}


}
