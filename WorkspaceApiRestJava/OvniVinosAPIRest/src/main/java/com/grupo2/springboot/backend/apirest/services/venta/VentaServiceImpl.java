package com.grupo2.springboot.backend.apirest.services.venta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.grupo2.springboot.backend.apirest.services.contabilidadanual.IContabilidadAnualService;
import com.grupo2.springboot.backend.apirest.services.contabilidaddiaria.IContabilidadDiariaService;
import com.grupo2.springboot.backend.apirest.services.contabilidadmensual.IContabilidadMensualService;
import com.grupo2.springboot.backend.apirest.services.producto.IProductoService;
import com.grupo2.springboot.backend.apirest.dao.IVentaDao;
@Service
public class VentaServiceImpl implements IVentaService{

	@Autowired
	private IVentaDao ventaDao;
	
	@Autowired
	private IContabilidadDiariaService contabilidadDiariaService;

	@Autowired
	private IContabilidadMensualService contabilidadMensualService;

	@Autowired
	private IContabilidadAnualService contabilidadAnualService;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<VentaVo> findAll() {
		return (List<VentaVo>) ventaDao.findAll();
	}

	@Override
	@Transactional
	public VentaVo save(VentaVo venta) {
		return ventaDao.save(venta);
	}

	@Override
	public VentaVo findById(Integer idVenta) {
		return ventaDao.findById(idVenta).orElse(null);
	}

	@Override
	public void asignarContabilidadDiaHoy(ContabilidadDiariaVo ultimaD, VentaVo ventaNew, VentaVo venta) {
		
		Integer contaMensualId = contabilidadMensualService.findUltima();
		ContabilidadMensualVo contaMensual = contabilidadMensualService.findById(contaMensualId);
		

		contaMensual.setIngresos_contabilidad_mensual(contaMensual.getIngresos_contabilidad_mensual() + venta.getPrecio_venta());
		contaMensual.setVentas_contabilidad_mensual(contaMensual.getVentas_contabilidad_mensual() + 1);
		contabilidadMensualService.save(contaMensual);

		Integer contaAnualId = contabilidadAnualService.findUltima();
		ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

		contaAnual.setIngresos_contabilidad_anual(contaAnual.getIngresos_contabilidad_anual() + venta.getPrecio_venta());
		contaAnual.setVentas_contabilidad_anual(contaAnual.getVentas_contabilidad_anual() + 1);
		contabilidadAnualService.save(contaAnual);

		ultimaD.setVentas_contabilidad_diaria(ultimaD.getVentas_contabilidad_diaria() + 1);
		ultimaD.setIngresos_contabilidad_diaria(ultimaD.getIngresos_contabilidad_diaria() + venta.getPrecio_venta());
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(ultimaD);
		
		
		ventaNew.setId_registro_contabilidad_diaria(contaDiaGu);
	}

	@Override
	public void asignarContabilidadMesMalo(VentaVo ventaNew, VentaVo venta) {
		Integer contaMensualId = contabilidadMensualService.findUltima();
		ContabilidadMensualVo contaMensual = contabilidadMensualService.findById(contaMensualId);

		contaMensual.setIngresos_contabilidad_mensual(contaMensual.getIngresos_contabilidad_mensual() + venta.getPrecio_venta());
		contaMensual.setVentas_contabilidad_mensual(contaMensual.getVentas_contabilidad_mensual() + 1);
		contabilidadMensualService.save(contaMensual);

		Integer contaAnualId = contabilidadAnualService.findUltima();
		ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

		contaAnual.setIngresos_contabilidad_anual(contaAnual.getIngresos_contabilidad_anual() + venta.getPrecio_venta());
		contaAnual.setVentas_contabilidad_anual(contaAnual.getVentas_contabilidad_anual() + 1);
		contabilidadAnualService.save(contaAnual);

		ContabilidadDiariaVo contaHoy = new ContabilidadDiariaVo();
		contaHoy.setIngresos_contabilidad_diaria(venta.getPrecio_venta());
		contaHoy.setVentas_contabilidad_diaria(1);
		contaHoy.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaHoy.setId_registro_contabilidad_mensual(contaMensual);
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(contaHoy);
		
		ventaNew.setId_registro_contabilidad_diaria(contaDiaGu);
	}

	@Override
	public void asignarContabilidadDiaMalo(VentaVo ventaNew, VentaVo venta) {
		Integer contaAnualId = contabilidadAnualService.findUltima();
		ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

		contaAnual.setIngresos_contabilidad_anual(contaAnual.getIngresos_contabilidad_anual() + venta.getPrecio_venta());
		contaAnual.setVentas_contabilidad_anual(contaAnual.getVentas_contabilidad_anual() + 1);
		ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

		ContabilidadMensualVo contaMensual = new ContabilidadMensualVo();
		contaMensual.setIngresos_contabilidad_mensual(venta.getPrecio_venta());
		contaMensual.setVentas_contabilidad_mensual(1);
		contaMensual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaMensual.setId_registro_contabilidad_anual(contaAnualGu);
		ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

		ContabilidadDiariaVo contaDiaria = new ContabilidadDiariaVo();
		contaDiaria.setIngresos_contabilidad_diaria(venta.getPrecio_venta());
		contaDiaria.setVentas_contabilidad_diaria(1);
		contaDiaria.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaDiaria.setId_registro_contabilidad_mensual(contaMensualGu);
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(contaDiaria);
		
		ventaNew.setId_registro_contabilidad_diaria(contaDiaGu);
	}

	@Override
	public void asignarContabilidadCrearTodo(VentaVo ventaNew, VentaVo venta) {
		ContabilidadAnualVo contaAnual = new ContabilidadAnualVo();
		contaAnual.setIngresos_contabilidad_anual(venta.getPrecio_venta());
		contaAnual.setVentas_contabilidad_anual(1);
		contaAnual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

		ContabilidadMensualVo contaMensual = new ContabilidadMensualVo();
		contaMensual.setIngresos_contabilidad_mensual(venta.getPrecio_venta());
		contaMensual.setVentas_contabilidad_mensual(1);
		contaMensual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaMensual.setId_registro_contabilidad_anual(contaAnualGu);
		ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

		ContabilidadDiariaVo contaDiaria = new ContabilidadDiariaVo();
		contaDiaria.setIngresos_contabilidad_diaria(venta.getPrecio_venta());
		contaDiaria.setVentas_contabilidad_diaria(1);
		contaDiaria.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaDiaria.setId_registro_contabilidad_mensual(contaMensualGu);
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(contaDiaria);
		
		ventaNew.setId_registro_contabilidad_diaria(contaDiaGu);
	}

	@Override
	public void gestorAsignarContabilidad(VentaVo ventaNew, VentaVo venta) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
		
		Integer ultimaDId = contabilidadDiariaService.findUltima();
		if(ultimaDId==null) {
			ultimaDId = 0;
		}
		
		ContabilidadDiariaVo ultimaD = contabilidadDiariaService.findById(ultimaDId);
		if (dtf.format(LocalDateTime.now()).split("-")[0].equals(ventaNew.getFecha_venta().toString().split("-")[0]) && dtf.format(LocalDateTime.now()).split("-")[1].equals(ventaNew.getFecha_venta().toString().split("-")[1]) && dtf.format(LocalDateTime.now()).split("-")[2].equals(ventaNew.getFecha_venta().toString().split("-")[2].split("T")[0])) {
			
			this.asignarContabilidadDiaHoy(ultimaD, ventaNew, venta);
		} else {
			if (ventaNew.getFecha_venta().toString().split("-")[0].equals(LocalDate.now().toString().split("-")[0])) {
				if (ventaNew.getFecha_venta().toString().split("-")[1].equals(LocalDate.now().toString().split("-")[1])) {
					this.asignarContabilidadMesMalo(ventaNew, venta);
					
				} else {
					this.asignarContabilidadDiaMalo(ventaNew,venta);
				}
			} else {
				this.asignarContabilidadCrearTodo(ventaNew,venta);
			}
		}
	}


}
