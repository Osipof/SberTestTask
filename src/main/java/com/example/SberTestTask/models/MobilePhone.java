package com.example.SberTestTask.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "mobile_phones")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MobilePhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Integer price;
    private String color;
    private Integer amount;

    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    @Column(name = "color")
    public String getColor() {
        return color;
    }

    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }
}
