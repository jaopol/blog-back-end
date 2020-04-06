package br.com.blog.services;

import java.util.List;
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
	 * @return UserPost
	 */
	Optional<UserPost> addPost( UserPost post );

	/**
	 * Retorna todos os posts do blog
	 * @return List
	 */
	Optional<List<UserPost>> findAll();

	/**
	 * Exclui um Post expecífico
	 * @param id
	 * @return Boolean - true : excluido, false : não excluido
	 */
	Boolean deleteUserPost( Long id );

	/**
	 * Atualiza o post do usuario
	 * @param id
	 * @return UserPost
	 */
	Optional<UserPost> updateUserPost( UserPost userPost );

	/**
	 * Recupera um Post específico
	 * @param id
	 * @return
	 */
	Optional<UserPost> findById( Long id );

	/**
	 * Recupera os Posts pelo texto
	 * 
	 * @param text
	 * @return List
	 */
	Optional<List<UserPost>> findByText( String text );

	
}
