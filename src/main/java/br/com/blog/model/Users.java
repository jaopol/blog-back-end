package br.com.blog.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users implements UserDetails, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( name = "id" )
	private Long id;
	
	@Column( name = "full_name", nullable = false, length = 100 )
	private String fullName;
	
	@Column( name = "user_name", nullable = false, unique = true )
	private String username;
	
	@Column( name = "password", nullable = false )
	private String password;
	
	@Temporal(TemporalType.DATE)
	@Column( name = "date_created", nullable = false )
	private Date createdDate;
	
	@Column( name = "account_non_expired" )
	private boolean accountNonExpired;

	@Column( name = "account_non_locked" )
	private boolean accountNonLocked;

	@Column( name = "credentials_non_expired" )
	private boolean credentialsNonExpired;

	@Column( name = "enabled" )
	private boolean enabled;

	@ManyToMany( fetch = FetchType.EAGER  )
	@JoinTable( name = "user_permission", joinColumns = { @JoinColumn( name = "id_user" ) },
			inverseJoinColumns = { @JoinColumn( name = "id_permission" ) }  )
	private List<Permission> permissions;
	
	public List<String> getRoles(){
		
		List<String> roles = permissions.stream().map( obj -> obj.getDiscription() ).collect( Collectors.toList() );
		return roles;
	}
	
	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	

}
