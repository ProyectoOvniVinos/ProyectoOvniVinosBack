package com.grupo2.springboot.backend.apirest.services.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.springboot.backend.apirest.dao.ICompraDao;
import com.grupo2.springboot.backend.apirest.entity.CompraVo;

@Service
public class CompraServiceImpl implements ICompraService{
	
	@Autowired
	private ICompraDao compraDao;

	@Override
	public List<CompraVo> findAll() {
		return (List<CompraVo>) compraDao.findAll();
	}

	@Override
	public CompraVo findById(Integer id) {
		return compraDao.findById(id).orElse(null);
	}

	@Override
	public CompraVo save(CompraVo compra) {
		return compraDao.save(compra);
	}

}
