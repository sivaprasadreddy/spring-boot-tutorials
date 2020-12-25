package com.sivalabs.todolist.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="roles")
@Data
public class Role implements Serializable
{

	@Id
	@SequenceGenerator(name="role_generator", sequenceName="role_sequence", allocationSize = 1)
	@GeneratedValue(generator = "role_generator")
	private Long id;
	
	@Column(nullable=false, unique=true)
	@NotEmpty
	private String name;
		
	@ManyToMany(mappedBy="roles")
	private List<User> users;

}
