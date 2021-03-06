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
	public Boolean deleteUserPost( UserPost userPost ) {

		try {
			postRepository.delete( userPost );
			return Boolean.TRUE;
		}
		catch (Exception e) {
			return false;
		}
		
	}

	/**
	 *Atualiza o post do usuário
	 */
	@Override
	public Optional<UserPost> updateUserPost(UserPost userPost) {
		return Optional.ofNullable( postRepository.save( userPost ) );
	}


	/**
	 *Recuper um post específico
	 */
	@Override
	public Optional<UserPost> findById(Long id) {
		return postRepository.findById( id );
	}


	/**
	 *Recupera os Posts pelo texto
	 */
	@Override
	public Optional<List<UserPost>> findByText(String text) {
		return postRepository.findByTextLike( "%"+text+"%" );
	}

}
