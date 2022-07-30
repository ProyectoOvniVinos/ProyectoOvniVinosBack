package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.InventarioGeneralVo;

public interface IInventarioGeneralDao extends CrudRepository<InventarioGeneralVo, Integer>{
	@Query("select inventario from InventarioGeneralVo inventario where inventario.codigo_producto = ?1")
	public InventarioGeneralVo findByProducto(Integer term);
}
