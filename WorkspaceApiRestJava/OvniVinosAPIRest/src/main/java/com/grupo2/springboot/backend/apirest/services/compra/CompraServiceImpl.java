package com.grupo2.springboot.backend.apirest.services.compra;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.springboot.backend.apirest.dao.ICompraDao;
import com.grupo2.springboot.backend.apirest.entity.CompraVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;
import com.grupo2.springboot.backend.apirest.services.contabilidadanual.IContabilidadAnualService;
import com.grupo2.springboot.backend.apirest.services.contabilidaddiaria.IContabilidadDiariaService;
import com.grupo2.springboot.backend.apirest.services.contabilidadmensual.IContabilidadMensualService;

@Service
public class CompraServiceImpl implements ICompraService{
	
	@Autowired
	private ICompraDao compraDao;
	
	@Autowired
	private IContabilidadDiariaService contabilidadDiariaService;

	@Autowired
	private IContabilidadMensualService contabilidadMensualService;

	@Autowired
	private IContabilidadAnualService contabilidadAnualService;

	@Override
	public List<CompraVo> findAll() {
		return (List<CompraVo>) compraDao.findAll();
	}

	@Override
	public CompraVo findById(Integer id) {
		return compraDao.findById(id).orElse(null);
	}

	@Override
	public CompraVo save(CompraVo compra) {
		return compraDao.save(compra);
	}

	@Override
	public void asignarContabilidadDiaHoy(ContabilidadDiariaVo ultimaD, CompraVo compraNew, CompraVo compra) {
		Integer contaMensualId = contabilidadMensualService.findUltima();
		ContabilidadMensualVo contaMensual = contabilidadMensualService.findById(contaMensualId);
		
		contaMensual.setEgresos_contabilidad_mensual(contaMensual.getEgresos_contabilidad_mensual()+compra.getPrecio_compra());
		ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

		Integer contaAnualId = contabilidadAnualService.findUltima();
		ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

		contaAnual.setEgresos_contabilidad_anual(contaAnual.getEgresos_contabilidad_anual() + compra.getPrecio_compra());
		ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

		ultimaD.setEgresos_contabilidad_diaria(ultimaD.getEgresos_contabilidad_diaria() + compra.getPrecio_compra());
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(ultimaD);
		
		compra.setId_registro_contabilidad_diaria(contaDiaGu);
		
	}

	

	@Override
	public void asignarContabilidadMesMalo(CompraVo compraNew, CompraVo compra) {
		Integer contaMensualId = contabilidadMensualService.findUltima();
		ContabilidadMensualVo contaMensual = contabilidadMensualService.findById(contaMensualId);

		contaMensual.setEgresos_contabilidad_mensual(
				contaMensual.getEgresos_contabilidad_mensual() + compra.getPrecio_compra());
		ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

		Integer contaAnualId = contabilidadAnualService.findUltima();
		ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

		contaAnual.setEgresos_contabilidad_anual(
				contaAnual.getEgresos_contabilidad_anual() + compra.getPrecio_compra());
		ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

		ContabilidadDiariaVo contaHoy = new ContabilidadDiariaVo();
		contaHoy.setEgresos_contabilidad_diaria(compra.getPrecio_compra());
		contaHoy.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaHoy.setId_registro_contabilidad_mensual(contaMensual);
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(contaHoy);
		
		compra.setId_registro_contabilidad_diaria(contaDiaGu);
		
	}

	@Override
	public void asignarContabilidadDiaMalo(CompraVo compraNew, CompraVo compra) {
		Integer contaAnualId = contabilidadAnualService.findUltima();
		ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

		contaAnual.setEgresos_contabilidad_anual(
				contaAnual.getEgresos_contabilidad_anual() + compra.getPrecio_compra());
		ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

		ContabilidadMensualVo contaMensual = new ContabilidadMensualVo();
		contaMensual.setEgresos_contabilidad_mensual(compra.getPrecio_compra());
		contaMensual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaMensual.setId_registro_contabilidad_anual(contaAnualGu);
		ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

		ContabilidadDiariaVo contaDiaria = new ContabilidadDiariaVo();
		contaDiaria.setEgresos_contabilidad_diaria(compra.getPrecio_compra());
		contaDiaria.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaDiaria.setId_registro_contabilidad_mensual(contaMensualGu);
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(contaDiaria);
		
		compra.setId_registro_contabilidad_diaria(contaDiaGu);
		
	}

	@Override
	public void asignarContabilidadCrearTodo(CompraVo compraNew, CompraVo compra) {
		ContabilidadAnualVo contaAnual = new ContabilidadAnualVo();
		contaAnual.setEgresos_contabilidad_anual(compra.getPrecio_compra());
		contaAnual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

		ContabilidadMensualVo contaMensual = new ContabilidadMensualVo();
		contaMensual.setEgresos_contabilidad_mensual(compra.getPrecio_compra());
		contaMensual.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaMensual.setId_registro_contabilidad_anual(contaAnualGu);
		ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

		ContabilidadDiariaVo contaDiaria = new ContabilidadDiariaVo();
		contaDiaria.setEgresos_contabilidad_diaria(compra.getPrecio_compra());
		contaDiaria.setFecha(java.sql.Date.valueOf(LocalDate.now()));
		contaDiaria.setId_registro_contabilidad_mensual(contaMensualGu);
		ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(contaDiaria);
		
		compra.setId_registro_contabilidad_diaria(contaDiaGu);
		
	}
	
	@Override
	public void gestorAsignarContabilidad(CompraVo compraNew, CompraVo compra) {
		Integer ultimaDId = contabilidadDiariaService.findUltima();
		if(ultimaDId==null) {
			ultimaDId = 0;
		}
		ContabilidadDiariaVo ultimaD = contabilidadDiariaService.findById(ultimaDId);
		
		if (ultimaD != null) {
			if (ultimaD.getFecha().toString().split("-")[0].equals(compra.getFecha_compra().toString().split("-")[0]) && ultimaD.getFecha().toString().split("-")[1].equals(compra.getFecha_compra().toString().split("-")[1]) && ultimaD.getFecha().toString().split("-")[2].equals(compra.getFecha_compra().toString().split("-")[2].split("T")[0])) {
				System.out.println("AAAAAAAAa");
				
				this.asignarContabilidadDiaHoy(ultimaD, compraNew, compra);
			} else {
				if (compra.getFecha_compra().toString().split("-")[0].equals(ultimaD.getFecha().toString().split("-")[0])) {
					if (compra.getFecha_compra().toString().split("-")[1].equals(ultimaD.getFecha().toString().split("-")[1])) {
						
						this.asignarContabilidadMesMalo(compraNew, compra);
					} else {
						this.asignarContabilidadDiaMalo(compraNew,compra);
					}
				} else {
					this.asignarContabilidadCrearTodo(compraNew,compra);
				}
			}
		} else if(ultimaD==null){
			this.asignarContabilidadCrearTodo(compraNew,compra);

		}
		
	}
	

}
