package br.com.blog.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.repositories.UserRepository;
import br.com.blog.security.AccountCredentials;
import br.com.blog.security.jwt.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;

@Api( value = "Autentição Blog") 
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	UserRepository repository;
	
	@ApiOperation(value = "Autentica o usuario e retorna o token")
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signin" )
	public ResponseEntity signin(@RequestBody AccountCredentials data) {
		try {
			String username = data.getUsername();
			String pasword = data.getPassword();
			
			authenticationManager.authenticate( new UsernamePasswordAuthenticationToken( username, pasword ) );
			
			var user = repository.findByUsername( username );
			
			var token = "";
			
			if( user != null ) {
				token = tokenProvider.createToken( username, user.getRoles() );
			} else {
				throw new UsernameNotFoundException( "Username " + username + " não encontrado!" );
			}
			
			Map<Object, Object> model = new HashMap<>();
			model.put( "username", username );
			model.put( "token", token );
			return ok( model );
		} 
		catch( AuthenticationException e ) {
			throw new BadCredentialsException( "Usuário ou senha inválidos!" );
		}
	}
}
