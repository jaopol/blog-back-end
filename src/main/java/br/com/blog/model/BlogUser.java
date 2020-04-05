package br.com.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "blog_user")
public class BlogUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id" )
	private Long id;
	
	@Column( name = "name", nullable = false, length = 100 )
	private String name;
	
	@Column( name = "login", nullable = false )
	private String login;
	
	@Column( name = "password", nullable = false )
	private String password;
	
	@Temporal(TemporalType.DATE)
	@Column( name = "date_created", nullable = false )
	private Date createdDate;
	
	@OneToMany( mappedBy = "blogUser", cascade = CascadeType.ALL )
	private List<UserPost> listPost;
	

}
