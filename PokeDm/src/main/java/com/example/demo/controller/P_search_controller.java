package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class P_search_controller {

		@Autowired
		JdbcTemplate jdbcTemplate;
		
		
		@RequestMapping(path = "/P_search", method = RequestMethod.POST)
		public String P_serch_post(String user_id, String user_pass, String user_name, Model model) {

//			jdbcTemplate.update("INSERT INTO users (user_id,user_pass,user_name,user_image) VALUES(?,?,?,?);", user_id,
//					user_pass, user_name, "");
			
			
			
			return "redirect:/P_serch";
		}
}
