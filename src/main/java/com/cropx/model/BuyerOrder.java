package com.cropx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name="buyer_orders")
public class BuyerOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String buyer;
    private String phone;
    @ManyToOne(optional=false) private Crop crop;
    @NotNull @DecimalMin("0.01") private Double quantity;
    @NotNull @DecimalMin("0.0") private BigDecimal unitPrice;
    private String status = "Pending";

    public BuyerOrder() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getBuyer(){return buyer;} public void setBuyer(String buyer){this.buyer=buyer;}
    public String getPhone(){return phone;} public void setPhone(String phone){this.phone=phone;}
    public Crop getCrop(){return crop;} public void setCrop(Crop crop){this.crop=crop;}
    public Double getQuantity(){return quantity;} public void setQuantity(Double quantity){this.quantity=quantity;}
    public BigDecimal getUnitPrice(){return unitPrice;} public void setUnitPrice(BigDecimal unitPrice){this.unitPrice=unitPrice;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
}
