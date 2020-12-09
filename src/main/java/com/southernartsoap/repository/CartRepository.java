/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.repository;

import com.southernartsoap.model.Cart;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jared
 */
public class CartRepository extends CrudRepository<Cart, Long> {
    
}
