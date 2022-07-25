package com.grupo2.springboot.backend.apirest.services.cliente;

import java.util.Optional;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;

public interface IClienteService {

	public ClienteVo findByCorreo(String correo);
	
	
	public ClienteVo save(ClienteVo cliente);
}
