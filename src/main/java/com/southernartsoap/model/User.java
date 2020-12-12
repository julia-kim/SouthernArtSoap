package com.southernartsoap.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User implements UserDetails{
	
	
	  @Transient
	  private boolean accountNonExpired = true;
	  @Transient
	  private boolean accountNonLocked = true;
	  @Transient
	  private boolean credentialsNonExpired = true;
	  @Transient
	  private boolean enabled = true;
	  @Transient
	  private Collection<GrantedAuthority> authorities = null;
	

	  
	  
	  
	
	private int active;
	
	@CreationTimestamp 
	private Date createdAt;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	
	@Length(min = 3, message = "Your username must have at least 3 characters")
	@Length(max = 15, message = "Your username cannot have more than 15 characters")
	@Pattern(regexp="[^\\s]+", message="Your username cannot contain spaces")
	private String username;
	
	@NotEmpty(message = "Please provide your first name")
	private String firstName;
	
	@NotEmpty(message = "Please provide your last name")
	private String lastName;
	
	@Email(message = "Please provide a valid email")
	@NotEmpty(message = "Please provide an email")
	private String email;
	
	@Length(min = 5, message = "Your password must have at least 5 characters")
	private String password;
        
        @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "cart_id")
        private Cart cart;
	
	
}