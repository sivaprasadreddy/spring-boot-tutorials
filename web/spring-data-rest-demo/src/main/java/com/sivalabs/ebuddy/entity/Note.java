package com.sivalabs.ebuddy.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "notes")
public class Note extends BaseEntity {

    @Id
    @SequenceGenerator(name = "note_id_generator", sequenceName = "note_id_seq")
    @GeneratedValue(generator = "note_id_generator")
    private Long id;
    @NotEmpty(message = "Text should not be empty")
    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}
