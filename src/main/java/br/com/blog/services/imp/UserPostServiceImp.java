package br.com.blog.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blog.model.UserPost;
import br.com.blog.repositories.UserPostRepository;
import br.com.blog.services.UserPostService;

/**
 * @author jp
 *
 */
@Service
public class UserPostServiceImp implements UserPostService {

	@Autowired
	private UserPostRepository postRepository;
	

	/**
	 *Grava um post do usuário
	 */
	@Override
	public Optional<UserPost> addPost(UserPost post) {
		return Optional.ofNullable( postRepository.save( post ) );
	}


	/**
	 *Retorna todos os posts do blog
	 */
	@Override
	public Optional<List<UserPost>> findAll() {
		return Optional.ofNullable( postRepository.findAll() );
	}


	/**
	 *Deleta um Post específico
	 */
	@Override
	public Boolean deleteUserPost(Long id) {

		Optional<UserPost> userPost = postRepository.findById( id );
		
		if( userPost.isPresent() ) {
			postRepository.delete( userPost.get() );
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

}
