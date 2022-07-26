package com.grupo2.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;

public interface IContabilidadAnualDao extends CrudRepository<ContabilidadAnualVo, Integer>{

}
