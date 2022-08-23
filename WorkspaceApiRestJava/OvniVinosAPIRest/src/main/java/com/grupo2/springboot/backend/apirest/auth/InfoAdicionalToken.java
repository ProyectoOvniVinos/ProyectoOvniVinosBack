package com.grupo2.springboot.backend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;
import com.grupo2.springboot.backend.apirest.services.usuarios.IUsuarioAdminService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{
	
	@Autowired
	private IUsuarioAdminService usuarioAdminSerive;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		AdministradorVo admin = usuarioAdminSerive.findByCorreo(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("correo", admin.getCorreoAdmin());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
