/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.repository;

import com.southernartsoap.model.Cart;
import com.southernartsoap.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jared
 */
@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
	Cart findCartByUser(User user);
}
