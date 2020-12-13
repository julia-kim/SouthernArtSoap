/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.southernartsoap.model.CartDetails;
import com.southernartsoap.model.User;
import com.southernartsoap.repository.CartDetailsRepository;
import com.southernartsoap.service.CartService;
import com.southernartsoap.service.ProductService;
import com.southernartsoap.service.UserService;


/**
 *
 * @author jared
 */
@Controller
public class TestingController {
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired 
	private CartDetailsRepository cartDetailsRepository;
    
    @GetMapping(value="/navbar-test")
    public String navbar(){
        return "navbar-test";
    }
    
    @GetMapping(value="/cart")
    public String cart(Model model){
    	model.addAttribute("cartDetails", cartService.findAll());
        return "cart";
    }
    
    @PostMapping(value="/cart/add/{id}")
    public String addToCart(@PathVariable Long id, CartDetails cartDetails) {
    	User user = userService.getLoggedInUser();
    	cartDetails.setCart(cartService.findCartByUser(user));
    	cartDetails.setProduct(productService.findById(id));
		cartDetailsRepository.save(cartDetails);
    	return "cart";
    }
    

}
