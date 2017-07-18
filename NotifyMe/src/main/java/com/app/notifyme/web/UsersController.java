package com.app.notifyme.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.notifyme.models.User;
import com.app.notifyme.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserRepository userRepository;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String addNewUser(@RequestParam String email, @RequestParam String name,
			@RequestParam String password, @RequestParam String phoneNumber, @RequestParam byte type) {
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setPhoneNumber(phoneNumber);
		user.setProfileImage(null);
		user.setType(type);
		userRepository.save(user);
		return "New User Added";
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<User> getAll() {
		System.out.println("getting...");
		List<User> users = (List<User>) userRepository.findAll();
		System.out.println(users.size());
		return users;
	}

}
