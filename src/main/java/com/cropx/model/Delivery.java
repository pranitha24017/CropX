package com.cropx.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional=false) private BuyerOrder buyerOrder;
    @NotNull private LocalDate expectedDate;
    private String status = "Pending";
    private String driver;

    public Delivery() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public BuyerOrder getBuyerOrder(){return buyerOrder;} public void setBuyerOrder(BuyerOrder buyerOrder){this.buyerOrder=buyerOrder;}
    public LocalDate getExpectedDate(){return expectedDate;} public void setExpectedDate(LocalDate expectedDate){this.expectedDate=expectedDate;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
    public String getDriver(){return driver;} public void setDriver(String driver){this.driver=driver;}
}
