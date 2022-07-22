package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.ProductoVo;

public interface IProductoDao extends CrudRepository<ProductoVo, Integer>{

}
