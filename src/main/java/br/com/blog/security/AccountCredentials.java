package br.com.blog.security;

import java.io.Serializable;

import lombok.Data;

/**
 * @author jp
 *
 */
@Data
public class AccountCredentials implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;

}
