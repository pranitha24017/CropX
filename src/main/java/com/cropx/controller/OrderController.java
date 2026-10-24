package com.cropx.controller;

import com.cropx.model.BuyerOrder;
import com.cropx.model.Crop;
import com.cropx.repo.CropRepository;
import com.cropx.repo.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository orders; private final CropRepository crops;
    public OrderController(OrderRepository orders,CropRepository crops){this.orders=orders;this.crops=crops;}
    @GetMapping public List<BuyerOrder> all(){return orders.findAll();}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public BuyerOrder create(@Valid @RequestBody OrderRequest r){
        Crop crop=crops.findById(r.cropId()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Crop not found"));
        BuyerOrder o=new BuyerOrder();o.setBuyer(r.buyer());o.setPhone(r.phone());o.setCrop(crop);o.setQuantity(r.quantity());o.setUnitPrice(crop.getPrice());o.setStatus(r.status()==null||r.status().isBlank()?"Pending":r.status());return orders.save(o);
    }
    @PutMapping("/{id}") public BuyerOrder update(@PathVariable Long id,@Valid @RequestBody OrderRequest r){
        BuyerOrder o=orders.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found"));
        Crop crop=crops.findById(r.cropId()).orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Crop not found"));
        o.setBuyer(r.buyer());o.setPhone(r.phone());o.setCrop(crop);o.setQuantity(r.quantity());o.setUnitPrice(crop.getPrice());o.setStatus(r.status());return orders.save(o);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){orders.deleteById(id);}
    public record OrderRequest(@jakarta.validation.constraints.NotBlank String buyer,String phone,@NotNull Long cropId,@NotNull @jakarta.validation.constraints.DecimalMin("0.01") Double quantity,String status){}
}
