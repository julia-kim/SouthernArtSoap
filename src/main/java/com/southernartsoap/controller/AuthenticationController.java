package com.southernartsoap.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.southernartsoap.model.User;
import com.southernartsoap.service.UserService;

@Controller
class AuthenticationController {
  @Autowired
  private UserService userService;

  
  @GetMapping(value="/login")
  public String login() {
  	System.out.println("User Logged in!!!");
      return "/";
  }
  

  @GetMapping(value="/signin")
  public String registration(Model model){
      User user = new User();
      model.addAttribute("user", user);
      return "login-signup";
  }
  
  
  @PostMapping(value = "/signin")
  public String createNewUser(@Valid User user, 
		  					BindingResult bindingResult, Model model, HttpServletRequest request) throws ServletException {
      User userExists = userService.findByUsername(user.getUsername());
      String password = user.getPassword();
      if (userExists != null) {
          bindingResult.rejectValue("username", "error.user", "Username is already taken");
      }
      if (!bindingResult.hasErrors()) {
          userService.saveNew(user);
          
          model.addAttribute("success", "Sign up successful!");
          model.addAttribute("user", new User());
      }
    
      request.login(user.getUsername(), password); return "redirect:/"; 
  }
  
//  
  
//  @PostMapping("/signin") public String signup(@Valid User user, @RequestParam String submit, 
//		  BindingResult bindingResult, HttpServletRequest request) 
//				  throws ServletException { 
//	  String password = user.getPassword();
//		  if(submit.equals("up")) { 
//			  if(userService.findByUsername(user.getUsername())
//		  == null) { 
//				  userService.saveNew(user); 
//				  } else {
//		  bindingResult.rejectValue("username", "error.user",
//		  "Username is already taken."); 
//		  return "signin"; } 
//			  }
//	request.login(user.getUsername(), password); return "redirect:/"; 
	

//@PostMapping(value="/signin")
//public String singup(@Valid User user,                     
//                     BindingResult bindingResult) {
//    String password = user.getPassword();
//    if(submit.equals("up")) {
//        if(userService.findByUsername(user.getUsername()) == null) {
//            userService.saveNew(user);
//        } else {
//            bindingResult.rejectValue("username", "error.user", "Username is already taken.");
//            return "login-signup";
//        }
//    }
//    request.login(user.getUsername(), password);
//    return "redirect:/";
//}

//@GetMapping(value="/signin")
//public String login() {
//  return "login-signup";
//}


//Both of these methods serve up the signup page (GET), 
//as well as handle form submissions when a user hits submit on the signup page (POST).
//  public User getLoggedInUser() {
//	    String loggedInUsername = SecurityContextHolder.
//	      getContext().getAuthentication().getName();
//	    
//	    return findByUsername(loggedInUsername);
//	}
  
}