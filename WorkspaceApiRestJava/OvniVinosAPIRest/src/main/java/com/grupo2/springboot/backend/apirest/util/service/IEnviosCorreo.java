package com.grupo2.springboot.backend.apirest.util.service;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;

public interface IEnviosCorreo {
	
	public void enviarCorreo(ClienteVo cliente, VentaVo venta);

}