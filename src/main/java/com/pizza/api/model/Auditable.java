package com.pizza.api.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * From: https://github.com/njnareshjoshi/articles/blob/master/spring-data-jpa-auditing/src/main/java/org/programming/mitra/Auditable.java
 * @param <U>
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
abstract class Auditable<U> {

    @Column(name="created_by", updatable = false)
    @CreatedBy
    @Getter @Setter
    protected U createdBy;

    @Column(name="created_date", updatable = false)
    @CreatedDate
    @Temporal(TIMESTAMP)
    @Getter @Setter
    protected Date createdDate;

    @LastModifiedBy
    @Getter @Setter
    protected U lastModifiedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Getter @Setter
    protected Date lastModifiedDate;

}
