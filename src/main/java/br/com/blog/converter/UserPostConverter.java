package br.com.blog.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.blog.dtos.UserPostDTO;
import br.com.blog.model.UserPost;

/**
 * @author jp
 *
 */
@Component
public class UserPostConverter {
	
	@Autowired
	private BlogUsersConverter blogUserConverter;

	public UserPostDTO transformEntityToDto( UserPost entity ) {

		UserPostDTO dto = new UserPostDTO();
		
		if( entity != null ) {
			dto.setId( entity.getId() );
			dto.setTitle( entity.getTitle() );
			dto.setText( entity.getText() );
			dto.setUrl( entity.getUrl() );
			dto.setBlogUser( blogUserConverter.transformEntityToDto( entity.getBlogUser() ) );
			dto.setImage( entity.getImage()  );
			dto.setCreatedDate( entity.getCreatedDate() );
		}
		
		return dto;
	}

	public UserPost transformDtoToEntity( UserPostDTO dto ) {
		
		UserPost entity = new UserPost();
		
		if( entity != null ) {
			entity.setId( dto.getId() );
			entity.setTitle( dto.getTitle() );
			entity.setText( dto.getText() );
			entity.setUrl( dto.getUrl() );
			entity.setImage( dto.getImage() );
			entity.setCreatedDate( dto.getCreatedDate() );
			entity.setBlogUser( blogUserConverter.transformDtoToEntity( dto.getBlogUser() ) ); 
		}

		return entity;
	}

	
}
