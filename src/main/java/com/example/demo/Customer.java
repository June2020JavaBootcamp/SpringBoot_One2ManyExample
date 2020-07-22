package com.example.demo;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

//    @OneToMany(mappedBy = "customer",
//               cascade = CascadeType.ALL,
//               orphanRemoval = true)

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER)
    private Set<Sale> sales;

    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}
