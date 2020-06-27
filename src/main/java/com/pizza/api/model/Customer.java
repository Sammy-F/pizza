package com.pizza.api.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Customer")
@Table(name = "customer")
public class Customer extends Auditable<String> {

    // Columns

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Column(name = "first_name", nullable = false) @Getter @Setter private String firstName;
    @Column(name = "last_name", nullable = false) @Getter @Setter private String lastName;
    @Column(name = "phone_number", nullable = false) @Getter @Setter private String phoneNumber;
    @Column(name = "email", nullable = false) @Getter @Setter private String email;
    @Column(name = "password", nullable = false) @Getter @Setter private String password;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Address.class)
    @Getter @Setter
    private List<Address> addresses;

    // Constructors

    // public Customer(Long id, String firstName, String lastName, String phoneNumber, String email, String password,
    //                 Date createdAt, String createdBy, Date lastModifiedDate, String lastModifiedBy, List<Address> addresses) {
        
    //     super();
    //     this.id = id;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.phoneNumber = phoneNumber;
    //     this.email = email;
    //     this.createdAt = createdAt;
    //     this.createdBy = createdBy;
    //     this.lastModifiedDate = lastModifiedDate;
    //     this.lastModifiedBy = lastModifiedBy;
    //     this.addresses = addresses;
    // }

    // public Customer(Long id, String firstName, String lastName, String phoneNumber, String email, String password,
    //                 Date createdAt, String createdBy, Date lastModifiedDate, String lastModifiedBy) {
        
    //     super();
    //     this.id = id;
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.phoneNumber = phoneNumber;
    //     this.email = email;
    //     this.createdAt = createdAt;
    //     this.createdBy = createdBy;
    //     this.lastModifiedDate = lastModifiedDate;
    //     this.lastModifiedBy = lastModifiedBy;
    // }

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
