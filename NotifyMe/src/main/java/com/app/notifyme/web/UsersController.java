package com.app.notifyme.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.notifyme.models.User;
import com.app.notifyme.repositories.UserRepository;


@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<User> getAll() {
		System.out.println("getting...");
		List<User> stories = (List<User>) userRepository.findAll();
		System.out.println(stories.size());
		return stories;
	}	
	
}
