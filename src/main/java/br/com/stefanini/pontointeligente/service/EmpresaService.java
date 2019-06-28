package br.com.stefanini.pontointeligente.service;

import java.util.Optional;

import br.com.stefanini.pontointeligente.entity.Empresa;

public interface EmpresaService {
	
	Optional<Empresa> buscarPorCnpj(String cnpj);
	Empresa persist(Empresa empresa);
}