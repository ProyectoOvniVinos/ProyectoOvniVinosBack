package com.grupo2.springboot.backend.apirest.util.service;

import java.io.File;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.grupo2.springboot.backend.apirest.entity.ClienteVo;
import com.grupo2.springboot.backend.apirest.entity.VentaVo;
import com.grupo2.springboot.backend.apirest.util.CorreoDTO;
import com.grupo2.springboot.backend.apirest.util.EnviarCorreo;

@Service
public class EnvioCorreoImpl implements IEnviosCorreo {

	@Override
	public void enviarCorreo(ClienteVo cliente, VentaVo venta) {

		CorreoDTO dto = new CorreoDTO();

		String subject = "Compra ovnivinos";
		String body = "Hola " + cliente.getNombreCliente() + " " + cliente.getApellidoCliente()
				+ " su compra se realizo con exito \n"
				+ " para descargar su factura ingrese a http://localhost:8080/apiVenta/factura/"
				+ venta.getCodigo_venta();
		// List<File> adjuntos = Collections.singletonList(new File("PATH_TO_FILE"));
		List<File> adjuntos = null;
		try {

			dto.getDestinatarios().add(cliente.getCorreoCliente());
			dto.setContenido(body);
			dto.setTitulo(subject);
			if (adjuntos != null && !adjuntos.isEmpty())
				dto.setAdjuntos(adjuntos);

			EnviarCorreo enviarCorreo = new EnviarCorreo(dto);
			enviarCorreo.start();
		} catch (Exception e) {
			System.out.println("hola");
		}

	}
}
