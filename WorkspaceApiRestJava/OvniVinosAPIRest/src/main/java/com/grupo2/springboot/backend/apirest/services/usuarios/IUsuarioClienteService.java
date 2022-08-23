package com.grupo2.springboot.backend.apirest.services.usuarios;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;

public interface IUsuarioClienteService {
	public ClienteVo findByCorreo(String correo);
}
