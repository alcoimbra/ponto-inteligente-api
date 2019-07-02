package br.com.stefanini.pontointeligente.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.stefanini.pontointeligente.entity.Funcionario;
import br.com.stefanini.pontointeligente.entity.enums.PerfilEnum;

public class JwtUserFactory {
	
	private JwtUserFactory() {}
	
	public static JwtUser create(Funcionario funcionario) {
		return new JwtUser(funcionario.getId(), funcionario.getEmail(), funcionario.getSenha(),
				mapToGrantedAuthorities(funcionario.getPerfil()));
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		
		return authorities;
	}
}