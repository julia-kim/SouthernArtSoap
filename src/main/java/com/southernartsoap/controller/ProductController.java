package com.southernartsoap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.southernartsoap.model.Product;
import com.southernartsoap.service.ProductService;

public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping(value = { "/shop" })
	public String getProducts(@RequestParam(value = "filter", required = false) String filter, Model model) {
		List<Product> products = new ArrayList<>();
		if (filter == null) {
			filter = "all";
		}
		if (filter.equalsIgnoreCase("baby")) {
			products = productService.findByCategory("baby shower");
			model.addAttribute("filter", "baby");
		} 
		if (filter.equalsIgnoreCase("wedding")) {
			products = productService.findByCategory("wedding");
			model.addAttribute("filter", "following");
		} if (filter.equalsIgnoreCase("holiday")) {
			products = productService.findByCategory("holidays");
			model.addAttribute("filter", "holiday");
		} if (filter.equalsIgnoreCase("seasonal")) {
			products = productService.findByCategory("seasons");
			model.addAttribute("filter", "seasonal");
		} if (filter.equalsIgnoreCase("flower")) {
			products = productService.findByCategory("flowers");
			model.addAttribute("filter", "flower");
		}  else {
			products = productService.findAll();
			model.addAttribute("filter", "all");
		}
		model.addAttribute("productList", products);
		return "shop";
	}

    @GetMapping("/product/{id}")
    public String show(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product";
    }

    // TODO: Either implement ADMIN controls or remove these methods.

    // @RequestMapping(value = "/product", method = {RequestMethod.POST, RequestMethod.PUT})
    // public String createOrUpdate(@Valid Product product) {
    //     productService.save(product);
    //     return "redirect:/product/" + product.getId();
    // }
}


