package com.iktpreobuka.e_diary.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.e_diary.entities.RoleEntity;
import com.iktpreobuka.e_diary.entities.UserEntity;

@RestController
@RequestMapping ( path = "/api/v1/users")
public class UserController {

	public List<UserEntity> getDummyDB() {
		List<UserEntity> list= new ArrayList<>();
		
		RoleEntity re= new RoleEntity();
		re.setId((long)1);
		re.setName("admin");
		
		UserEntity ue= new UserEntity();
		ue.setId((long) 1);
		ue.setEmail("user@example.com");
		ue.setName("Vladimir");
		ue.setLastName("Dimitrieski");
		ue.setPassword("password1234");
		ue.setRole(re);
		
		UserEntity ue1= new UserEntity();
		ue1.setId((long) 2);
		ue1.setEmail("user2@example.com");
		ue1.setName("Milan");
		ue1.setLastName("Celikovic");
		ue1.setPassword("password4321");
		ue1.setRole(re);
		list.add(ue);
		list.add(ue1);
		return list;
		}
	
		@RequestMapping(path="/users", method = RequestMethod.GET)
		public List<UserEntity> listUsers(){
		return getDummyDB();
		}
		
}
