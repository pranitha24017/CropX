package com.cropx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Crop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String name;
    @NotNull @DecimalMin("0.0") private Double quantity;
    @NotNull @DecimalMin("0.0") private BigDecimal price;
    private String location;
    private String status = "Available";

    public Crop() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public Double getQuantity(){return quantity;} public void setQuantity(Double quantity){this.quantity=quantity;}
    public BigDecimal getPrice(){return price;} public void setPrice(BigDecimal price){this.price=price;}
    public String getLocation(){return location;} public void setLocation(String location){this.location=location;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
}
