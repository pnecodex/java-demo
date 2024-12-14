package com.example.demo.services;

import com.example.demo.entity.Products;
import com.example.demo.exception.ProductsNotFoundException;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {
    @Autowired private ProductRepository repo;

    public List<Products> listProducts(){
        return  (List<Products>) repo.findAll();
    }

    public void save(Products products) {
        repo.save(products);
    }

    public Products editProduct(Long id) throws ProductsNotFoundException {
     Optional<Products> data = repo.findById(id);
     if (data.isPresent()){
         return data.get();
     }
     throw new ProductsNotFoundException("Cloud not find any product" + id);
    }

    public void delete(Long id) throws ProductsNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new ProductsNotFoundException("Cloud not find any product with ID" + id);
        }
        repo.deleteById(id);
    }
}
