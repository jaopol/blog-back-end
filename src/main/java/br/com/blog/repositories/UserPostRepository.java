package br.com.blog.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.blog.model.UserPost;

/**
 * @author jp
 *
 */
@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {

	@Transactional(readOnly = true)
	Optional<List<UserPost>> findByTextLike(String text);

}
