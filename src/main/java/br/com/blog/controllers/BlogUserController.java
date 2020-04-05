package br.com.blog.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.converter.BlogUserConverter;
import br.com.blog.dtos.BlogUserDTO;
import br.com.blog.model.BlogUser;
import br.com.blog.response.Response;
import br.com.blog.services.BlogUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api( value = "API blog - User")
@RestController
@RequestMapping( "/user" )
public class BlogUserController {
	
	@Autowired
	private BlogUserService blgoUserService;
	
	@Autowired
	private BlogUserConverter blogUserConverter;

	@ApiOperation( value = "Grava um usuário no blog" )
	@PostMapping( name="/add" )
	public ResponseEntity<Response<BlogUserDTO>> addBlogUser( @RequestBody BlogUserDTO blogUser ) {
		
		Response<BlogUserDTO> response = new Response<BlogUserDTO>();
		
		Optional<BlogUser> result = blgoUserService.addBlogUser( blogUserConverter.transformDtoToEntity( blogUser ) );
		response.setData( blogUserConverter.transformEntityToDto( result.get() ) );

		return ResponseEntity.ok( response );

	}
	
}
