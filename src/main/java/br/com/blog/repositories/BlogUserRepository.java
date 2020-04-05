package br.com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.blog.model.BlogUser;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {
	
	

}
