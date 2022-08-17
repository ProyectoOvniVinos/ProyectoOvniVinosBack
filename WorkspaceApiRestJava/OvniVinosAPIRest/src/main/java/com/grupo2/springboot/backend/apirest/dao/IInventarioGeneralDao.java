package com.grupo2.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.InventarioGeneralVo;

public interface IInventarioGeneralDao extends CrudRepository<InventarioGeneralVo, Integer>{
	
	@Query("select inventario from InventarioGeneralVo inventario where inventario.codigoProducto.codigoProducto = ?1")
	public InventarioGeneralVo findByProducto(Integer term);
	
	@Query("select inventario from InventarioGeneralVo inventario ORDER BY inventario.cantidadProducto asc")
	public List<InventarioGeneralVo> findByCantidad();
	
	@Query("select inventario from InventarioGeneralVo inventario where inventario.cantidadProducto > 0 and inventario.codigoProducto.estado = 1")
	public List<InventarioGeneralVo> findByCantidadPositiva();
	
	@Query("select inventario from InventarioGeneralVo inventario where inventario.cantidadProducto > 0 and inventario.codigoProducto.estado = 1 and  inventario.codigoProducto.descripcionProducto like %?1%")
	public List<InventarioGeneralVo> findByCantidadPositivaFiltrado(String term);
	
}
