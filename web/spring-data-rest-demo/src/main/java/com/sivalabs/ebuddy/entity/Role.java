package com.sivalabs.ebuddy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Id
    @SequenceGenerator(name = "role_id_generator", sequenceName = "role_id_seq")
    @GeneratedValue(generator = "role_id_generator")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

}
