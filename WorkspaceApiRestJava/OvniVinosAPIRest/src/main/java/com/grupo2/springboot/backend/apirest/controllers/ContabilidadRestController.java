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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadMensualVo;
import com.grupo2.springboot.backend.apirest.entity.InventarioGeneralVo;
import com.grupo2.springboot.backend.apirest.services.contabilidadanual.IContabilidadAnualService;
import com.grupo2.springboot.backend.apirest.services.contabilidaddiaria.IContabilidadDiariaService;
import com.grupo2.springboot.backend.apirest.services.contabilidadmensual.IContabilidadMensualService;

@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiContabilidad")
public class ContabilidadRestController {

	@Autowired
	private IContabilidadAnualService contabilidadAnualService;
	
	@Autowired
	private IContabilidadMensualService contabilidadMensualService;
	
	@Autowired
	private IContabilidadDiariaService contabilidadDiariaService;
	
// ANUAL
	// http://localhost:8080/apiContabilidad/contabilidadesAnuales
	@GetMapping("/contabilidadesAnuales")
	public ResponseEntity<?> contabilidadesAnuales(){
		List<ContabilidadAnualVo> contabilidades = null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			contabilidades = contabilidadAnualService.findAll();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ContabilidadAnualVo>>(contabilidades,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiContabilidad/contabilidadAnual
	@GetMapping("/contabilidadAnual/{id}")
	public ResponseEntity<?> contabilidadAnuales(Integer id){
		ContabilidadAnualVo contabilidad = null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			contabilidad = contabilidadAnualService.findById(id);
			if(contabilidad==null) {
				response.put("mensaje","no se encontro el registro de contabilidad anual");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ContabilidadAnualVo>(contabilidad,HttpStatus.OK);
	}

// MENSUAL
	// http://localhost:8080/apiContabilidad/contabilidadesMensuales
	@GetMapping("/contabilidadesMensuales")
	public ResponseEntity<?> contabilidadesMensuales(){
		List<ContabilidadMensualVo> contabilidades = null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			contabilidades = contabilidadMensualService.findAll();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ContabilidadMensualVo>>(contabilidades,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiContabilidad/contabilidadMensual/{id}
	@GetMapping("/contabilidadMensual/{id}")
	public ResponseEntity<?> contabilidadMensual(Integer id){
		ContabilidadMensualVo contabilidad = null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			contabilidad = contabilidadMensualService.findById(id);
			if(contabilidad==null) {
				response.put("mensaje","no se encontro el registro de contabilidad mensual");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ContabilidadMensualVo>(contabilidad,HttpStatus.OK);
	}
	
// DIARIA
	// http://localhost:8080/apiContabilidad/contabilidadesDiarias
	@GetMapping("/contabilidadesDiarias")
	public ResponseEntity<?> contabilidadesDiarias(){
		List<ContabilidadDiariaVo> contabilidades = null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			contabilidades = contabilidadDiariaService.findAll();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<ContabilidadDiariaVo>>(contabilidades,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiContabilidad/contabilidadDiaria/{id}
	@GetMapping("/contabilidadDiaria/{id}")
	public ResponseEntity<?> contabilidadDiaria(Integer id){
		ContabilidadDiariaVo contabilidad = null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			contabilidad = contabilidadDiariaService.findById(id);
			if(contabilidad==null) {
				response.put("mensaje","no se encontro el registro de contabilidad mensual");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ContabilidadDiariaVo>(contabilidad,HttpStatus.OK);
	}
	

	// http://localhost:8080/apiContabilidad/llenar
	@GetMapping("/llenar")
	public ResponseEntity<?> llenar(){
		ContabilidadDiariaVo contabilidad = null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			contabilidad = contabilidadDiariaService.llenar();

		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ContabilidadDiariaVo>(contabilidad,HttpStatus.OK);
	}
	
}
