/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.service;

import com.southernartsoap.model.CartDetails;
import com.southernartsoap.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.southernartsoap.repository.CartDetailsRepository;
import java.util.List;

/**
 *
 * @author jared
 */
@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    
    
}
