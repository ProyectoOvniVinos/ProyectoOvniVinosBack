package com.grupo2.springboot.backend.apirest.services.administrador;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IAdministradorDao;
import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;

@Service
public class AdministradorServiceImpl implements IAdministradorService{

	@Autowired
	private IAdministradorDao administradorDao;
	
	@Override
	@Transactional(readOnly = true)
	public AdministradorVo findByCorreo(String correo) {
		
		return administradorDao.findById(correo).orElse(null);
	}

	@Override
	@Transactional
	public AdministradorVo save(AdministradorVo adminActual) {
		return administradorDao.save(adminActual);
	}


}
