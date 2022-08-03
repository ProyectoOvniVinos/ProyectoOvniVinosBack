package com.grupo2.springboot.backend.apirest.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.CarritoClienteVo;

public interface ICarritoClienteDao extends CrudRepository<CarritoClienteVo, Integer>{
	
	@Query("SELECT id_carrito from CarritoClienteVo WHERE cliente =?1")
	public Integer findIdByCliente(String correoCliente);
}
