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
import java.util.ArrayList;


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
    
    
    //done this way because I don't know SQL and spring well enough to make a query
    //refactor this abomination
    public List<CartDetails> findAllCartDetailsesByCartId(Long cartId){
        ArrayList<CartDetails> allCartDetailses = (ArrayList) cartDetailsRepository.findAll(); //there must be a better way to do this
        ArrayList<CartDetails> currentUserCartDetailses = new ArrayList<>(); 
        for(CartDetails cartDetails : allCartDetailses){
            if(cartId == cartDetails.getCart().getId()){
                currentUserCartDetailses.add(cartDetails);
            }
        }
        return currentUserCartDetailses;
    }
        
    
    public void removeCartDetailFromCartByCartDetailsId(Long cartDetailsId){ //CartDetails or CartDetailses?
       //remove cartdetails from cart


       //delete cartdetails
       //should also remove from cart through cascading
      cartDetailsRepository.deleteById(cartDetailsId);
   }
    
    public void updateCartDeailsQuantity(Long id, Integer newQuantity){
        CartDetails cartDetails = cartDetailsRepository.findAllById(id).get(0);
        cartDetails.setQuantity(newQuantity);
        cartDetailsRepository.delete(cartDetails); //what a crappy way to do this. 
        cartDetailsRepository.save(cartDetails);
    }
    
  public List <CartDetails>  findAllCartDetailsesByCartIdSortedByDescendingDateCreated(Long cartId){
        ArrayList<CartDetails> cartDetailses = (ArrayList<CartDetails>) findAllCartDetailsesByCartId(cartId);
        //sort on .getCreatedAt
        // https://stackoverflow.com/a/37291086/1491213
        cartDetailses.sort((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()));
        return cartDetailses;
    }
}
