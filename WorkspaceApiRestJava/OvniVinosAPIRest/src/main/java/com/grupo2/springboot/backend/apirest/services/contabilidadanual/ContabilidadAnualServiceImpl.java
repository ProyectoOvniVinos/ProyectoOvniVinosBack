package com.grupo2.springboot.backend.apirest.services.contabilidadanual;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IContabilidadAnualDao;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;


@Service
public class ContabilidadAnualServiceImpl implements IContabilidadAnualService{

	@Autowired
	private IContabilidadAnualDao contabilidadAnualDao;
	
	@Override
	@Transactional
	public ContabilidadAnualVo save(ContabilidadAnualVo contabilidadAnual) {
		return contabilidadAnualDao.save(contabilidadAnual);
	}

	@Override
	@Transactional
	public ContabilidadAnualVo update(ContabilidadAnualVo contabilidadAnual) {
		return contabilidadAnualDao.save(contabilidadAnual);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContabilidadAnualVo> findAll() {
		return (List<ContabilidadAnualVo>) contabilidadAnualDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ContabilidadAnualVo findById(Integer idContabilidadAn) {
		return contabilidadAnualDao.findById(idContabilidadAn).orElse(null);
	}
	
	@Override
	public ContabilidadAnualVo findUltima() {
		/*
		return contabilidadAnualDao.findUltima();
		*/
		return null;
	}
	
	
	

}
