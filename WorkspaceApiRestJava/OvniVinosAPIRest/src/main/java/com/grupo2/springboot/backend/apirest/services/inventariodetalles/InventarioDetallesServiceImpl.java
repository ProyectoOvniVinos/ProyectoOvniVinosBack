package com.grupo2.springboot.backend.apirest.services.inventariodetalles;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Autowired
	private IInventarioGeneralDao inventarioGeneralDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<InventarioDetallesVo> findAll() {
		
		return (List<InventarioDetallesVo>) inventarioDetallesDao.findAll();
	}
	
	@Transactional(readOnly=true)
	@Override
	public InventarioDetallesVo findById(Integer id) {
		
		return inventarioDetallesDao.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public InventarioDetallesVo save(InventarioDetallesVo inventarioRegistrar) {
		
		return inventarioDetallesDao.save(inventarioRegistrar);
	}
	
	@Transactional
	@Override
	public InventarioDetallesVo update(InventarioDetallesVo inventarioMoidficado) {
		
		return inventarioDetallesDao.save(inventarioMoidficado);
	}
	
	@Transactional
	@Override
	public void InsertarInventario(CompraVo compra) {
		
		List<CompraAdminVo> listaCompras = compra.getCompras();
		
		InventarioGeneralVo nuevoInventarioGeneral = null;
		
		
		//List<InventarioGeneralVo> inventarios = new ArrayList<>();
		
		for (CompraAdminVo compraAdminVo : listaCompras) {
			
			List<InventarioDetallesVo> detallesAnteriores = new ArrayList<>();
			
			ProductoVo producto = compraAdminVo.getCodigo_producto();
			int cantidad = compraAdminVo.getCantidad_producto();
			LocalDate fecha = LocalDate.now();
			InventarioDetallesVo nuevoInventarioDetalle = new InventarioDetallesVo();
			
			nuevoInventarioDetalle.setCantidad_producto(cantidad);
			nuevoInventarioDetalle.setFecha_ultimo_ingreso_inventario(Date.valueOf(fecha));
			
			
			nuevoInventarioGeneral = inventarioGeneralDao.findByProducto(producto.getCodigo_producto());
			

			if(nuevoInventarioGeneral != null) {
				detallesAnteriores = nuevoInventarioGeneral.getDetalles();
			}else {
				nuevoInventarioGeneral = new InventarioGeneralVo();
				nuevoInventarioGeneral.setCodigo_producto(producto);
			}
			
			 
			detallesAnteriores.add(nuevoInventarioDetalle);
			nuevoInventarioGeneral.setDetalles(detallesAnteriores);
			
			nuevoInventarioGeneral.setCantidad_producto();
			
			//inventarios.add(nuevoInventarioGeneral);
			
			
			inventarioGeneralDao.save(nuevoInventarioGeneral);
			
		}
		/*
		for(InventarioGeneralVo inventario : inventarios) {
			System.out.println(inventario);
			inventarioGeneralDao.save(inventario);
		}
		*/
	}
	
	@Transactional(readOnly=true)
	public EstadoProducto validacionCantidad(List<VentaClienteVo> detallesVenta) {
		
		List<EstadoProductoIndividual> estadoProductoIndividual = new ArrayList<>();
		
		for(VentaClienteVo venta : detallesVenta) {
			int codigoProducto = venta.getCodigo_producto().getCodigo_producto();
			
			InventarioGeneralVo comprovarInventario = inventarioGeneralDao.findByProducto(codigoProducto);
			String nombre = venta.getCodigo_producto().getNombre_producto();
			int cantidad = venta.getCantidad_producto();
			EstadoProductoIndividual estadoProductoI = new EstadoProductoIndividual(nombre, true,comprovarInventario.getCantidad_producto());
			if(cantidad>comprovarInventario.getCantidad_producto()) {
				
				estadoProductoI.setEstado(false);
			}
			estadoProductoIndividual.add(estadoProductoI);
		}
		EstadoProducto estadoProductos = new EstadoProducto(true,estadoProductoIndividual);
		estadoProductos.setEstado();
		return estadoProductos;
	}
	
	@Override
	@Transactional
	public EstadoProducto disminuirCantidad(VentaVo venta) {
		
		EstadoProducto estadoProductos = this.validacionCantidad(venta.getVentas());
		
		
		if(estadoProductos.isEstado()==true) {
			for(VentaClienteVo detallesVenta: venta.getVentas()) {
				int codigoProducto = detallesVenta.getCodigo_producto().getCodigo_producto();
				InventarioGeneralVo actualizarInventarioG = inventarioGeneralDao.findByProducto(codigoProducto);
				
				List<InventarioDetallesVo> actualizarDetalles = actualizarInventarioG.getDetalles();
				List<InventarioDetallesVo> actualizarDetalles2 = actualizarInventarioG.getDetalles();
				
				int cantidadVenta = detallesVenta.getCantidad_producto();
				int contador = 0;
				
				for(InventarioDetallesVo detalle : actualizarDetalles) {
					System.out.println(detalle);
					System.out.println("10");
					int cantidadInventario = detalle.getCantidad_producto();
					if(cantidadInventario>=cantidadVenta) {
						cantidadInventario -= cantidadVenta;
						if(cantidadInventario==0) {
							System.out.println("1");
							System.out.println(contador);
							System.out.println(actualizarDetalles.size());
							actualizarDetalles.remove(contador);
							contador = contador-1;
						
							actualizarInventarioG.setDetalles(actualizarDetalles);
							inventarioDetallesDao.deleteById(detalle.getIdDetalles());

						}else {
							System.out.println("2");
							detalle.setCantidad_producto(cantidadInventario);
							inventarioDetallesDao.save(detalle);
							break;
						}
					}else {
						cantidadVenta -= cantidadInventario;
						System.out.println(cantidadVenta+"-");
						System.out.println("3");
						actualizarDetalles.remove(contador);
						actualizarInventarioG.setDetalles(actualizarDetalles);
						inventarioDetallesDao.delete(detalle);
					}
					contador += 1;
				}
				actualizarInventarioG = inventarioGeneralDao.findByProducto(codigoProducto);
				actualizarInventarioG.setCantidad_producto();
				inventarioGeneralDao.save(actualizarInventarioG);
			}
		}
		return estadoProductos;
	}

}
