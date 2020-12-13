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
import com.southernartsoap.model.Image;
import com.southernartsoap.service.ProductService;
import com.southernartsoap.model.Product;
import com.southernartsoap.model.Cart;
import com.southernartsoap.model.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.southernartsoap.service.UserService;
import com.southernartsoap.repository.CartDetailsRepository;
import com.southernartsoap.service.CartService;

/**
 *
 * @author jared
 */
@Controller
public class CartController {
    @Autowired
    CartDetailsService cartDetailsService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    CartDetailsRepository cartDetailsRepository;
    @Autowired
    CartService cartService;
    

    
    
    @GetMapping(value="/cart")
    public String cart(Model model){
       //initializing it like this allows us to check if it's empty in the cart.html template
       User user = userService.getLoggedInUser();
       ArrayList<CartDetails> cartDetailses = new ArrayList<CartDetails>();
       ArrayList<Image> cartImages = new ArrayList<Image>(); 
       if(user!=null){ //so cart doesn't crash if no one is logged in    
            cartDetailses =  (ArrayList) cartDetailsService.findAllCartDetailsesByCartId(user.getCart().getId());    
       }
       
        
        
        double totalPrice = 0; //passsed to thymeleaf to b/c thymeleaf is a pain
        for(CartDetails cartDetails : cartDetailses){
            Long productId = cartDetails.getProduct().getId();
            Image image = productService.findFirstProductImagesByProductId(productId); 
            cartImages.add(image);
            totalPrice+= cartDetails.getProduct().getPrice() * cartDetails.getQuantity();
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartDetailses", cartDetailses);
        model.addAttribute("images", cartImages);
        
        return "cart";
    }
    
//    @PostMapping("/cart/delete")
//    public String deleteItem(@ModelAttribute CartDetails cartDetails, BindingResult bindingResult, Model model){
//        System.out.println(cartDetails); //somehow this isn't being passed in?
//        if(!bindingResult.hasErrors()){
//            cartDetailsService.removeCartDetailFromCartByCartDetailsId(cartDetails.getId());
//            
//        }
//    
//        return "cart";
//    }
    

     @RequestMapping(value = "/cart/delete/{id}", method = RequestMethod.GET)
    public String deleteCartDetailById(@PathVariable Long id, CartDetails cartDetails, Model model) {
        System.out.println(id); 
        cartDetailsService.removeCartDetailFromCartByCartDetailsId(id);
            
        return "cart";
    }
//    
//    @PostMapping("/cart/update/{id}")
//    public String updateQuantity(){
//        
//        
//        return "cart";
//    }
//    
//        @GetMapping(value="/cart")
//    public String cart(Model model){
//    	model.addAttribute("cartDetails", cartService.findAll());
//        return "cart";
//    }
//    
    @PostMapping(value="/cart/add/{id}")
    public String addToCart(@PathVariable Long id, CartDetails cartDetails, Model model) {
    	User user = userService.getLoggedInUser();
    	cartDetails.setCart(cartService.findCartByUser(user)); //i bet this crashes if the user is not logged in
    	cartDetails.setProduct(productService.findById(id));
        cartDetailsRepository.save(cartDetails);  //probably should call the service not the repository from the controller
        return cart(model);
    }
    

    
}
