package at.htl.vehicle.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "V_CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "C_ID")
    Long id;
    @Column(name = "C_NAME")
    private String name;
    @Column(name = "C_dob")
    private LocalDate dob;

    public Customer() {
    }

    public Customer(String name, LocalDate dob) {
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
