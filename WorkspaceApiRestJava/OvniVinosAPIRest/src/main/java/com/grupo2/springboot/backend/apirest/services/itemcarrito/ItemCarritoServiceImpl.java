package com.grupo2.springboot.backend.apirest.services.itemcarrito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IitemCarritoDao;
import com.grupo2.springboot.backend.apirest.entity.ItemCarritoVo;

public class ItemCarritoServiceImpl implements IitemCarritoService{

	@Autowired
	private IitemCarritoDao itemCarritoDao;
	
	@Override
	@Transactional
	public void delete(ItemCarritoVo itemCarrito) {
		itemCarritoDao.delete(itemCarrito);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItemCarritoVo> findByCarrito(Integer idCarrito) {
		return itemCarritoDao.findByCarrito(idCarrito);
	}

}
