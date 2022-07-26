package com.grupo2.springboot.backend.apirest.services.venta;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.VentaVo;

public interface IVentaService {

	public List<VentaVo> findAll();
	
	public VentaVo save(VentaVo venta);
	
	public VentaVo findById(Integer idVenta);
}
