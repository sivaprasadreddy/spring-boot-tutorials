package com.sivalabs.sbidemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String details;
    @Enumerated(EnumType.STRING)
    private Status status = Status.CREATED;
    private Date createdDate = new Date();

    enum Status {
        CREATED, DELIVERED, CANCELLED
    }
}
