package com.grupo2.springboot.backend.apirest.controllers;

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
import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.grupo2.springboot.backend.apirest.services.venta.IVentaService;
import org.springframework.web.bind.annotation.PathVariable;
@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiVenta")
public class VentaRestController {

	@Autowired
	private IVentaService ventaService;
	
	//http://localhost:8080/apiVenta/ventas
	@GetMapping("/ventas")
	public ResponseEntity<?> compras(){
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
	//http://localhost:8080/apiVenta/ventas/id
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
	
}
