package com.grupo2.springboot.backend.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.services.administrador.IAdministradorService;
import com.grupo2.springboot.backend.apirest.services.cliente.IClienteService;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IAdministradorService administradorService;

	@Autowired
	private IClienteService clienteService;

	// http://localhost:8080/api/iniciar_sesion
	@GetMapping("/iniciar_sesion")
	public Object logueo(@PathVariable String correo) {
		Object obj = null;
		obj = administradorService.findByCorreo(correo);
		if (obj != null) {
			return (AdministradorVo) obj;
		}

		obj = clienteService.findById(correo);
		if (obj != null) {
			return (ClienteVo) obj;
		}
		return null;
	}

}
