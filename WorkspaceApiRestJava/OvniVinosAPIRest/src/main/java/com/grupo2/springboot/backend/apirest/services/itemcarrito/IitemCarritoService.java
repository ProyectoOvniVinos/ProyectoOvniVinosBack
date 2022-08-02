package com.grupo2.springboot.backend.apirest.services.itemcarrito;

import java.util.List;

import com.grupo2.springboot.backend.apirest.entity.ItemCarritoVo;

public interface IitemCarritoService {

	public void delete(ItemCarritoVo itemCarrito);

	public List<ItemCarritoVo> findByCarrito(Integer term);
}
