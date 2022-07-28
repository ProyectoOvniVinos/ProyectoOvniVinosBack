package com.grupo2.springboot.backend.apirest.services.compra;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.CompraVo;

public interface ICompraService {
	
	public List<CompraVo> findAll();
	public CompraVo findById(Integer id);
	public CompraVo save(CompraVo compra);
}
