package com.pizza.api.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "Customer")
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    // Columns

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long customerId;

    @Column(name = "first_name", nullable = false) @Getter @Setter private String firstName;
    @Column(name = "last_name", nullable = false) @Getter @Setter private String lastName;
    @Column(name = "phone_number", nullable = false) @Getter @Setter private String phoneNumber;
    @Column(name = "email", nullable = false) @Getter @Setter private String email;

    @Column(name = "created_at", nullable = false) @CreatedDate @Getter @Setter private Date createdAt;
    @Column(name = "created_by", nullable =  false) @CreatedBy private String createdBy;
    @Column(name = "last_modified_date", nullable = false) @LastModifiedDate private Date lastModifiedDate;
    @Column(name = "last_modified_by", nullable = false) @LastModifiedBy private String lastModifiedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Address> addresses = new ArrayList<>();

    // Constructors

    public Customer(Long customerId, String firstName, String lastName, String phoneNumber, String email, 
                    Date createdAt, String createdBy, Date lastModifiedDate, String lastModifiedBy) {
        
            super();
            this.customerId = customerId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.createdAt = createdAt;
            this.createdBy = createdBy;
            this.lastModifiedDate = lastModifiedDate;
            this.lastModifiedBy = lastModifiedBy;
        }

    public Customer() {
        super();
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setCustomer(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setCustomer(null);
    }
    
}