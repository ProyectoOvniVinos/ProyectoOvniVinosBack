package com.grupo2.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.springboot.backend.apirest.entity.ProductoVo;
import com.grupo2.springboot.backend.apirest.services.producto.IProductoService;

@RestController
@RequestMapping("/apiProd")
public class ProductoRestController {
	
	@Autowired
	private IProductoService productoService;
	
	// http://localhost:8080/apiProd/productos
	@GetMapping("/productos")
	public List<ProductoVo> getProductos(){
		return productoService.findAll();
	}
	
	// http://localhost:8080/apiProd/productos
	@GetMapping("/productos")
	public ResponseEntity<?>  getProducto(@PathVariable int codigo){
		ProductoVo producto=null;
		
		Map<String,Object>response = new HashMap<>();
		try {
			producto = productoService.findByCodigo_producto(codigo);
		} catch(DataAccessException e) {
			response.put("mensaje","error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ProductoVo>(producto,HttpStatus.OK);
	}
	
	// http://localhost:8080/apiProd/producto
	@PostMapping("/producto")
	public ResponseEntity<?> create(@RequestBody ProductoVo producto){
		ProductoVo productoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			productoNew = productoService.save(producto);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje","el producto se ha creado con exito");
		response.put("producto", productoNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	// http://localhost:8080/apiProd/producto
	@PutMapping("/producto")
	public ResponseEntity<?> update(@RequestBody ProductoVo producto,  @PathVariable int codigo){
		ProductoVo productoActual = productoService.findByCodigo_producto(codigo);
		ProductoVo productoUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			productoActual.setNombre_producto(producto.getNombre_producto());
			productoActual.setPrecio_producto(producto.getPrecio_producto());
			productoActual.setPrecio_producto_proveedor(producto.getPrecio_producto_proveedor());
			productoActual.setDescripcion_producto(producto.getDescripcion_producto());
			
			productoUpdated = productoService.save(productoActual);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","el producto ha sido actualizado con exito");
		response.put("producto", productoUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}

	// http://localhost:8080/apiProd/producto
	@DeleteMapping("/producto")
	public ResponseEntity<?> delete(@PathVariable int codigo){
		Map<String,Object> response = new HashMap<>();
		try {
			ProductoVo producto = productoService.findByCodigo_producto(codigo);
			productoService.delete(producto);
		}catch(DataAccessException e) {
			response.put("mensaje","Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "el producto fue eliminado con exito");
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
	}
}
