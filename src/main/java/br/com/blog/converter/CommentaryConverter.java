package br.com.blog.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.com.blog.dtos.CommentaryDTO;
import br.com.blog.model.Commentary;

/**
 * @author jp
 *
 */
@Component
public class CommentaryConverter {
	

	public CommentaryDTO transformEntityToDto( Commentary entity ) {

		CommentaryDTO dto = new CommentaryDTO();
		
		if( entity != null ) {
			dto.setId( entity.getId() );
			dto.setText( entity.getText() );
			dto.setCreatedDate( entity.getCreatedDate() );	
		}
		
		return dto;
	}

	public Commentary transformDtoToEntity( CommentaryDTO dto ) {
		
		Commentary entity = new Commentary();
		
		if( entity != null ) {
			entity.setId( dto.getId() );
			entity.setText( dto.getText() );
			entity.setCreatedDate( dto.getCreatedDate() );
			//entity.setBlogUser( blogUserConverter.transformDtoToEntity( dto.getBlogUserDTO() ) );
			//entity.setUserPost( userPostConverter.transformDtoToEntity( dto.getUserPostDTO() ) );
		}

		return entity;
	}

	public List<CommentaryDTO> transformListEntityToDto(Optional<List<Commentary>> listCommentary) {
		
		if( listCommentary.isPresent() && !CollectionUtils.isEmpty( listCommentary.get() ) ) {
			return listCommentary.get().stream().map( commentary -> transformEntityToDto( commentary ) )
					.collect( ArrayList::new, ArrayList::add, ArrayList::addAll );
		}
		return null;
		
	}

	
}
