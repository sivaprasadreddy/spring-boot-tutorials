package com.sivalabs.springmongodbdemo.documents;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="people")
public class Person {
    @Id
    //@Field("id")
    private String id;

    private String firstName;

    private String lastName;
    @Indexed(unique = true)
    private String email;
    private String phone;
    private Address address;

    @CreatedBy
    private String createdBy;

    @Field("created_date")
    @CreatedDate
    private Instant createdDate;

    @Version
    private Long version;
}
