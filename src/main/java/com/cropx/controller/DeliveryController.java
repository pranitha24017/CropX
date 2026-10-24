package com.cropx.controller;

import com.cropx.model.BuyerOrder;
import com.cropx.model.Delivery;
import com.cropx.repo.DeliveryRepository;
import com.cropx.repo.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final DeliveryRepository deliveries; private final OrderRepository orders;
    public DeliveryController(DeliveryRepository deliveries,OrderRepository orders){this.deliveries=deliveries;this.orders=orders;}
    @GetMapping public List<Delivery> all(){return deliveries.findAll();}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Delivery create(@Valid @RequestBody DeliveryRequest r){
        BuyerOrder order=orders.findById(r.orderId()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Order not found"));
        if(deliveries.findAll().stream().anyMatch(d->d.getBuyerOrder().getId().equals(order.getId()))) throw new ResponseStatusException(HttpStatus.CONFLICT,"This order already has a delivery");
        Delivery d=new Delivery();d.setBuyerOrder(order);d.setExpectedDate(r.expectedDate());d.setStatus(r.status()==null||r.status().isBlank()?"Pending":r.status());d.setDriver(r.driver());return deliveries.save(d);
    }
    @PutMapping("/{id}") public Delivery update(@PathVariable Long id,@Valid @RequestBody DeliveryRequest r){
        Delivery d=deliveries.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Delivery not found"));
        BuyerOrder order=orders.findById(r.orderId()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Order not found"));
        d.setBuyerOrder(order);d.setExpectedDate(r.expectedDate());d.setStatus(r.status());d.setDriver(r.driver());return deliveries.save(d);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){deliveries.deleteById(id);}
    public record DeliveryRequest(@NotNull Long orderId,@NotNull LocalDate expectedDate,String status,String driver){}
}
