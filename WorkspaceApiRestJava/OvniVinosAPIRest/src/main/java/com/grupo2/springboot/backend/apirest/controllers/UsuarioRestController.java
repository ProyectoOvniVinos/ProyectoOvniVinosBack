package com.grupo2.springboot.backend.apirest.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
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
	public AdministradorVo logueo(@PathVariable String correo){
		Object obj = null;
		obj = administradorService.findByCorreo(correo);
		obj = clienteService.findById(correo);
		return administradorService.findByCorreo(correo);
	}

}
