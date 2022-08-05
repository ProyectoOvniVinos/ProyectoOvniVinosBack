package com.grupo2.springboot.backend.apirest.dao;

import java.util.List;

import javax.annotation.processing.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.grupo2.springboot.backend.apirest.entity.ItemCarritoVo;

public interface IitemCarritoDao extends CrudRepository<ItemCarritoVo, Integer>{

	@Query("SELECT itemCarritoVo from ItemCarritoVo itemCarritoVo WHERE carrito = ?1")
	public List<ItemCarritoVo> findByCarrito(Integer term);
	
}
