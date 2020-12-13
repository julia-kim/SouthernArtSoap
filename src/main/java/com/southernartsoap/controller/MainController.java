package com.southernartsoap.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.southernartsoap.model.Product;
import com.southernartsoap.service.ProductService;

@Controller
@ControllerAdvice // This makes the `@ModelAttribute`s of this controller available to all
					// controllers, for the navbar.
public class MainController {
	@Autowired
	ProductService productService;

	@GetMapping(value = "/")
	public String home() {
		return "index";
	}

	@RequestMapping(value = "/products")
	public String viewPage(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Page<Product> productPage = productService.findAll(page);
		List<Product> products = productPage.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("products", products);
		return "filters";
	}

	@ModelAttribute(value = "categories")
	public List<String> categories() {
		return productService.findDistinctCategories();
	}

	@GetMapping(value = "/products/filter")
	public String filter(@RequestParam(required = false) String category,
			@RequestParam(name = "page", defaultValue = "1") int page, Model model) {
		Page<Product> productPage = productService.findByCategory(category, page);
		List<Product> filtered = productPage.getContent();
		model.addAttribute("category", category);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("products", filtered);
		return "filters";
	}

	@GetMapping(value = "/search")
	public String search(@RequestParam(value = "q", required = false) String query, Model model) {
		List<Product> results = new ArrayList<>();
		if (query != null) {
			results = productService.findByNameIgnoreCaseContaining(query);
			model.addAttribute("query", query);
			model.addAttribute("results", results);
		}
		return "search";
	}


	@GetMapping(value = "/about")
	public String about() {
		return "about";
	}
}
