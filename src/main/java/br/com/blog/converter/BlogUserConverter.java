package br.com.blog.converter;

import org.springframework.stereotype.Component;

import br.com.blog.dtos.BlogUserDTO;
import br.com.blog.model.BlogUser;

/**
 * @author jp
 *
 */
@Component
public class BlogUserConverter {

	public BlogUserDTO transformEntityToDto(BlogUser entity) {

		BlogUserDTO dto = new BlogUserDTO();

		if( entity != null ) {
			dto.setId( entity.getId() );
			dto.setName( entity.getName() );
			dto.setLogin( entity.getLogin() );
			dto.setPassword( entity.getPassword() );
			dto.setCreatedDate( entity.getCreatedDate() );
		}
		
		return dto;
	}

	public BlogUser transformDtoToEntity(BlogUserDTO dto) {
		
		BlogUser entity = new BlogUser();

		if( dto != null ) {
			entity.setId(dto.getId());
			entity.setName( dto.getName() );
			entity.setLogin( dto.getLogin() );
			entity.setPassword( dto.getPassword() );
			entity.setCreatedDate( dto.getCreatedDate() );
		}

		return entity;
	}

	
}
