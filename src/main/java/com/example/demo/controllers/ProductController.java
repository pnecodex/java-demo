package com.example.demo.controllers;

import com.example.demo.entity.Products;
import com.example.demo.exception.ProductsNotFoundException;
import com.example.demo.services.ProductServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
//@RestController
public class ProductController {
    @Autowired
    private ProductServices services;
    @GetMapping("/product")
    public String index (Model model){
        List<Products> listProducts = services.listProducts();
        model.addAttribute("listProducts", listProducts);
        return "index";
    }
    @GetMapping("/product/add")
    public String add (Model model) {
        model.addAttribute("products", new Products());
        return "add";
    }
    @PostMapping("/product/ave_product")
    public String saveProduct(@Validated Products products, HttpSession session){
        System.out.println(session);
        services.save(products);
        return "redirect:/";
    }
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        try {
            Products products = services.editProduct(id);
            model.addAttribute("products",products);
            return "add";
        }catch (ProductsNotFoundException e){
            return "redirect:/product";
        }
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes ra){
        try {
            services.delete(id);
        }catch (ProductsNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/";
    }

    @GetMapping("/api/products")
    public List<Products> allProducts() {
        List<Products> list;
        list = services.listProducts();
        return list;
    }
}
