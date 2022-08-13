package com.sivalabs.bookmarker.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="users")
@Setter
@Getter
public class User extends BaseEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "user_id_generator")
	private Long id;

	@Column(nullable=false)
	@NotEmpty()
	private String name;

	@Column(nullable=false, unique=true)
	@NotEmpty
	@Email(message="Invalid email")
	private String email;
	
	@Column(nullable=false)
	@NotEmpty
	@Size(min=4)
	private String password;

	@Column
	private String imageUrl;

	@Column
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(
	      name="user_role",
	      joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
	      inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	private Set<Role> roles;
	
}
