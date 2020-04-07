package br.com.blog.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.blog.converter.CommentaryConverter;
import br.com.blog.converter.UserPostConverter;
import br.com.blog.dtos.UserPostDTO;
import br.com.blog.model.Commentary;
import br.com.blog.model.UserPost;
import br.com.blog.model.Users;
import br.com.blog.response.Response;
import br.com.blog.services.BlogUserService;
import br.com.blog.services.CommentaryService;
import br.com.blog.services.UserPostService;
import br.com.blog.util.UtilValidation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api( value = "API blog - Post")
@RestController
@RequestMapping( "/api/v1/userpost" )
public class UserPostController {
	
	@Autowired
	private UserPostService userPostService;
	
	@Autowired
	private UserPostConverter userPostConverter;
	
	@Autowired
	private CommentaryConverter commentaryConverter;
	
	@Autowired
	private BlogUserService userService;
	
	@Autowired
	private CommentaryService commentaryService;
	
	@ApiOperation( value = "Grava um post do usário no blog" )
	@PostMapping( name="/add" )
	public ResponseEntity<Response<UserPostDTO>> addUserPost( @RequestBody UserPostDTO userPostDTO ) {
		
		Response<UserPostDTO> response = new Response<UserPostDTO>();
		
		try {
			Optional<UserPost> result = userPostService.addPost( userPostConverter.transformDtoToEntity( userPostDTO ) );
			response.setData( userPostConverter.transformEntityToDto( result.get() ) );
			
			return ResponseEntity.ok( response );
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setContent( Arrays.asList( e.getMessage() ) );
			
			return ResponseEntity.badRequest().body( response );
		}

	}
	
	@ApiOperation( value = "Retorna todos os posts do blog" )
	@GetMapping( name = "/findAll" )
	public ResponseEntity<Response<List<UserPostDTO>>> findAll(){
		
		Response<List<UserPostDTO>> response = new Response<List<UserPostDTO>>();
		Optional<List<UserPost>> listUserPost = userPostService.findAll();
		try {
			if ( !listUserPost.isPresent() || CollectionUtils.isEmpty( listUserPost.get() ) ) {
				List<String> listaErros = Arrays.asList("Nenhum post publicado!");
				response.setContent( listaErros );
				
				return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( response );
			}
			
			List<UserPostDTO> listUserPostDTO = transformListEntityToDto( listUserPost.get() );
			
			for ( UserPostDTO userPostDTO : listUserPostDTO ) {
				Optional<List<Commentary>> listCommentary = commentaryService.findAllByIdPost( userPostDTO.getId() );
				
				userPostDTO.setListCommentaryDTO( commentaryConverter.transformListEntityToDto( listCommentary ) );
			}
			
			response.setData( listUserPostDTO  );
			return ResponseEntity.ok(response);
		} 
		catch (Exception e) {
			response.setContent( Arrays.asList( e.getMessage() ) );
			return ResponseEntity.badRequest().body( response );
		}
	}
	

	@ApiOperation( value = "Retorna todos os posts do blog pelo texto informado" )
	@GetMapping( name = "/findByText", value = "/{text}" )
	public ResponseEntity<Response<List<UserPostDTO>>> findByText( @RequestParam String text ){
		
		Response<List<UserPostDTO>> response = new Response<List<UserPostDTO>>();
		Optional<List<UserPost>> listUserPost = userPostService.findByText( text );
		try {
			if ( !listUserPost.isPresent() || CollectionUtils.isEmpty( listUserPost.get() ) ) {
				List<String> listaErros = Arrays.asList("Nenhum post publicado!");
				response.setContent( listaErros );
				
				return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( response );
			}
			response.setData( transformListEntityToDto( listUserPost.get() ) );
			return ResponseEntity.ok(response);
		} 
		catch (Exception e) {
			response.setContent( Arrays.asList( e.getMessage() ) );
			return ResponseEntity.badRequest().body( response );
		}
	}
	
	@ApiOperation( value = "Exclui um Post específico" )
	@DeleteMapping( name="/delete", value = "/{id}/{login}" )
	public ResponseEntity<Response<UserPostDTO>> deleteUserPost( @RequestParam Long id, @RequestParam String login ) {
		
		Response<UserPostDTO> response = new Response<UserPostDTO>();
		
		try {
			
			Optional<UserPost> userPost = userPostService.findById( id );
			
			if( !userPost.isPresent() ) {
				response.setContent( Arrays.asList( "Post não encontrado!" ) );
				return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( response );
			}
			
			Optional<Users> user = userService.findByLogin( login );
			
			if( !user.isPresent() ) {
				response.setContent( Arrays.asList( "Usuário não encontrado!" ) );
				return ResponseEntity.status( HttpStatus.NOT_FOUND ).body( response );
			}
			
			
			if( UtilValidation.isSameUsers( user.get().getId(), userPost.get().getBlogUser().getId() ) ) {
			
				Boolean removed = userPostService.deleteUserPost( userPost.get() );
				
				if( removed ) {
					response.setContent( Arrays.asList( "Post excluído com sucesso!" ) );
					return ResponseEntity.ok( response ); 
				}
				response.setContent( Arrays.asList( "Post não foi excluido, aconteceu algum imprevisto!" ) );
				return ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( response );
			}
			else {
				response.setContent( Arrays.asList( "Usuário não tem permissão para deletar o post!" ) );
				return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( response );
			}

		} catch (Exception e) {
			response.setContent( Arrays.asList( e.getMessage() ) );
			return 	ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( response );
		}
	}
	
	@ApiOperation( value = "Exclui um Post específico" )
	@PutMapping( name="/put", value = "/{id}" )
	public ResponseEntity<Response<UserPostDTO>> updateUserPost( @RequestParam Long id, @RequestBody UserPostDTO userPostDTO ) {
		
		Response<UserPostDTO> response = new Response<UserPostDTO>();
		
		try {
			
			Optional<UserPost> result = userPostService.findById( id );
			
			if( result.isPresent() ) {

				userPostService.updateUserPost( updateEntity( userPostDTO, result.get() ) );
				response.setData( userPostConverter.transformEntityToDto( result.get() ) );
				response.setContent( Arrays.asList( "Post atualizado com sucesso!" ) );
				return ResponseEntity.ok( response );
			}
			
			response.setContent( Arrays.asList( "Post não encontrado!" ) );
			return ResponseEntity.ok( response );

		} catch (Exception e) {
			response.setContent( Arrays.asList( e.getMessage() ) );
			return 	ResponseEntity.status( HttpStatus.BAD_REQUEST ).body( response );
		}
	}

	
	/**
	 * Atualiza a entidade com os novos valores possíveis para update
	 * 
	 * @param userPostDTO
	 * @param userPost
	 * @return UserPost
	 */
	private UserPost updateEntity(UserPostDTO userPostDTO, UserPost userPost) {
		
		userPost.setImage( userPostDTO.getImage() );
		userPost.setText( userPostDTO.getText() );
		userPost.setTitle( userPostDTO.getTitle() );
		userPost.setUrl( userPostDTO.getUrl() );
		
		return userPost;
	}

	private List<UserPostDTO> transformListEntityToDto(List<UserPost> listUserPost) {

		return listUserPost.stream().map( userPost -> userPostConverter.transformEntityToDto( userPost ) )
				.collect( ArrayList::new, ArrayList::add, ArrayList::addAll );
	}

}
