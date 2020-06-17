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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Column(name = "first_name", nullable = false) @Getter @Setter private String firstName;
    @Column(name = "last_name", nullable = false) @Getter @Setter private String lastName;
    @Column(name = "phone_number", nullable = false) @Getter @Setter private String phoneNumber;
    @Column(name = "email", nullable = false) @Getter @Setter private String email;
    @Column(name = "password", nullable = false) @Getter @Setter private String password;

    @Column(name = "created_at", nullable = false) @CreatedDate @Getter @Setter private Date createdAt;
    @Column(name = "created_by", nullable =  false) @CreatedBy private String createdBy;
    @Column(name = "last_modified_date", nullable = false) @LastModifiedDate private Date lastModifiedDate;
    @Column(name = "last_modified_by", nullable = false) @LastModifiedBy private String lastModifiedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Address.class)
    @JoinColumn(name = "customer_id")
    @Getter @Setter
    private List<Address> addresses;

    // Constructors

    public Customer(Long id, String firstName, String lastName, String phoneNumber, String email, String password,
                    Date createdAt, String createdBy, Date lastModifiedDate, String lastModifiedBy, List<Address> addresses) {
        
            super();
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.createdAt = createdAt;
            this.createdBy = createdBy;
            this.lastModifiedDate = lastModifiedDate;
            this.lastModifiedBy = lastModifiedBy;
            this.addresses = addresses;
        }

    public Customer() {
        super();
    }

    public void addAddress(Address address) {
        if (this.addresses == null) {
            this.addresses = new ArrayList<>();
        }
        addresses.add(address);
        address.setCustomer(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
    }

    public void removeAllAddresses() {
        addresses.clear();
    }
    
}
