package com.app.notifyme.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.notifyme.models.User;
import com.app.notifyme.repositories.UserRepository;

@RestController
public class UserController {

	// private final String IMAGE_SAVE_FOLDER =
	// "C:/Users/ADMIN/wissen-project/NotifyMe/src/main/resources/images";

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public void addUser(@RequestBody User user, @RequestParam("file") MultipartFile profileImage) {
		System.out.println("Added user" + user.getName());

		try {
			user.setProfileImage(profileImage.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		userRepository.save(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	User loginUser(@RequestParam String email, @RequestParam String password) {
		User user = userRepository.getUserById(email);
		System.out.println("login user: " + user.getName());
		System.out.println(user.getPassword() + " -> " + password);
		if (password.equals(user.getPassword())) {
			System.out.println("returning user");
			return user;
		}
		return null;
	}
}
