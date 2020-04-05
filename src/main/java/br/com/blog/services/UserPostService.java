package br.com.blog.services;

import java.util.Optional;

import br.com.blog.model.UserPost;


/**
 * @author jp
 *
 */
public interface UserPostService {

	/**
	 * Adiciona um post do usuário 
	 * @param post
	 * @return
	 */
	Optional<UserPost>  addPost( UserPost post );
	
}
