/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.southernartsoap.model.CartDetails;
import org.springframework.beans.factory.annotation.Autowired;
import com.southernartsoap.repository.CartDetailsRepository;

/**
 *
 * @author jared
 */
@Service
public class CartDetailsService {
    @Autowired
    private CartDetailsRepository cartDetailsRepository;
    public List<CartDetails> findAllCartDetailses(){ //CartDetails or CartDetailses?
      return cartDetailsRepository.findAll();
    }
}
