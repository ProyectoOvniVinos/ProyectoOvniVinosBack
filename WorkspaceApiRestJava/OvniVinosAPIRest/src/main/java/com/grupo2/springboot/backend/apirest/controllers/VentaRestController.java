package com.grupo2.springboot.backend.apirest.controllers;

import java.sql.Date;
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

@CrossOrigin(origins = { "http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089" })
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

	// http://localhost:8080/apiVenta/ventas
	@GetMapping("/ventas")
	public ResponseEntity<?> ventas() {
		List<VentaVo> ventas = null;
		Map<String, Object> response = new HashMap<>();
		try {
			ventas = ventaService.findAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


		return new ResponseEntity<List<VentaVo>>(ventas, HttpStatus.OK);
	}

	// http://localhost:8080/apiVenta/venta/id
	@GetMapping("/venta/{id}")
	public ResponseEntity<?> getVenta(@PathVariable Integer id) {
		VentaVo venta = null;

		Map<String, Object> response = new HashMap<>();

		try {
			venta = ventaService.findById(id);
			if (venta == null) {
				response.put("mensaje", "No se encontro la venta");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<VentaVo>(venta, HttpStatus.OK);
	}

	// http://localhost:8080/apiVenta/venta
	@PostMapping("/venta")
	public ResponseEntity<?> create(@RequestBody VentaVo venta) {
		VentaVo ventaNew = null;

		Map<String, Object> response = new HashMap<>();

		try {
			/**
			 * ClienteVo cliente = new ClienteVo();

			
			cliente.setCorreoCliente("grajales0@gmail.com");
			cliente.setNombreCliente("juan");
			cliente.setApellidoCliente("villa");
			cliente.setDireccionCliente(null);
			cliente.setTelefonoCliente("3000");
			cliente.setPasswordCliente("camilo");

			venta.setCorreo_cliente(cliente);
			 */
			

			Integer ultimaDId = contabilidadDiariaService.findUltima();
			if(ultimaDId==null) {
				ultimaDId = 0;
			}
			ContabilidadDiariaVo ultimaD = contabilidadDiariaService.findById(ultimaDId);
			if (ultimaD != null) {
				System.out.println("_______________________");
				System.out.println(venta.getFecha_venta());
				System.out.println(LocalDate.now().toString());
				System.out.println("_______________________");
				if (venta.getFecha_venta().toString().equals(LocalDate.now().toString())) {
					System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBB");
					Integer contaMensualId = contabilidadMensualService.findUltima();
					ContabilidadMensualVo contaMensual = contabilidadMensualService.findById(contaMensualId);
					

					contaMensual.setIngresos_contabilidad_mensual(contaMensual.getIngresos_contabilidad_mensual() + venta.getPrecio_venta());
					contaMensual.setVentas_contabilidad_mensual(contaMensual.getVentas_contabilidad_mensual() + 1);
					ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

					Integer contaAnualId = contabilidadAnualService.findUltima();
					ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

					contaAnual.setIngresos_contabilidad_anual(
							contaAnual.getIngresos_contabilidad_anual() + venta.getPrecio_venta());
					contaAnual.setVentas_contabilidad_anual(contaAnual.getVentas_contabilidad_anual() + 1);
					ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

					ultimaD.setVentas_contabilidad_diaria(ultimaD.getVentas_contabilidad_diaria() + 1);
					ultimaD.setIngresos_contabilidad_diaria(ultimaD.getIngresos_contabilidad_diaria() + venta.getPrecio_venta());
					ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(ultimaD);
					
					venta.setId_registro_contabilidad_diaria(contaDiaGu);
				} else {
					if (venta.getFecha_venta().toString().split("-")[0].equals(LocalDate.now().toString().split("-")[0])) {
						if (venta.getFecha_venta().toString().split("-")[1].equals(LocalDate.now().toString().split("-")[1])) {
							System.out.println("VVV");
							
							Integer contaMensualId = contabilidadMensualService.findUltima();
							ContabilidadMensualVo contaMensual = contabilidadMensualService.findById(contaMensualId);

							contaMensual.setIngresos_contabilidad_mensual(
									contaMensual.getIngresos_contabilidad_mensual() + venta.getPrecio_venta());
							contaMensual
									.setVentas_contabilidad_mensual(contaMensual.getVentas_contabilidad_mensual() + 1);
							ContabilidadMensualVo contaMensualGu = contabilidadMensualService.save(contaMensual);

							Integer contaAnualId = contabilidadAnualService.findUltima();
							ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

							contaAnual.setIngresos_contabilidad_anual(
									contaAnual.getIngresos_contabilidad_anual() + venta.getPrecio_venta());
							contaAnual.setVentas_contabilidad_anual(contaAnual.getVentas_contabilidad_anual() + 1);
							ContabilidadAnualVo contaAnualGu = contabilidadAnualService.save(contaAnual);

							ContabilidadDiariaVo contaHoy = new ContabilidadDiariaVo();
							contaHoy.setIngresos_contabilidad_diaria(venta.getPrecio_venta());
							contaHoy.setVentas_contabilidad_diaria(1);
							contaHoy.setFecha(java.sql.Date.valueOf(LocalDate.now()));
							contaHoy.setId_registro_contabilidad_mensual(contaMensual);
							ContabilidadDiariaVo contaDiaGu=contabilidadDiariaService.save(contaHoy);
							
							venta.setId_registro_contabilidad_diaria(contaDiaGu);
						} else {
							Integer contaAnualId = contabilidadAnualService.findUltima();
							ContabilidadAnualVo contaAnual = contabilidadAnualService.findById(contaAnualId);

							contaAnual.setIngresos_contabilidad_anual(
									contaAnual.getIngresos_contabilidad_anual() + venta.getPrecio_venta());
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
							
							venta.setId_registro_contabilidad_diaria(contaDiaGu);
						}
					} else {
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
						
						venta.setId_registro_contabilidad_diaria(contaDiaGu);
					}
				}
			} else if(ultimaD==null){
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
				
				venta.setId_registro_contabilidad_diaria(contaDiaGu);

			}

			/**
			 * venta.getVentas().get(0).getId_registro_contabilidad_diaria().setId_registro_contabilidad_diaria(1);
			venta.getVentas().get(0).getId_registro_contabilidad_diaria().getId_registro_contabilidad_mensual()
					.setId_registro_contabilidad_mensual(1);
			venta.getVentas().get(0).getId_registro_contabilidad_diaria().getId_registro_contabilidad_mensual()
					.getId_registro_contabilidad_anual().setId_registro_contabilidad_anual(1);
			venta.getVentas().get(1).getId_registro_contabilidad_diaria().setId_registro_contabilidad_diaria(1);
			venta.getVentas().get(1).getId_registro_contabilidad_diaria().getId_registro_contabilidad_mensual()
					.setId_registro_contabilidad_mensual(1);
			venta.getVentas().get(1).getId_registro_contabilidad_diaria().getId_registro_contabilidad_mensual()
					.getId_registro_contabilidad_anual().setId_registro_contabilidad_anual(1);
			 */
			ClienteVo cliente = new ClienteVo();

			
			cliente.setCorreoCliente("crissis2004@gmail.com");
			cliente.setNombreCliente("Cristian");
			cliente.setApellidoCliente("Amador");
			cliente.setDireccionCliente("centenario");
			cliente.setTelefonoCliente("323");
			cliente.setPasswordCliente("12345");
			
			venta.setCorreo_cliente(cliente);
			venta.setFecha_venta(java.sql.Date.valueOf(LocalDate.now()));
			
			ventaNew = ventaService.save(venta);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		ventaNew.setFecha_venta(java.sql.Date.valueOf(LocalDate.now()));
		response.put("mensaje", "la venta se ha registro con exito");
		
		response.put("venta", ventaNew);
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

}
