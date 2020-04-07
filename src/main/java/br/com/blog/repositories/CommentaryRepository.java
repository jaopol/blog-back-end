package br.com.blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.blog.model.Commentary;

/**
 * @author jp
 *
 */
@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

	@Query( "SELECT obj FROM Commentary obj WHERE obj.userPost.id =:idPost " )
	Optional<List<Commentary>> findAllByIdPost(Long idPost);

}
