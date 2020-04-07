package br.com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.blog.model.Users;

@Repository
public interface BlogUserRepository extends JpaRepository<Users, Long> {
	
	@Query( "SELECT obj FROM Users obj WHERE obj.username =:login " )
	Users findByLogin( @Param("login") String login );

}
