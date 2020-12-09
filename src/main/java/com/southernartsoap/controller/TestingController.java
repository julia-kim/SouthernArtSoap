/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author jared
 */
@Controller
public class TestingController {
    
    @GetMapping(value="/navbar-test")
    public String navbar(){
        return "navbar-test";
    }
    
    @GetMapping(value="/cart")
    public String cart(){
        return "cart";
    }
}
