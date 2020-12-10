/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author jared
 */
public class CartController {
    
    @GetMapping(value="/cart")
    public String cart(){
        return "cart";
    }
    
}
