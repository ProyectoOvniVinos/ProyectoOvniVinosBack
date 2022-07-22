package com.grupo2.springboot.backend.apirest.services.cliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IAdministradorDao;
import com.grupo2.springboot.backend.apirest.dao.IClienteDao;
import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;

public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<ClienteVo> findByCorreo(String correo) {
		
		return clienteDao.findById(correo);
	}
}
