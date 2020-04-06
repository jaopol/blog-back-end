package br.com.blog.controllers;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.converter.CommentaryConverter;
import br.com.blog.dtos.CommentaryDTO;
import br.com.blog.dtos.UserPostDTO;
import br.com.blog.model.BlogUser;
import br.com.blog.model.Commentary;
import br.com.blog.response.Response;
import br.com.blog.services.BlogUserService;
import br.com.blog.services.CommentaryService;
import br.com.blog.util.UtilValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api( value = "API blog - Commentários")
@RestController
@RequestMapping( "/commentary" )
public class CommentaryController {
	
	@Autowired
	private CommentaryService commentaryService;
	
	@Autowired
	private CommentaryConverter commentaryConverter;
	
	@Autowired
	private BlogUserService userService; 
	
	@ApiOperation( value = "Grava um Comentário do usário no blog" )
	@PostMapping( name="/add" )
	public ResponseEntity<Response<CommentaryDTO>> addCommentary( @RequestBody CommentaryDTO commentaryDTO ) {
		
		Response<CommentaryDTO> response = new Response<CommentaryDTO>();
		
		try {
			Optional<Commentary> result = commentaryService.addCommentary( commentaryConverter.transformDtoToEntity( commentaryDTO ) );
			response.setData( commentaryConverter.transformEntityToDto( result.get() ) );
			
			return ResponseEntity.ok( response );
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setContent( Arrays.asList( e.getMessage() ) );
			
			return ResponseEntity.badRequest().body( response );
		}

	}
	
	
	@ApiOperation( value = "Exclui um Comentário específico" )
	@DeleteMapping( name="/delete", value = "/{id}/{login}" )
	public ResponseEntity<Response<UserPostDTO>> deleteUserPost( @RequestParam Long id, @RequestParam String login ) {
		
		Response<UserPostDTO> response = new Response<UserPostDTO>();
		
		try {
			
			Optional<Commentary> commentary = commentaryService.findById( id );
			
			if( !commentary.isPresent() ) {
				response.setContent( Arrays.asList( "Comentário não encontrado!" ) );
				return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( response );
			}
			
			Optional<BlogUser> user = userService.findByLogin( login );

			if( !user.isPresent() ) {
				response.setContent( Arrays.asList( "Usuário não encontrado" ) );
				return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( response );
			}
			
			if( UtilValidation.isSameUsers( user.get().getId(), commentary.get().getBlogUser().getId() ) ) {
			
				Boolean removed = commentaryService.deleteCommentary( commentary.get() );
				
				if( removed ) {
					response.setContent( Arrays.asList( "Comentário excluído com sucesso!" ) );
					return ResponseEntity.ok( response ); 
				}
				response.setContent( Arrays.asList( "Usuário não tem permissão" ) );
				return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( response );
			}
			else {
				response.setContent( Arrays.asList( "Usuário não tem permissão" ) );
				return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( response );
			}

		} catch (Exception e) {
			response.setContent( Arrays.asList( e.getMessage() ) );
			return 	ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( response );
		}
	}
	
}
