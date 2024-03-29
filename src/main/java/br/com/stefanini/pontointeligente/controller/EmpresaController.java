package br.com.stefanini.pontointeligente.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.stefanini.pontointeligente.dto.EmpresaDto;
import br.com.stefanini.pontointeligente.entity.Empresa;
import br.com.stefanini.pontointeligente.response.Response;
import br.com.stefanini.pontointeligente.service.EmpresaService;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {
	private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);
	
	@Autowired
	private EmpresaService empresaService;
	
	public EmpresaController() {}
	
	public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj){
		log.info("Buscando Empresa por CNPJ: {}", cnpj);
		Response<EmpresaDto> response = new Response<EmpresaDto>();
		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(cnpj);
		
		if(!empresa.isPresent()) {
			log.info("Empresa não Encontrada para o CNPJ: {}", cnpj);
			response.getErrors().add("Empresa não Encontrada para o CNPJ " + cnpj);
			
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(this.converterEmpresaDto(empresa.get()));
		
		return ResponseEntity.ok(response);
	}
	
	private EmpresaDto converterEmpresaDto(Empresa empresa) {
		EmpresaDto empresaDto = new EmpresaDto();
		empresaDto.setId(empresa.getId());
		empresaDto.setCnpj(empresa.getCnpj());
		empresaDto.setRazaoSocial(empresa.getRazaoSocial());
		
		return empresaDto;
	}
}