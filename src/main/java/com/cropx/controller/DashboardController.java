package com.cropx.controller;
import com.cropx.repo.*;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final CropRepository crops; private final OrderRepository orders; private final DeliveryRepository deliveries;
    public DashboardController(CropRepository c,OrderRepository o,DeliveryRepository d){crops=c;orders=o;deliveries=d;}
    @GetMapping public Map<String,Object> summary(){
        BigDecimal value=crops.findAll().stream().map(c->c.getPrice().multiply(BigDecimal.valueOf(c.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal::add);
        long active=deliveries.findAll().stream().filter(d->!d.getStatus().equalsIgnoreCase("Delivered")&&!d.getStatus().equalsIgnoreCase("Cancelled")).count();
        return Map.of("cropCount",crops.count(),"orderCount",orders.count(),"activeDeliveries",active,"cropValue",value);
    }
}
