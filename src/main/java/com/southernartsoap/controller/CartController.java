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
        List<CartDetails> cartDetailses = cartDetailsService.findAllCartDetailses();
        model.addAttribute("cartDetailses", cartDetailses)
        return "cart";
    }
    
    
    
}
