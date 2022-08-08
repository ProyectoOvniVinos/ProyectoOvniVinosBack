package com.grupo2.springboot.backend.apirest.util.service;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;

public interface IEnviosCorreo {
	
	public void enviarCorreo(ClienteVo cliente);

}
