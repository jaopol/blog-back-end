package br.com.blog.dtos;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CommentaryDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String text;

	private UserPostDTO userPostDTO;
	
	private Date createdDate;
	
	private BlogUserDTO blogUserDTO;
}
