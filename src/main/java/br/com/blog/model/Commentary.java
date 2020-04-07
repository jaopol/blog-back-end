package br.com.blog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "commentary")
public class Commentary implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id" )
	private Long id;
	
	@Column( name = "text", nullable = false, length = 2000 )
	private String text;
	
	
	@Temporal(TemporalType.DATE)
	@Column( name = "date_created", nullable = false )
	private Date createdDate;
	
	@ManyToOne( fetch = FetchType.EAGER , targetEntity = UserPost.class )
	@JoinColumn( name = "id_user_post" )
	private UserPost userPost;
	
	@ManyToOne( fetch = FetchType.EAGER , targetEntity = Users.class )
	@JoinColumn( name = "id_blog_user" )
	private Users blogUser;
	

}
