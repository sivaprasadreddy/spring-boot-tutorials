package com.sivalabs.ebuddy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bookmarks")
public class Bookmark extends BaseEntity {

    @Id
    @SequenceGenerator(name = "bm_id_generator", sequenceName = "bm_id_seq")
    @GeneratedValue(generator = "bm_id_generator")
    private Long id;

    @NotEmpty(message = "Title should not be empty")
    @Column(nullable = false)
    private String title;

    @NotEmpty(message = "URL should not be empty")
    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}
