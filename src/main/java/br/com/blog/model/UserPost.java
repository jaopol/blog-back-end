package br.com.blog.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "user_post")
public class UserPost implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id" )
	private Long id;
	
	@Column( name = "title", nullable = false, length = 200 )
	private String title;
	
	@Column( name = "url", nullable = true, length = 500 )
	private String url;
	
	@Column( name = "text", nullable = true, length = 5000 )
	private String text;
	
	@Column( name = "image", nullable = true, length = 5000 )
	private Byte[] image;	
	
	@ManyToOne( fetch = FetchType.EAGER , targetEntity = Users.class )
	@JoinColumn( name = "id_blog_user" )
	private Users blogUser;
	
	@Temporal( TemporalType.TIMESTAMP )
	@Column( name = "created_date", nullable = true, length = 5000 )
	private Date createdDate;
	
	@OneToMany( mappedBy = "userPost", cascade = CascadeType.ALL )
	private List<Commentary> commentary;
	
}
