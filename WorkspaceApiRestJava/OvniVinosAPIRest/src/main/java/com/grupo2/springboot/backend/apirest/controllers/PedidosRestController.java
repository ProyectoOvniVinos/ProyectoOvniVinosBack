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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.dao.IAdministradorDao;
import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.PedidoVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.grupo2.springboot.backend.apirest.services.administrador.IAdministradorService;
import com.grupo2.springboot.backend.apirest.services.inventariodetalles.IinventarioDetallesService;
import com.grupo2.springboot.backend.apirest.services.pedido.IPedidoService;
import com.grupo2.springboot.backend.apirest.services.venta.IVentaService;

@CrossOrigin(origins = { "http://localhost:4200", "**", "http://localhost:8090", "http://localhost:8089" })
@RestController
@RequestMapping("/apiPedidos")
public class PedidosRestController {
	
	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IVentaService ventaService;

	@Autowired
	private IinventarioDetallesService inventarioService;
	
	@Autowired
	private IAdministradorService adminService;
	
	@PostMapping("/pedido")
	public ResponseEntity<?> create(@RequestBody PedidoVo pedido) {
		Map<String, Object> response = new HashMap<>();
		PedidoVo pedidoNew = null;
		AdministradorVo admin = null;
		try {
			admin = adminService.findByCorreo("crissis2004@gmail.com");
			pedido.setAdministrador(admin);
			System.out.println(pedido.getEstado());
			System.out.println(pedido.getAdministrador().getCorreoAdmin());
			System.out.println(pedido.getCliente().getCorreoCliente());
			System.out.println(pedido.getVenta().getPrecioVenta());
			pedidoNew = pedidoService.create(pedido);
			System.out.println(pedidoNew.getId());
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "el pedido se realizo con exito");

		response.put("pedido", pedidoNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody PedidoVo pedido){
		Map<String, Object> response = new HashMap<>();
		PedidoVo pedidoActualizado = null;
		try {
			pedidoActualizado = pedidoService.create(pedido);
			switch (pedidoActualizado.getEstado()) {
				case "3": {
					ventaService.gestorAsignarContabilidad(pedido.getVenta(), pedidoActualizado.getVenta());
				}
				case "4": {
					inventarioService.ventaDevuelta(pedidoActualizado.getVenta());
					pedidoActualizado.setVenta(null);
					ventaService.eliminar(pedido.getVenta().getCodigoVenta());
				}
			}
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "el pedido se actualizo con exito");

		response.put("pedido", pedidoActualizado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/pedidos")
	public ResponseEntity<?> todosPedido(){
		List<PedidoVo> pedidos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			pedidos = pedidoService.findByAll();
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<PedidoVo>>(pedidos, HttpStatus.OK);
	}
	
	@GetMapping("/pedidosPendientes")
	public ResponseEntity<?> todosPendientes(){
		List<PedidoVo> pedidos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			pedidos = pedidoService.findByPendientes();
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<PedidoVo>>(pedidos, HttpStatus.OK);
	}
	
	@GetMapping("/pedidosProceso")
	public ResponseEntity<?> todosProceso(){
		List<PedidoVo> pedidos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			pedidos = pedidoService.findByProceso();
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<PedidoVo>>(pedidos, HttpStatus.OK);
	}
	
	@GetMapping("/pedidosCompletados")
	public ResponseEntity<?> todosCompletados(){
		List<PedidoVo> pedidos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			pedidos = pedidoService.findByCompleto();
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<PedidoVo>>(pedidos, HttpStatus.OK);
	}
	
	@GetMapping("/pedidosCliente/{correo}")
	public ResponseEntity<?> todosCliente(@PathVariable String correo){
		List<PedidoVo> pedidos = null;
		Map<String, Object> response = new HashMap<>();
		try {
			pedidos = pedidoService.findByCliente(correo);
		} catch (DataAccessException e) {
			response.put("mensaje", "error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<PedidoVo>>(pedidos, HttpStatus.OK);
	}

}
