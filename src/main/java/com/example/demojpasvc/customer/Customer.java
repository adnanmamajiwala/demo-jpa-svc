package com.example.demojpasvc.customer;

import com.example.demojpasvc.sample.Sample;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phone;
    private String city;
    private String state;
    private String zipcode;

    @OneToMany
    @JoinColumn(name = "customer_id")
    @JsonManagedReference
    private List<Sample> samples;

}
