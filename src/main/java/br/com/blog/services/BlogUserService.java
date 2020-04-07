package br.com.blog.services;

import java.util.Optional;

import br.com.blog.model.Users;


/**
 * @author jp
 *
 */
public interface BlogUserService {

	/**
	 * Adiciona um usuário do blog 
	 * @param blogUser
	 * @return
	 */
	Optional<Users>  addBlogUser( Users blogUser );
	
	Optional<Users>  findByLogin( String login );
	
}
