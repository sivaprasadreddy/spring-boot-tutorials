package com.sivalabs.ebuddy.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
abstract class BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn = new Date();

}
