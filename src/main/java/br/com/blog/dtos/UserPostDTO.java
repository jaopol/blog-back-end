package br.com.blog.dtos;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserPostDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String title;
	
	private String url;
	
	private String text;
	
	private Byte[] image;	
	
	private BlogUserDTO blogUser;
	
	private Date createdDate;
}
