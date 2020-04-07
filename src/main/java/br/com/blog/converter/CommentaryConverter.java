package br.com.blog.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.blog.dtos.CommentaryDTO;
import br.com.blog.model.Commentary;

/**
 * @author jp
 *
 */
@Component
public class CommentaryConverter {
	
	@Autowired
	private UserPostConverter userPostConverter;
	
	@Autowired
	private BlogUsersConverter blogUserConverter;

	public CommentaryDTO transformEntityToDto( Commentary entity ) {

		CommentaryDTO dto = new CommentaryDTO();
		
		if( entity != null ) {
			dto.setId( entity.getId() );
			dto.setText( entity.getText() );
			dto.setCreatedDate( entity.getCreatedDate() );
			dto.setBlogUserDTO( blogUserConverter.transformEntityToDto( entity.getBlogUser() ) );
			dto.setUserPostDTO( userPostConverter.transformEntityToDto( entity.getUserPost() ) );
		}
		
		return dto;
	}

	public Commentary transformDtoToEntity( CommentaryDTO dto ) {
		
		Commentary entity = new Commentary();
		
		if( entity != null ) {
			entity.setId( dto.getId() );
			entity.setText( dto.getText() );
			entity.setCreatedDate( dto.getCreatedDate() );
			entity.setBlogUser( blogUserConverter.transformDtoToEntity( dto.getBlogUserDTO() ) );
			entity.setUserPost( userPostConverter.transformDtoToEntity( dto.getUserPostDTO() ) );
		}

		return entity;
	}

	
}
