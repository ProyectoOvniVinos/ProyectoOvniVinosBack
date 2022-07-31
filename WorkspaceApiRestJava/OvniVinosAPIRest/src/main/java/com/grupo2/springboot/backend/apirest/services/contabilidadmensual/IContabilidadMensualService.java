package com.grupo2.springboot.backend.apirest.services.contabilidadmensual;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;

public interface IContabilidadMensualService {

	public ContabilidadMensualVo save(ContabilidadMensualVo contabilidadMensual);
	
	public ContabilidadMensualVo update(ContabilidadMensualVo contabilidadMensual);
	
	public List<ContabilidadMensualVo> findAll();
	
	public ContabilidadMensualVo findById(Integer idContabilidadAn);
	
	public Integer findUltima();
}
