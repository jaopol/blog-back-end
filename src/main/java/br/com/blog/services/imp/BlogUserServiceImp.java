package br.com.blog.services.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.blog.model.BlogUser;
import br.com.blog.repositories.BlogUserRepository;
import br.com.blog.services.BlogUserService;

/**
 * @author jp
 *
 */
@Service
public class BlogUserServiceImp implements BlogUserService {

	@Autowired
	private BlogUserRepository blogUserRepository;
	
	
	/**
	 *Grava um usuário do blog
	 */
	@Override
	public Optional<BlogUser> addBlogUser(BlogUser blogUser) {
		return Optional.ofNullable( this.blogUserRepository.save( blogUser ) );
	}


	/**
	 *Reupera o usuario pelo login
	 */
	@Override
	public Optional<BlogUser> findByLogin(String login) {
		return Optional.ofNullable( this.blogUserRepository.findByLogin( login ) );
	}

}
