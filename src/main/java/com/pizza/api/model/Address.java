package com.pizza.api.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "Address")
@Table(name="address")
@EntityListeners(AuditingEntityListener.class)
public class Address {

    @Id
    @Column(name="address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;

    @Column(name="line_1", nullable = false) @Getter @Setter private String line1;
    @Column(name="line_2") @Getter @Setter private String line2;
    @Column(name="line_3") @Getter @Setter private String line3;
    @Column(name="city", nullable = false) @Getter @Setter private String city;
    @Column(name="state", length=2, nullable = false) @Getter @Setter private String state;
    @Column(name="zipcode", nullable = false) @Getter @Setter private String zipcode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    // Constructors

    public Address(Long addressId, String line1, String line2, String line3, String city, String state, String zipcode, Customer customer) {
        super();
        this.addressId = addressId;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.customer = customer;
    }

    public Address() {
        super();
    }

    // Extra Getters/Setters

    public Long getAddressId() {
        return this.addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Handling  many-to-one relationship 

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        return addressId != null && addressId.equals( ((Address) o).getAddressId() );
    }

        // Required as explained by: https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
        // "Equality must be consistent across all entity state transitions"

    @Override
    public int hashCode() {
        return 55;
    }
    
}