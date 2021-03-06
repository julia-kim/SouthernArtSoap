/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southernartsoap.repository;

import org.springframework.data.repository.CrudRepository;
import com.southernartsoap.model.CartDetails;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jared
 */
@Repository
public interface CartDetailsRepository extends CrudRepository<CartDetails, Long>{
    //we always want all the CartDetails because we want the entire shopping cart
//    @Override //is this needed?
    List<CartDetails> findAll();
    //do I need @override?
    //probably don't need @ovverride because I am not modifying how it works
    void deleteById(Long id);
   

    List<CartDetails> findAllById(Long id); //says a type error when I do findById //returns just one item but in a list because that's how findAllById works. So we take the first element of the list. Always. 
}
