package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class P_sinkiController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(path="/poke_sinki",method=RequestMethod.GET)
	public String P_sinkihyouzi(Model model) {
		
		return "p_sinki";

	}
	
	
	@RequestMapping(path="/poke_sinki",method=RequestMethod.POST)
	public String P_sinki(String Poke_id, String Poke_pass, Model model, HttpSession session) {
	
			jdbcTemplate.update("insert into user1(ID,password) values(?,?)",Poke_id,Poke_pass);
			return "p_sinki";
		
			
		
			
			
		}
	
	}
	