package com.grupo2.springboot.backend.apirest.services.inventariodetalles;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.springboot.backend.apirest.dao.IInventarioDetallesDao;
import com.grupo2.springboot.backend.apirest.dao.IInventarioGeneralDao;
import com.grupo2.springboot.backend.apirest.entity.CompraAdminVo;
import com.grupo2.springboot.backend.apirest.entity.CompraVo;
import com.grupo2.springboot.backend.apirest.entity.InventarioDetallesVo;
import com.grupo2.springboot.backend.apirest.entity.InventarioGeneralVo;
import com.grupo2.springboot.backend.apirest.entity.ProductoVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;

@Service
public class InventarioDetallesServiceImpl implements IinventarioDetallesService{
	
	@Autowired
	private IInventarioDetallesDao inventarioDetallesDao;
	
	private IInventarioGeneralDao inventarioGeneralDao;
	
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
		
		List<CompraAdminVo> listaCompras = compra.getCompras();
		
		InventarioGeneralVo nuevoInventarioGeneral = null;
		
		List<InventarioDetallesVo> detallesAnteriores = new ArrayList<>();
		
		for (CompraAdminVo compraAdminVo : listaCompras) {
			
			ProductoVo producto = compraAdminVo.getCodigo_producto();
			int cantidad = compraAdminVo.getCantidad_producto();
			Date fecha = compra.getFecha_compra();
			 
			InventarioDetallesVo nuevoInventarioDetalle = new InventarioDetallesVo();
			
			nuevoInventarioDetalle.setCantidad_producto(cantidad);
			nuevoInventarioDetalle.setFecha_ultimo_ingreso_inventario(fecha);
			
			nuevoInventarioGeneral = inventarioGeneralDao.findByProducto(producto.getCodigo_producto());
			
			if(nuevoInventarioGeneral != null) {
				detallesAnteriores = nuevoInventarioGeneral.getDetalles();
			}else {
				nuevoInventarioGeneral.setCodigo_producto(producto);
			}
			
			 
			detallesAnteriores.add(nuevoInventarioDetalle);
			nuevoInventarioGeneral.setDetalles(detallesAnteriores);
			
			nuevoInventarioGeneral.setCantidad_producto();
			
			inventarioGeneralDao.save(nuevoInventarioGeneral);
			
			detallesAnteriores.clear();
			
		}
		
	}
	
	public void disminuirCantidad(VentaVo venta) {
		
		venta.getVentas().get(0).getCodigo_producto();
		
	}

}
