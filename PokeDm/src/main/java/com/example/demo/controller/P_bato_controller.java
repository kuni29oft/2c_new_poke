package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class P_bato_controller {
	
		@Autowired
		JdbcTemplate jdbcTemplate;
		
	@RequestMapping(path="/poke_damage",method=RequestMethod.GET)
	public String battle(Model model) {
		
		return "p_battle" ;
		
	}
	

}
  