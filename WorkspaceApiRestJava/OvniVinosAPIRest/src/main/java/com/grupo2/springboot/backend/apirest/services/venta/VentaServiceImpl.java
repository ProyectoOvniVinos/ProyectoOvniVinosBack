package com.grupo2.springboot.backend.apirest.services.venta;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.grupo2.springboot.backend.apirest.services.producto.IProductoService;
import com.grupo2.springboot.backend.apirest.dao.IVentaDao;
@Service
public class VentaServiceImpl implements IVentaService{

	@Autowired
	private IVentaDao ventaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<VentaVo> findAll() {
		return (List<VentaVo>) ventaDao.findAll();
	}

	@Override
	@Transactional
	public VentaVo save(VentaVo venta) {
		return ventaDao.save(venta);
	}

	@Override
	public VentaVo findById(Integer idVenta) {
		return ventaDao.findById(idVenta).orElse(null);
	}
	
	

}
