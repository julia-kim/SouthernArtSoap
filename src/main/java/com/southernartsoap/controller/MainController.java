package com.southernartsoap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.southernartsoap.model.Product;
import com.southernartsoap.service.ProductService;

@Controller
@ControllerAdvice // This makes the `@ModelAttribute`s of this controller available to all controllers, for the navbar.
public class MainController {
    @Autowired
    ProductService productService;

    @GetMapping(value = "/")
    public String home() {
        return "index";
    }
    
    @ModelAttribute(value = "products")
    public List<Product> products() {
        return productService.findAll();
    }

    @ModelAttribute(value = "categories")
    public List<String> categories() {
        return productService.findDistinctCategories();
    }

    @GetMapping(value = "/products/filter/{category}")
    public String filter(@RequestParam(required = false) String category,
                         Model model) {
        List<Product> filtered = productService.findByCategory(category);
        model.addAttribute("products", filtered); // Overrides the @ModelAttribute above.
        return "filters";
    }
    
    @GetMapping(value = "/products")
    public String shopAll() {
        return "filters";
    }

    @GetMapping(value = "/about")
    public String about() {
        return "about";
    }
}


