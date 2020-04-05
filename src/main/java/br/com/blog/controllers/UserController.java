package br.com.blog.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api( value = "API blog - User")
@RestController
@RequestMapping( "/user" )
public class UserController {

	@ApiOperation( value = "Retorna todos os usuarios cadastrados" )
	@GetMapping( name = "/findAll" )
	public ResponseEntity<List<String>> findAll(){
		
	//	List<Cpf> listCpf = service.findAll();
	//	List<CpfDTO> listCpfDto = listCpf.stream().map( obj -> new CpfDTO( obj ) )
	//			.collect( Collectors.toList() );
	List<String> list = new ArrayList<String>();	
		return ResponseEntity.ok().body( list );
	}
	
}
