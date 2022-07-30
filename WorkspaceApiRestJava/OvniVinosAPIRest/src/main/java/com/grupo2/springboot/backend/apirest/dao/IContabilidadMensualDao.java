package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;

public interface IContabilidadMensualDao extends CrudRepository<ContabilidadMensualVo, Integer>{

	@Query("SELECT MAX(id_registro_contabilidad_mensual) FROM ContabilidadMensualVo")
	public Integer findUltima();
	
}
