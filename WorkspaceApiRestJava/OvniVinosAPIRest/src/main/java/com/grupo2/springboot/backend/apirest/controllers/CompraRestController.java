package com.grupo2.springboot.backend.apirest.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.CompraVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;
import com.grupo2.springboot.backend.apirest.services.compra.ICompraService;
import com.grupo2.springboot.backend.apirest.services.contabilidadanual.IContabilidadAnualService;
import com.grupo2.springboot.backend.apirest.services.contabilidaddiaria.IContabilidadDiariaService;
import com.grupo2.springboot.backend.apirest.services.contabilidadmensual.IContabilidadMensualService;
import com.grupo2.springboot.backend.apirest.services.inventariodetalles.IinventarioDetallesService;

@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiCompra")
public class CompraRestController {
	
	@Autowired
	private ICompraService compraService;
	
	@Autowired
	private IinventarioDetallesService inventarioService;
	
	@Autowired
	private IContabilidadDiariaService contabilidadDiariaService;

	@Autowired
	private IContabilidadMensualService contabilidadMensualService;

	@Autowired
	private IContabilidadAnualService contabilidadAnualService;
	
	// http://localhost:8080/apiCompra/compras
	@GetMapping("/compras")
	public ResponseEntity<?> compras(){
		List<CompraVo> compras = null; 
		Map<String, Object> response = new HashMap<>();
		try {
			compras = compraService.findAll();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println(compras);
		
		return new ResponseEntity<List<CompraVo>>(compras,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiCompra/compra/id
	@GetMapping("/compra/{id}")
	public ResponseEntity<?>  getCompra(@PathVariable Integer id){
		CompraVo compra=null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			compra = compraService.findById(id);
			if(compra==null) {
				response.put("mensaje","no se encontro la compra");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CompraVo>(compra,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiCompra/registro
	@PostMapping("/registro")
	public ResponseEntity<?> registro(@RequestBody CompraVo compra){
		CompraVo compraNew=null;
		
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
		try {
			System.out.println(compra);
			
			AdministradorVo admin = new AdministradorVo();
			admin.setCorreoAdmin("cristian@gmail.com");
			admin.setNombreAdmin("Cristian");
			admin.setApellidoAdmin("Amador");
			admin.setDireccionAdmin("centenario");
			admin.setTelefonoAdmin("323");
			admin.setPasswordAdmin("12345");
			compra.setCorreo_admin(admin);
			
			compra.setFecha_compra(LocalDateTime.parse(dtf.format(LocalDateTime.now()),dtf));
			Integer ultimaDId = contabilidadDiariaService.findUltima();
			if(ultimaDId==null) {
				ultimaDId = 0;
			}
			ContabilidadDiariaVo ultimaD = contabilidadDiariaService.findById(ultimaDId);
			if (ultimaD != null) {
				if (dtf.format(LocalDateTime.now()).split("-")[0].equals(compra.getFecha_compra().toString().split("-")[0]) && dtf.format(LocalDateTime.now()).split("-")[1].equals(compra.getFecha_compra().toString().split("-")[1]) && dtf.format(LocalDateTime.now()).split("-")[2].equals(compra.getFecha_compra().toString().split("-")[2].split("T")[0])) {
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
				} else {
					if (compra.getFecha_compra().toString().split("-")[0].equals(LocalDate.now().toString().split("-")[0])) {
						if (compra.getFecha_compra().toString().split("-")[1].equals(LocalDate.now().toString().split("-")[1])) {
							System.out.println("VVV");
							
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
						} else {
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
					} else {
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
				}
			} else if(ultimaD==null){
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
			
			System.out.println(compra);
			System.out.println(compra.getId_registro_contabilidad_diaria());
			
			compra.setCantidad_compra();
			compra.setPrecio_compra();
			
			compraNew = compraService.save(compra);
			inventarioService.InsertarInventario(compraNew);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","la compra se ha creado con exito");
		response.put("compra", compraNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

}
