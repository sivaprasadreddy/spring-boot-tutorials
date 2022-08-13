package com.sivalabs.bookmarker.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="roles")
@Setter
@Getter
public class Role extends BaseEntity implements Serializable
{

	@Id
	@SequenceGenerator(name = "role_id_generator", sequenceName = "role_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "role_id_generator")
	private Long id;
	
	@Column(nullable=false, unique=true)
	@NotEmpty
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	private List<User> users;

}
