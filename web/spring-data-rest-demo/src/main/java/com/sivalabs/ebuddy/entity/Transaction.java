package com.sivalabs.ebuddy.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {

    @Id
    @SequenceGenerator(name = "txn_id_generator", sequenceName = "txn_id_seq")
    @GeneratedValue(generator = "txn_id_generator")
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    public enum Type {
        INCOME,
        EXPENSE
    }
}
