package by.it_academy.calorie_diary.controllers;

import by.it_academy.calorie_diary.entity.Product;
import by.it_academy.calorie_diary.services.api.IProductService;
import by.it_academy.calorie_diary.services.dto.PageDTO;
import by.it_academy.calorie_diary.services.dto.product.ProductDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @PostMapping
    protected ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO data) {
        ProductDTO created = this.service.create(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    protected ResponseEntity<ProductDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok(service.read(id));
    }

    @GetMapping
    protected ResponseEntity<PageDTO<Product>> getList(Pageable pageable) {
        return ResponseEntity.ok(service.get(pageable));
    }

    @PutMapping("/{id}/update_date/{update_date}")
    protected ResponseEntity<Product> doPut(@PathVariable UUID id,
                                         @PathVariable("update_date") long updateTime,
                                         @RequestBody ProductDTO data) {
        LocalDateTime updateDate = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(updateTime),
                ZoneId.of("UTC")
        );
        return ResponseEntity.ok(this.service.update(data, id, updateDate));
    }
}