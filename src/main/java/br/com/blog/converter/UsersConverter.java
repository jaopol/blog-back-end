package br.com.blog.converter;

import org.springframework.stereotype.Component;

import br.com.blog.dtos.BlogUserDTO;
import br.com.blog.model.Users;

/**
 * @author jp
 *
 */
@Component
public class UsersConverter {

	public BlogUserDTO transformEntityToDto(Users entity) {

		BlogUserDTO dto = new BlogUserDTO();

		if( entity != null ) {
			dto.setId( entity.getId() );
			dto.setName( entity.getFullName() );
			dto.setLogin( entity.getUsername() );
			dto.setPassword( entity.getPassword() );
			dto.setCreatedDate( entity.getCreatedDate() );
		}
		
		return dto;
	}

	public Users transformDtoToEntity(BlogUserDTO dto) {
		
		Users entity = new Users();

		if( dto != null ) {
			entity.setId(dto.getId());
			entity.setFullName( dto.getName() );
			entity.setUsername( dto.getLogin() );
			entity.setPassword( dto.getPassword() );
			entity.setCreatedDate( dto.getCreatedDate() );
		}

		return entity;
	}

	
}
