package com.grupo2.springboot.backend.apirest.services.administrador;


import java.util.Optional;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;

public interface IAdministradorService {
	
	public AdministradorVo findByCorreo(String correo);

	public AdministradorVo save(AdministradorVo adminActual);
	
	
}
