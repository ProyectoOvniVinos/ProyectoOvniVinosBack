package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;

public interface IContabilidadDiariaDao extends JpaRepository<ContabilidadDiariaVo, Integer> {

	@Query("SELECT MAX(id_registro_contabilidad_diaria) FROM ContabilidadDiariaVo")
	public Integer findUltima();

}
