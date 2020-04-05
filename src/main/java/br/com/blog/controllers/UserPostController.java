package br.com.blog.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.converter.UserPostConverter;
import br.com.blog.dtos.UserPostDTO;
import br.com.blog.model.UserPost;
import br.com.blog.response.Response;
import br.com.blog.services.UserPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api( value = "API blog - Post")
@RestController
@RequestMapping( "/userpost" )
public class UserPostController {
	
	@Autowired
	private UserPostService userPostService;
	
	@Autowired
	private UserPostConverter userPostConverter;
	
	@ApiOperation( value = "Grava um post do usário no blog" )
	@PostMapping( name="/add" )
	public ResponseEntity<Response<UserPostDTO>> addUserPost( @RequestBody UserPostDTO userPostDTO ) {
		
		Response<UserPostDTO> response = new Response<UserPostDTO>();
		
		Optional<UserPost> result = userPostService.addPost( userPostConverter.transformDtoToEntity( userPostDTO ) );
		response.setData( userPostConverter.transformEntityToDto( result.get() ) );

		return ResponseEntity.ok( response );

	}

}
