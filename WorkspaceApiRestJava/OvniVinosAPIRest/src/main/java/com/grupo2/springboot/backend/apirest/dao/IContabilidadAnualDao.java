package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;


public interface IContabilidadAnualDao extends JpaRepository<ContabilidadAnualVo, Integer> {

	@Query("SELECT MAX(id_registro_contabilidad_anual) FROM ContabilidadAnualVo")
	public Integer findUltima();
}
