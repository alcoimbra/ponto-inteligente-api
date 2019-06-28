package br.com.stefanini.pontointeligente.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.stefanini.pontointeligente.entity.Empresa;
import br.com.stefanini.pontointeligente.repository.EmpresaRepository;

@Service
public class EmpresaServiceImpl {
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma Empresa para o CNPJ {}", cnpj);
		
		return Optional.ofNullable(this.empresaRepository.findByCnpj(cnpj));
	}
	
	public Empresa persist(Empresa empresa) {
		log.info("Persistindo Empresa: {}", empresa);
		
		return this.empresaRepository.save(empresa);
	}
}