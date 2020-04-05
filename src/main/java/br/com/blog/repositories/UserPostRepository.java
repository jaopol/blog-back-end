package br.com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blog.model.UserPost;

/**
 * @author jp
 *
 */
@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {

}
