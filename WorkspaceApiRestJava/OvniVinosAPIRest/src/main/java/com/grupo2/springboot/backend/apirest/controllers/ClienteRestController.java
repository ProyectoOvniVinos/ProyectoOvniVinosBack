package com.grupo2.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.services.cliente.IClienteService;


@CrossOrigin(origins= {"http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089"})
@RestController
@RequestMapping("/apiCliente")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	
	// http://localhost:8080/apiCliente/clientes
	@GetMapping("/clientes")
	public ResponseEntity<?> clientes(){
		List<ClienteVo> clientes = null; 
		Map<String, Object> response = new HashMap<>();
		try {
			clientes = clienteService.findAll();
		}catch(DataAccessException e){
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		System.out.println(clientes);
		
		return new ResponseEntity<List<ClienteVo>>(clientes,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiCliente/cliente/correo
	@GetMapping("/cliente/{correo}")
	public ResponseEntity<?>  getCliente(@PathVariable String correo){
		ClienteVo cliente=null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			cliente = clienteService.findById(correo);
			if(cliente==null) {
				response.put("mensaje","no se encontro el cliente");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ClienteVo>(cliente,HttpStatus.OK);
	}
	
	@GetMapping("/clienteNombre/{nombre}")
	public ResponseEntity<?>  getClienteBynombre(@PathVariable String nombre){
		ClienteVo cliente=null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			cliente = clienteService.findByNombre(nombre);
			System.out.println(cliente);
			if(cliente==null) {
				response.put("mensaje","no se encontro el cliente");
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ClienteVo>(cliente,HttpStatus.OK);
	}
	
	
	// http://localhost:8080/apiCliente/registro
	@PostMapping("/registro")
	public ResponseEntity<?> registro(@RequestBody ClienteVo cliente){
		ClienteVo clienteNew = null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			clienteNew = clienteService.save(cliente);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","el ciente se ha creado con exito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	// http://localhost:8080/apiCliente/cliente/correo
	@PutMapping("/cliente/{correo}")
	public ResponseEntity<?> update(@RequestBody ClienteVo cliente, @PathVariable String correo){
		ClienteVo clienteActual = clienteService.findById(correo);
		ClienteVo clienteUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteActual.setNombre_cliente(cliente.getNombre_cliente());
			clienteActual.setApellido_cliente(cliente.getApellido_cliente());
			clienteActual.setDireccion_cliente(cliente.getDireccion_cliente());
			clienteActual.setTelefono_cliente(cliente.getTelefono_cliente());
			clienteActual.setPassword_cliente(cliente.getPassword_cliente());
			
			clienteUpdated = clienteService.save(clienteActual);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","el cliente ha sido actualizado con exito");
		response.put("producto", clienteUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
}