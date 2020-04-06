package br.com.blog.services;

import java.util.Optional;

import br.com.blog.model.BlogUser;


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
	Optional<BlogUser>  addBlogUser( BlogUser blogUser );
	
	Optional<BlogUser>  findByLogin( String login );
	
}
