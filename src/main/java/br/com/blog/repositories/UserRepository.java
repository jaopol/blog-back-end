package br.com.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.blog.model.Users;

/**
 * @author jp
 *  Interface para JWT
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	
	@Query( "SELECT obj FROM Users obj WHERE obj.username =:username " )
	Users findByUsername( @Param("username") String username );

}
