package br.com.blog.services;

import java.util.List;
import java.util.Optional;

import br.com.blog.model.Commentary;


/**
 * @author jp
 *
 */
public interface CommentaryService {

	/**
	 * Adiciona um comentário do usuário 
	 * @param post
	 * @return Commentary
	 */
	Optional<Commentary> addCommentary( Commentary commentary );

	/**
	 * Retorna todos os comentários do blog
	 * @return List
	 */
	Optional<List<Commentary>> findAll();

	/**
	 * Exclui um comentários específico
	 * @param commentary
	 * @return Boolean - true : excluido, false : não excluido
	 */
	Boolean deleteCommentary( Commentary commentary );

	/**
	 * Recupera um a comentário especifico
	 * @param id
	 * @return
	 */
	Optional<Commentary> findById( Long id );


	
}
