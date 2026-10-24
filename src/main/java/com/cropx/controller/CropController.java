package com.cropx.controller;

import com.cropx.model.Crop;
import com.cropx.repo.CropRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController
@RequestMapping("/api/crops")
public class CropController {
    private final CropRepository repo;
    public CropController(CropRepository repo){this.repo=repo;}
    @GetMapping public List<Crop> all(){return repo.findAll();}
    @GetMapping("/{id}") public Crop one(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Crop not found"));}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Crop create(@Valid @RequestBody Crop crop){crop.setId(null);return repo.save(crop);}
    @PutMapping("/{id}") public Crop update(@PathVariable Long id,@Valid @RequestBody Crop input){Crop c=one(id);c.setName(input.getName());c.setQuantity(input.getQuantity());c.setPrice(input.getPrice());c.setLocation(input.getLocation());c.setStatus(input.getStatus());return repo.save(c);}
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){repo.delete(one(id));}
}
