/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.controller;

import com.southernartsoap.service.CartDetailsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.southernartsoap.model.CartDetails;
import java.util.ArrayList;
import java.util.LinkedList;
import com.southernartsoap.model.Image;
import com.southernartsoap.service.ProductService;
import java.util.HashMap;
import com.southernartsoap.model.Product;
import com.southernartsoap.model.Cart;

/**
 *
 * @author jared
 */
@Controller
public class CartController {
    @Autowired
    CartDetailsService cartDetailsService;
    @Autowired
    ProductService productService;
    
    @GetMapping(value="/cart")
    public String cart(Model model){
        //initializing it like this allows us to check if it's empty in the cart.html template
        ArrayList<CartDetails> cartDetailses = new ArrayList<CartDetails>();
        ArrayList<Image> cartImages = new ArrayList<Image>();
//        HashMap<CartDetails, Image> cartImageMap = new HashMap<CartDetails, Image>();
       ArrayList <CartDetails> cartDetailses =  (ArrayList) cartDetailsService.findAllCartDetailses();
//        model.addAttribute("cartDetailses", cartDetailses);
        
        //get the images that map to the cart
        //adding 3 items manually for testing
        for(int i=1; i < 4; i++){ //product ID starts at 1
            Product product = productService.findById(Long.valueOf(i));
            Cart cart = new Cart(); //might break?
            cartDetailses.add(new CartDetails(Long.valueOf(i), product, cart, 2, "Make it rain", "Roses", "green"));
            
        }
        
        double totalPrice = 0; //passsed to thymeleaf to b/c thymleaf is a pain
        for(CartDetails cartDetails : cartDetailses){
            Long productId = cartDetails.getProduct().getId();
            Image image = productService.findFirstProductImagesByProductId(productId); 
            cartImages.add(image);
            totalPrice+= cartDetails.getProduct().getPrice() * cartDetails.getQuantity();
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartDetailses", cartDetailses);
        model.addAttribute("images", cartImages);
        
        return "cart";
    }
    
    
    
}
