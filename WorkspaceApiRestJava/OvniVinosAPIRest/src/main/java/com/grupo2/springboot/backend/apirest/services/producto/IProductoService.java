package com.grupo2.springboot.backend.apirest.services.producto;

import java.util.List;
import java.util.Optional;

import com.grupo2.springboot.backend.apirest.entity.ProductoVo;

public interface IProductoService {

	public List<ProductoVo> findAll();
	
	public ProductoVo findByCodigo_producto(int codigoP);
	
	public ProductoVo save(ProductoVo producto);
	
	public void delete(ProductoVo producto);
}
