package com.grupo2.springboot.backend.apirest.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.CompraVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;
import com.grupo2.springboot.backend.apirest.entity.VentaClienteVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.grupo2.springboot.backend.apirest.services.contabilidadanual.IContabilidadAnualService;
import com.grupo2.springboot.backend.apirest.services.contabilidaddiaria.IContabilidadDiariaService;
import com.grupo2.springboot.backend.apirest.services.contabilidadmensual.IContabilidadMensualService;
import com.grupo2.springboot.backend.apirest.services.venta.IVentaService;
import org.springframework.web.bind.annotation.PathVariable;
@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiVenta")
public class VentaRestController {

	@Autowired
	private IVentaService ventaService;
	
	@Autowired
	private IContabilidadDiariaService contabilidadDiariaService;
	
	@Autowired
	private IContabilidadMensualService contabilidadMensualService;
	
	@Autowired
	private IContabilidadAnualService contabilidadAnualService;
	
	
	//http://localhost:8080/apiVenta/ventas
	@GetMapping("/ventas")
	public ResponseEntity<?> ventas(){
		List<VentaVo> ventas = null; 
		Map<String, Object> response = new HashMap<>();
		try {
			ventas = ventaService.findAll();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println(ventas);
		
		return new ResponseEntity<List<VentaVo>>(ventas,HttpStatus.OK);
	}
	//http://localhost:8080/apiVenta/venta/id
	@GetMapping("/venta/{id}")
	public ResponseEntity<?> getVenta(@PathVariable Integer id){
		VentaVo venta = null;
		
		Map<String,Object>response = new HashMap<>();
		
		try {
			venta = ventaService.findById(id);
			if(venta==null) {
				response.put("mensaje","No se encontro la venta");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<VentaVo>(venta,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiProd/venta
	@PostMapping("/venta")
	public ResponseEntity<?> create(@RequestBody VentaVo venta){
		VentaVo ventaNew = null;
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			ClienteVo cliente = new ClienteVo();
			
			cliente.setCorreoCliente("cristian@gmail.com");
			cliente.setNombreCliente("Cristian");
			cliente.setApellidoCliente("Amador");
			cliente.setDireccionCliente("centenario");
			cliente.setTelefonoCliente("3000");
			cliente.setPasswordCliente("david");
			
			venta.setCorreo_cliente(cliente);
			
			
			String[] fechaBd=contabilidadDiariaService.findUltima().getFecha().toString().split("-");
			String[] fechaHoy = LocalDate.now().toString().split("-");
			
			if(fechaBd[0].equals(fechaHoy[0])){
				ContabilidadDiariaVo contaDia = contabilidadDiariaService.findUltima();
				
				contaDia.setVentas_contabilidad_diaria(contaDia.getVentas_contabilidad_diaria() + 1);
				contaDia.setVentas_contabilidad_diaria(contaDia.getIngresos_contabilidad_diaria() + venta.getPrecio_venta());
				contabilidadDiariaService.save(contaDia);
				actualizarContabilidadMensual(fechaHoy);
			}else {
				ContabilidadDiariaVo contaDia = new ContabilidadDiariaVo();
				
				contaDia.setVentas_contabilidad_diaria(1);
				contaDia.setVentas_contabilidad_diaria(venta.getPrecio_venta());
				contabilidadDiariaService.save(contaDia);
				actualizarContabilidadMensual(fechaHoy);
			}
			
			venta.getVentas().get(0).getId_registro_contabilidad_diaria().setId_registro_contabilidad_diaria(1);
			venta.getVentas().get(0).getId_registro_contabilidad_diaria().getId_registro_contabilidad_mensual().setId_registro_contabilidad_mensual(1);
			venta.getVentas().get(0).getId_registro_contabilidad_diaria().getId_registro_contabilidad_mensual().getId_registro_contabilidad_anual().setId_registro_contabilidad_anual(1);
			System.out.println(venta);
			ventaNew = ventaService.save(venta);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		response.put("mensaje","la venta se ha registro con exito");
		response.put("venta", ventaNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	
	}
	private void actualizarContabilidadMensual(String[] fechaHoy) {
		Double valorVentas = 0.0;
		for(ContabilidadDiariaVo contaDia : contabilidadDiariaService.findAll()) {
			if(contaDia.getFecha().toString().split("-")[1].equals(fechaHoy[1])) {	
				valorVentas = valorVentas + contaDia.getIngresos_contabilidad_diaria();
			}
		}
		
		ContabilidadMensualVo contaMensual = contabilidadMensualService.findUltima();
		contaMensual.setIngresos_contabilidad_mensual(valorVentas);
		contaMensual.setVentas_contabilidad_mensual(contaMensual.getVentas_contabilidad_mensual() + 1);
		actualizarContabilidadAnual(fechaHoy);
	}
	private void actualizarContabilidadAnual(String[] fechaHoy) {
		Double valorVentas = 0.0;
		for(ContabilidadMensualVo contaMes : contabilidadMensualService.findAll()) {
			if(contaMes.getFecha().toString().split("-")[1].equals(fechaHoy[1])) {	
				valorVentas = valorVentas + contaMes.getIngresos_contabilidad_mensual();
			}
		}
		
		ContabilidadMensualVo contaMensual = contabilidadMensualService.findUltima();
		contaMensual.setIngresos_contabilidad_mensual(valorVentas);
		contaMensual.setVentas_contabilidad_mensual(contaMensual.getVentas_contabilidad_mensual() + 1);
		
	}
	
}
