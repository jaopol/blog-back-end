package br.com.blog.services.imp;

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
		return Optional.ofNullable( this.postRepository.save( post ) );
	}

}
