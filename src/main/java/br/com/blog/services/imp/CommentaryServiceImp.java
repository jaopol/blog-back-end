package br.com.blog.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blog.model.Commentary;
import br.com.blog.repositories.CommentaryRepository;
import br.com.blog.services.CommentaryService;

/**
 * @author jp
 *
 */
@Service
public class CommentaryServiceImp implements CommentaryService {
	
	@Autowired
	private CommentaryRepository commentaryRepository;

	@Override
	public Optional<Commentary> addCommentary(Commentary commentary) {
		return Optional.ofNullable( commentaryRepository.save( commentary ) );
	}

	@Override
	public Optional<List<Commentary>> findAll() {
		return Optional.ofNullable( commentaryRepository.findAll() );
	}

	@Override
	public Boolean deleteCommentary(Long id) {
		
		Optional<Commentary> commentary = commentaryRepository.findById( id );
		
		if( commentary.isPresent() ) {
			commentaryRepository.delete( commentary.get() );
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

	

	
}
