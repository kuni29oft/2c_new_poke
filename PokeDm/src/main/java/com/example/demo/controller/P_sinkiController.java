package com.example.demo.controller;

import java.util.List;
import java.util.Map;

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
		List<Map<String,Object>> resultList;
		resultList=jdbcTemplate.queryForList("select * from user1 where ID =?",Poke_id);
		
		int x = resultList.size();
		if(1<=x) {
		model.addAttribute("message","この情報は登録済みです")	;
			return "p_sinki";
			
		}else {
			jdbcTemplate.update("insert into user1(ID,password) values(?,?)",Poke_id,Poke_pass);
			return "p_login";
			
		}
	

			
			
		}
	
	}
	