package com.example.demojpasvc.sample;

import com.example.demojpasvc.customer.Customer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "SAMPLE")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String org;
    private String name;
    private String code;
    private String description;
    private double price;
    private double tax;
    private double start;
    private double end;
    private double total;
    private int quantity;
    private int weight;
    private String batchName;
    private String batchCode;
    private String unit;
    private String brand;
    private String qrCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @Basic
    private Instant createDate;
}
