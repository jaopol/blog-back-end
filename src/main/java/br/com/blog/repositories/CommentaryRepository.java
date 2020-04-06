package br.com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blog.model.Commentary;

/**
 * @author jp
 *
 */
@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

}
