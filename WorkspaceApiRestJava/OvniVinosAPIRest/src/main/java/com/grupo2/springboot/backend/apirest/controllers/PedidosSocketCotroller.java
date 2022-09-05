package com.grupo2.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.grupo2.springboot.backend.apirest.entity.PedidoVo;
import com.grupo2.springboot.backend.apirest.services.pedido.IPedidoService;

@Controller
public class PedidosSocketCotroller {
	
	@Autowired
	private IPedidoService pedidoService;
	
	@MessageMapping("/alerta")
	@SendTo("/topic/alerta")
	public List<PedidoVo> pedidoPendientes(String entro){
		System.out.println(entro);
		return pedidoService.findByPendientes();
		
	}

}
