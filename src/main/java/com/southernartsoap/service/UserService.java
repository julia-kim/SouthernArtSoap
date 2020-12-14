package com.southernartsoap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.southernartsoap.model.User;
import com.southernartsoap.repository.CartRepository;
import com.southernartsoap.repository.UserRepository;
import com.southernartsoap.model.Cart;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public void saveNew(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		Cart cart = new Cart();
		cart.setUser(user);
		cartRepository.save(cart);
	}

	public void saveExisting(User user) {
		userRepository.save(user);
	}

	public User getLoggedInUser() {
		return findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	public void updateCart(Cart cart) {
		User user = getLoggedInUser();
		user.setCart(cart);
		saveExisting(user);
	}

	// attempt to clear cart
	public void deleteCart(User user) {
		cartRepository.findCartByUser(user).getCartDetailses().clear();
		cartRepository.save(cartRepository.findCartByUser(user));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Username not found.");
		return user;
	}

}