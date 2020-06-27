package com.pizza.api.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "Address")
@Table(name="address")
// @EntityListeners(AuditingEntityListener.class)
public class Address extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @Column(name="line_1", nullable = false) @Getter @Setter private String line1;
    @Column(name="line_2") @Getter @Setter private String line2;
    @Column(name="line_3") @Getter @Setter private String line3;
    @Column(name="city", nullable = false) @Getter @Setter private String city;
    @Column(name="state", length=2, nullable = false) @Getter @Setter private String state;
    @Column(name="zipcode", nullable = false) @Getter @Setter private String zipcode;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @Getter @Setter
    private Customer customer;

    // Constructors

    public Address(Long id, String line1, String line2, String line3, String city, String state, String zipcode, Customer customer) {
        super();
        this.id = id;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.customer = customer;
    }

    public Address(Long id, String line1, String line2, String line3, String city, String state, String zipcode) {
        super();
        this.id = id;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Address() {
        super();
    }

    // Handling  many-to-one relationship 

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        return id != null && id.equals( ((Address) o).getId() );
    }

        // Required as explained by: https://vladmihalcea.com/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
        // "Equality must be consistent across all entity state transitions"

    @Override
    public int hashCode() {
        return 55;
    }

    @Override
    public String toString() {
        return "Address ID: " + id + "\n" +
            "Customer: " + customer.getFirstName() + " " + customer.getLastName() + "\n" +
            "Line 1: " + line1 + "\n" +
            "Line 2: " + line2 + "\n" +
            "Line 3: " + line3 + "\n" +
            "City: " + city + "\n" +
            "State: " + state + "\n" +
            "Zipcode: " + zipcode;
    }

}
