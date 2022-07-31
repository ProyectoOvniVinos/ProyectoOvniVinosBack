package com.grupo2.springboot.backend.apirest.services.contabilidaddiaria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IContabilidadAnualDao;
import com.grupo2.springboot.backend.apirest.dao.IContabilidadDiariaDao;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadAnualVo;
import com.grupo2.springboot.backend.apirest.entity.ContabilidadDiariaVo;

@Service
public class ContabilidadDiariaServiceImpl implements IContabilidadDiariaService {

	@Autowired
	private IContabilidadDiariaDao contabilidadDiariaDao;

	@Override
	@Transactional
	public ContabilidadDiariaVo save(ContabilidadDiariaVo contabilidadDiaria) {
		return contabilidadDiariaDao.save(contabilidadDiaria);
	}

	@Override
	@Transactional
	public ContabilidadDiariaVo update(ContabilidadDiariaVo contabilidadDiaria) {
		return contabilidadDiariaDao.save(contabilidadDiaria);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ContabilidadDiariaVo> findAll() {
		return (List<ContabilidadDiariaVo>) contabilidadDiariaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ContabilidadDiariaVo findById(Integer idContabilidadDia) {
		return contabilidadDiariaDao.findById(idContabilidadDia).orElse(null);
	}

	@Override
	public Integer findUltima() {
		return contabilidadDiariaDao.findUltima();
	}

}
