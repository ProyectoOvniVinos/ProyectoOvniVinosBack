package com.grupo2.springboot.backend.apirest.services.usuarios;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;

public interface IUsuarioAdminService {
	public AdministradorVo findByCorreo(String correo);
}
