package com.grupo2.springboot.backend.apirest.services.contabilidadanual;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;

public interface IContabilidadAnualService {
	
	public ContabilidadAnualVo save(ContabilidadAnualVo contabilidadAnual);
	
	public ContabilidadAnualVo update(ContabilidadAnualVo contabilidadAnual);
	
	public List<ContabilidadAnualVo> findAll();
	
	public ContabilidadAnualVo findById(Integer idContabilidadAn);
	
	public Integer findUltima();
}
