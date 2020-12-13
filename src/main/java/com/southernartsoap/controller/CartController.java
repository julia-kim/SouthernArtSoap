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
import com.southernartsoap.model.Image;
import com.southernartsoap.service.ProductService;
import com.southernartsoap.model.Product;
import com.southernartsoap.model.Cart;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
       cartDetailses =  (ArrayList) cartDetailsService.findAllCartDetailses();        
        
        //get the images that map to the cart
        //adding 3 items manually for testing
//        for(int i=1; i < 4; i++){ //product ID starts at 1
//            Product product = productService.findById(Long.valueOf(i));
//            Cart cart = new Cart(); //might break?
//            cartDetailses.add(new CartDetails(Long.valueOf(i), product, cart, 2, "Make it rain", "Roses", "green"));
//            
//        }
        
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
    
    @PostMapping("/cart/delete")
    public String deleteItem(@ModelAttribute CartDetails cartDetails, BindingResult bindingResult, Model model){
        System.out.println(cartDetails); //somehow this isn't being passed in?
        if(!bindingResult.hasErrors()){
            cartDetailsService.removeCartDetailFromCartByCartDetailsId(cartDetails.getId());
            
        }
    
        return "cart";
    }
    
    //this isn't hitting the endpoint?
     @RequestMapping(value = "/cart/delete/{id}", method = RequestMethod.GET)
    public String deleteCartDetailById(@PathVariable Long id, CartDetails cartDetails, Model model) {
        System.out.println(id); 
        cartDetailsService.removeCartDetailFromCartByCartDetailsId(id);
            
        return "cart";
    }
//    
//    @PostMapping("/cart")
//    public String updateQuantity(){
//        
//        
//        return "cart";
//    }
    

    
}
