package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;

public interface IContabilidadAnualDao extends CrudRepository<ContabilidadAnualVo, Integer>{
	/*
	@Query("SELECT MAX(id_registro_contabilidad_anual) FROM contabilidad_anual")
	public ContabilidadAnualVo findUltima();
	*/
}
