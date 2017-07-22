package com.app.notifyme.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.notifyme.models.User;
import com.app.notifyme.repositories.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public void addUser(@RequestBody User user) {
		System.out.println("Added user" + user.getName());
		userRepository.save(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginUser(@RequestParam String email, @RequestParam String password) {
		User user = userRepository.findByEmail(email);
		if (user != null) {
			System.out.println(user.getName());
		} else {
			System.out.println("Not a user");
		}
	}
}
