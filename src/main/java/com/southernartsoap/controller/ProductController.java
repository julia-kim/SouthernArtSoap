package com.southernartsoap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.southernartsoap.model.Image;
import com.southernartsoap.model.Product;
import com.southernartsoap.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/products/{id}")
	public String show(@PathVariable int id, Model model) {
		Product product = productService.findById(id);
		List<Image> images = productService.findAllProductImagesByProductId(id);
		model.addAttribute("category", product.getCategory());
		model.addAttribute("product", product);
		model.addAttribute("images", images);
		return "product";
	}

	// TODO: Either implement ADMIN controls or remove these methods.

	// @RequestMapping(value = "/product", method = {RequestMethod.POST,
	// RequestMethod.PUT})
	// public String createOrUpdate(@Valid Product product) {
	// productService.save(product);
	// return "redirect:/product/" + product.getId();
	// }
}
