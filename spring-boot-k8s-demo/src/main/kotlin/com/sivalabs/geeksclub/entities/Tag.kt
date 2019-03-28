package com.sivalabs.geeksclub.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "tags")
class Tag {
    @Id
    @SequenceGenerator(name = "tag_generator", sequenceName = "tag_sequence", initialValue = 100)
    @GeneratedValue(generator = "tag_generator")
    var id: Long? = null

    @Column(nullable = false, unique = true)
    var name: String = ""

    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    var links: MutableList<Link> = mutableListOf()

    override fun toString(): String {
        return "Tag(id=$id, name='$name')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tag

        if (id != other.id) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + name.hashCode()
        return result
    }


}
