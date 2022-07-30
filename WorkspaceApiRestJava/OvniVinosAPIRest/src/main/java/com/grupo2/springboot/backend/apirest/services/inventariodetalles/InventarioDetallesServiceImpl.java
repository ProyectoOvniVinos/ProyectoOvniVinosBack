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
import com.grupo2.springboot.backend.apirest.entity.VentaClienteVo;
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
	
	@Override
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
	
	public boolean validacionCantidad(List<VentaClienteVo> detallesVenta) {
		
		for(VentaClienteVo venta : detallesVenta) {
			int codigoProducto = venta.getCodigo_producto().getCodigo_producto();
			InventarioGeneralVo comprovarIncentario = inventarioGeneralDao.findByProducto(codigoProducto);
			if(venta.getCantidad_producto()>comprovarIncentario.getCantidad_producto()) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean disminuirCantidad(VentaVo venta) {
		
		boolean realizarVenta = this.validacionCantidad(venta.getVentas());
		
		if(realizarVenta==true) {
			for(VentaClienteVo detallesVenta: venta.getVentas()) {
				int codigoProducto = detallesVenta.getCodigo_producto().getCodigo_producto();
				InventarioGeneralVo actualizarInventarioG = inventarioGeneralDao.findByProducto(codigoProducto);
				
				List<InventarioDetallesVo> actualizarDetalles = actualizarInventarioG.getDetalles();
				
				int cantidadVenta = detallesVenta.getCantidad_producto();
				int contador = 0;
				
				for(InventarioDetallesVo detalle : actualizarDetalles) {
					int cantidadInventario = detalle.getCantidad_producto();
					
					if(cantidadInventario>=cantidadVenta) {
						
						cantidadInventario -= cantidadVenta;
						if(cantidadInventario==0) {
							inventarioDetallesDao.delete(detalle);
						}else {
							detalle.setCantidad_producto(cantidadInventario);
							inventarioDetallesDao.save(detalle);
						}
						
						break;
						
					}else {
						cantidadVenta -= cantidadInventario;
						inventarioDetallesDao.delete(detalle);
					}
					contador += 1;
				}
				actualizarInventarioG = inventarioGeneralDao.findByProducto(codigoProducto);
				actualizarInventarioG.setCantidad_producto();
				inventarioGeneralDao.save(actualizarInventarioG);
			}
		}
		return realizarVenta;
	}

}
