package br.com.blog.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.blog.model.Users;
import br.com.blog.repositories.UserRepository;

/**
 * @author jp
 * 
 * Serviço para JWT
 */
@Service
public class UserServiceImp implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = this.repository.findByUsername( username );
		if( user != null ) {
			return user;
		}
		else {
			throw new UsernameNotFoundException( "Usuário " + username + " não encontrado" );
		}
	}

}
