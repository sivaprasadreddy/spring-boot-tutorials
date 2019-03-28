package com.sivalabs.geeksclub.entities

import javax.persistence.*

@Entity
@Table(name = "categories")
class Category {
    @Id
    @SequenceGenerator(name = "cat_generator", sequenceName = "cat_sequence", initialValue = 100)
    @GeneratedValue(generator = "cat_generator")
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var name: String = ""

    @Column(nullable = false, unique = true)
    var label: String = ""

    override fun toString(): String {
        return "Category(id=$id, name='$name', label='$label')"
    }

}