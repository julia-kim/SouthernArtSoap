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

/**
 *
 * @author jared
 */
@Controller
public class CartController {
    @Autowired
    CartDetailsService cartDetailsService;
    
    @GetMapping(value="/cart")
    public String cart(Model model){
        //initializing it like this allows us to check if it's empty in the cart.html template
        ArrayList<CartDetails> cartDetailses = new ArrayList<CartDetails>();
        cartDetailses =  (ArrayList) cartDetailsService.findAllCartDetailses();
        model.addAttribute("cartDetailses", cartDetailses);
        return "cart";
    }
    
    
    
}
