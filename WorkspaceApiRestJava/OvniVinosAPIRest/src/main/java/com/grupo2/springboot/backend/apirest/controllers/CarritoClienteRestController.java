package com.grupo2.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.CarritoClienteVo;
import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.services.carritocliente.ICarritoClienteService;

@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiCarrito")
public class CarritoClienteRestController {
	
	@Autowired
	private ICarritoClienteService carritoService;
	
	// http://localhost:8080/apiCarrito/carrito
	@GetMapping("/carrito/{id}")
	public ResponseEntity<?> carrito(@PathVariable Integer id){
		CarritoClienteVo carrito = null;
		
		Map<String,Object>response = new HashMap<>();
		
		try {
			carrito = carritoService.findById(id);
			if(carrito==null) {
				response.put("mensaje","no se encontro el carrito");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
			}
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CarritoClienteVo>(carrito,HttpStatus.OK);
	}

}
