package com.sivalabs.ebuddy.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class Todo extends BaseEntity {

    @Id
    @SequenceGenerator(name = "todo_id_generator", sequenceName = "todo_id_seq")
    @GeneratedValue(generator = "todo_id_generator")
    private Long id;

    @NotEmpty(message = "Text should not be empty")
    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean done;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}
