package com.jayg.demo.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UsersController {
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/users", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public List<User> list() {
		List<User> list = userRepository.findAll();
		return list;
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET, produces="application/json;charset=UTF-8")
	public User getOne(@PathVariable int id) {
		User user = userRepository.getOne(id);
		return user;
	}
	
	@RequestMapping(value="/user", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public User add(@RequestBody User user) {
		return getOne(userRepository.save(user).getId());
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.PUT, produces="application/json;charset=UTF-8")
	public User put(@PathVariable int id, @RequestBody User user) {
		user.setId(id);
		userRepository.save(user);
		return getOne(id);
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE, produces="application/json;charset=UTF-8")
	public void delete(@PathVariable int id) {
		userRepository.delete(id);
	}
}
