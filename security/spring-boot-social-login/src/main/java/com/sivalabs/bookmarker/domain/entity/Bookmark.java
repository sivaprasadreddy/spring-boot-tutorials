package com.sivalabs.bookmarker.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "bookmarks")
@Setter
@Getter
public class Bookmark extends BaseEntity implements Serializable {
    @Id
    @SequenceGenerator(name = "bm_id_generator", sequenceName = "bm_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "bm_id_generator")
    private Long id;

    @Column(nullable=false)
    @NotEmpty()
    private String url;

    @Column(nullable=false)
    @NotEmpty()
    private String title;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

}
