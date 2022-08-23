package com.grupo2.springboot.backend.apirest.services.usuarios;

import java.util.List;
import java.util.stream.Collectors;

import javax.management.relation.Role;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupo2.springboot.backend.apirest.dao.IAdministradorDao;
import com.grupo2.springboot.backend.apirest.entity.AdministradorVo;

@Service
public class UsuarioAdminService implements IUsuarioAdminService, UserDetailsService {
	
	@Autowired
	private IAdministradorDao adminService;
	
	
	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		
		AdministradorVo admin = adminService.findById(correo).orElse(null);
		
		if(admin == null) {
			throw new UsernameNotFoundException("Error en login: no existe el usuario");
		}
		
		List<GrantedAuthority> authorities = admin.getRoles().stream()
							.map(role -> new SimpleGrantedAuthority(role.getNombre()))
							.collect(Collectors.toList());	
		
		return new User(admin.getCorreoAdmin(), admin.getPasswordAdmin(),true,true,true,true,authorities);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public AdministradorVo findByCorreo(String correo) {
		return adminService.findById(correo).orElse(null);
	}

}
