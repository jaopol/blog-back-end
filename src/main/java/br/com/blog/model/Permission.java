package br.com.blog.model;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
//TODO poderá ser persistida futuramente
//@Entity
//@Table( name = "permission" )
public class Permission implements GrantedAuthority, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//@Id
	//@GeneratedValue( strategy = GenerationType.AUTO )
	//@Column( name = "id" )
	private Long id;
	
	//@Column( name = "discription" )
	private String discription;
	
	//@Override
	public String getAuthority() {
		return this.discription;
	}

}
