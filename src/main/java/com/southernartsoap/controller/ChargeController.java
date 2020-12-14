package com.southernartsoap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

import com.southernartsoap.model.ChargeRequest;
import com.southernartsoap.model.User;
import com.southernartsoap.service.StripeService;
import com.southernartsoap.service.UserService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Controller
public class ChargeController {
	@Autowired
	private StripeService paymentsService; 
	
    @Autowired
    private UserService userService;

	@PostMapping("/charge")
	public String charge(ChargeRequest chargeRequest, Model model) throws StripeException {
		chargeRequest.setDescription("Example charge");
		chargeRequest.setCurrency(ChargeRequest.Currency.USD);
		Charge charge = paymentsService.charge(chargeRequest);
		model.addAttribute("id", charge.getId());
		model.addAttribute("status", charge.getStatus());
		model.addAttribute("chargeId", charge.getId());
		model.addAttribute("balance_transaction", charge.getBalanceTransaction());
		
		// delete all items from user's cart
		User user = userService.getLoggedInUser();
		userService.deleteCart(user);
		return "result";
	}

	@ExceptionHandler(StripeException.class)
	public String handleError(Model model, StripeException ex) {
		model.addAttribute("error", ex.getMessage());
		return "result";
	}
}