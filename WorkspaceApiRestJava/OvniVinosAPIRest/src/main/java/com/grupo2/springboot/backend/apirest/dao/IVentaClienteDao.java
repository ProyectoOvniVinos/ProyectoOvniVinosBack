package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.entity.VentaClienteVo;

public interface IVentaClienteDao extends CrudRepository<VentaClienteVo, Integer>{

}
