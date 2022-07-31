package com.grupo2.springboot.backend.apirest.services.contabilidaddiaria;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;

public interface IContabilidadDiariaService {

	public ContabilidadDiariaVo save(ContabilidadDiariaVo contabilidadDiaria);
	
	public ContabilidadDiariaVo update(ContabilidadDiariaVo contabilidadDiaria);
	
	public List<ContabilidadDiariaVo> findAll();
	
	public ContabilidadDiariaVo findById(Integer idContabilidadDia);
	
	public Integer findUltima();
}
