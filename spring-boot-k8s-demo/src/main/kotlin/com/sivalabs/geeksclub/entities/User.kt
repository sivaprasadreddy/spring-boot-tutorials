package com.sivalabs.geeksclub.entities

import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "users")
class User {
    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", initialValue = 10)
    @GeneratedValue(generator = "user_generator")
    var id: Long? = null

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email address is invalid")
    var email: String = ""

    @Column(nullable = false)
    @NotBlank(message = "Password must not be blank")
    var password: String = ""

    @Column(nullable = false)
    @NotBlank(message = "Name must not be blank")
    var name: String = ""

    @Column(nullable = true)
    var website: String = ""

    @Column(nullable = true, length = 4000)
    var bio: String = ""

    @Column(nullable = false)
    var role: String = ""
}