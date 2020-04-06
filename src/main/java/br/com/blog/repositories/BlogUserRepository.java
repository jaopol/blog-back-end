package br.com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.blog.model.BlogUser;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {
	
	@Query( "SELECT obj FROM BlogUser obj WHERE obj.login =:login " )
	BlogUser findByLogin( @Param("login") String login );

}
