package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;

public interface IContabilidadDiariaDao extends CrudRepository<ContabilidadDiariaVo, Integer> {

	@Query("SELECT MAX(id_registro_contabilidad_diaria) FROM ContabilidadDiariaVo")
	public Integer findUltima();

}
