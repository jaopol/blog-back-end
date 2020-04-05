package br.com.blog.dtos;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BlogUserDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "O Nome é obrigatório.")
	private String name;

	@NotEmpty(message = "O Login é obrigatório.")
	private String login;
	
	@NotEmpty(message = "O Password é obrigatório.")
	private String password;
	
	private Date createdDate;
}
