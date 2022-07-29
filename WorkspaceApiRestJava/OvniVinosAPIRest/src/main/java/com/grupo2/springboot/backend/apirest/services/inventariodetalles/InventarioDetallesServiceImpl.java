package com.grupo2.springboot.backend.apirest.services.inventariodetalles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.springboot.backend.apirest.dao.IInventarioDetallesDao;
import com.grupo2.springboot.backend.apirest.entity.CompraVo;
import com.grupo2.springboot.backend.apirest.entity.InventarioDetallesVo;
import com.grupo2.springboot.backend.apirest.entity.InventarioGeneralVo;
import com.grupo2.springboot.backend.apirest.entity.ProductoVo;

@Service
public class InventarioDetallesServiceImpl implements IinventarioDetallesService{
	
	@Autowired
	private IInventarioDetallesDao inventarioDetallesDao;
	
	@Override
	public List<InventarioDetallesVo> findAll() {
		
		return (List<InventarioDetallesVo>) inventarioDetallesDao.findAll();
	}

	@Override
	public InventarioDetallesVo findById(Integer id) {
		
		return inventarioDetallesDao.findById(id).orElse(null);
	}

	@Override
	public InventarioDetallesVo save(InventarioDetallesVo inventarioRegistrar) {
		
		return inventarioDetallesDao.save(inventarioRegistrar);
	}

	@Override
	public InventarioDetallesVo update(InventarioDetallesVo inventarioMoidficado) {
		
		return inventarioDetallesDao.save(inventarioMoidficado);
	}
	
	public void InsertarInventario(CompraVo compra) {
		
		ProductoVo producto = compra.getCompras().get(0).getCodigo_producto();
		int cantidad = compra.getCompras().get(0).getCantidad_producto();
		 
		InventarioDetallesVo nuevoInventarioDetalle = new InventarioDetallesVo();
		nuevoInventarioDetalle.setCantidad_producto(cantidad);
		nuevoInventarioDetalle.setCodigo_producto(producto);
		InventarioGeneralVo nuevoInventarioGeneral = new InventarioGeneralVo();
		nuevoInventarioGeneral.setCantidad_producto(9);
		
	}

}
