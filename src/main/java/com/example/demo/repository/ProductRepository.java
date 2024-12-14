package com.example.demo.repository;

import com.example.demo.entity.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Products,Long> {
    public Long countById(Long id);
}
