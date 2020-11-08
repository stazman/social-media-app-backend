package com.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Users;
import com.project.service.UserService;

@Controller
@CrossOrigin // Injects the header, allows requests from this origin. Can also use wildcards
@RequestMapping("/users")
public class UserController {
	
	private UserService us;
	
	public UserController() {
	}

	public UserController(UserService us) {
		super();
		this.us = us;
	}
	
	public UserService getUsers() {
		return us;
	}

	@Autowired
	public void setPps(UserService us) {
		this.us = us;
	}

	@GetMapping
	public @ResponseBody List<Users> getAll() {
		return us.getAllUsers();
	}
	
	@GetMapping("/find/{id}")
	public @ResponseBody Users findUserNoPass(@PathVariable int id) {
		return us.findUserNoPass(id);
	}
	
	@GetMapping("/validate/{username}+{password}")
	public @ResponseBody Users validateUser(@PathVariable String username, @PathVariable String password) {
		return us.validateLogin(username, password);
	}
	@GetMapping("/create/{username}+{password}+{firstname}+{lastname}+{email}")
	public @ResponseBody Users validateUser(@PathVariable String username, @PathVariable String pass, 
			@PathVariable String firstName, @PathVariable String lastName, @PathVariable String email) {
		return us.createUser(username, pass, firstName, lastName, email);
	}
	
	@PostMapping("/status")
	public @ResponseBody String updateStatus(@RequestBody Map<String, String> json) {
		int userId = Integer.parseInt(json.get("userId"));
		String status = json.get("status");
		return us.updateUserStatus(userId, status);
	}

}