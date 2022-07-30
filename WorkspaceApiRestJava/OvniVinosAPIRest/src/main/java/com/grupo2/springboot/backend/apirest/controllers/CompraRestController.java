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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.CompraVo;
import com.grupo2.springboot.backend.apirest.services.compra.ICompraService;
import com.grupo2.springboot.backend.apirest.services.inventariodetalles.IinventarioDetallesService;

@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiCompra")
public class CompraRestController {
	
	@Autowired
	private ICompraService compraService;
	
	private IinventarioDetallesService inventarioService;
	
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
		try {
			AdministradorVo admin = new AdministradorVo();
			admin.setCorreoAdmin("grajales@gmail.com");
			admin.setNombreAdmin("juan");
			admin.setApellidoAdmin("grajales");
			admin.setDireccionAdmin("villa");
			admin.setTelefonoAdmin("3000");
			admin.setPasswordAdmin("camilo");
			compra.setCorreo_admin(admin);
			
			compra.getId_registro_contabilidad_diaria().setId_registro_contabilidad_diaria(1);
			compra.getId_registro_contabilidad_diaria().getId_registro_contabilidad_mensual().setId_registro_contabilidad_mensual(1);
			compra.getId_registro_contabilidad_diaria().getId_registro_contabilidad_mensual().getId_registro_contabilidad_anual().setId_registro_contabilidad_anual(1);
			compra.getId_registro_contabilidad_diaria().setId_registro_contabilidad_diaria(1);
			compra.getId_registro_contabilidad_diaria().getId_registro_contabilidad_mensual().setId_registro_contabilidad_mensual(1);
			compra.getId_registro_contabilidad_diaria().getId_registro_contabilidad_mensual().getId_registro_contabilidad_anual().setId_registro_contabilidad_anual(1);
			System.out.println(compra);
			
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
