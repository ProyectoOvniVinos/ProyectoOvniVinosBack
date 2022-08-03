package com.grupo2.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.services.administrador.IAdministradorService;
import com.grupo2.springboot.backend.apirest.services.cliente.IClienteService;

@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IAdministradorService administradorService;

	@Autowired
	private IClienteService clienteService;

	// http://localhost:8080/api/iniciar_sesion
	@GetMapping("/iniciar_sesion/{usuario}")
	public ResponseEntity<?> logueo(@RequestBody List<String> datosUser) {
		
		Map<String,Object>response = new HashMap<>();
		
		
		if(administradorService.findByCorreo(datosUser.get(0))!=null){
			if(administradorService.findByCorreo(datosUser.get(0)).getPasswordAdmin().equals(datosUser.get(1))) {
				response.put("usuario","admin");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
			}else {
				response.put("error","La password es incorrecta");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
			}
		}else {
			if(clienteService.findById(datosUser.get(0))!=null){
				if(clienteService.findById(datosUser.get(0)).getPasswordCliente().equals(datosUser.get(1))) {
					response.put("usuario","cliente");
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
				}else {
					response.put("error","La password es incorrecta");
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
				}
			}else {
				response.put("error","No se encontro el correo");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
			}
		
		}
	}

}
