package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class P_AiInsertController {
	@Autowired
	JdbcTemplate jdbcTemplate;

@RequestMapping(path = "/poke_Ai_Insert/{Poke_Image}", method = RequestMethod.GET)
public String AIInsert(@PathVariable String Poke_Image, Model model)  {
	
	
	jdbcTemplate.update("INSERT INTO sample_poke  VALUES(?)", Poke_Image);
		

	
	return "aiueo";
}
}
