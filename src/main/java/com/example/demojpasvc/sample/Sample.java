package com.example.demojpasvc.sample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
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

    @Basic
    private Instant createDate;
}
