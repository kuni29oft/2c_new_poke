package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
//@Controller
@RestController
@CrossOrigin(origins="*")
public class P_AiInsertController {
	@Autowired
	JdbcTemplate jdbcTemplate;

@RequestMapping(path = "/poke_Ai_Insert", method = RequestMethod.POST)
public String AIInsert(String Poke_image, Model model,HttpSession session)  {
	
	String ID = (String) session.getAttribute("Poke_id");
	System.out.println(Poke_image);
	System.out.println(ID);
	jdbcTemplate.update("insert into sample_poke(name,imgId) values(?,?)",ID,Poke_image);
	String K="登録完了";	
   return "p_Ai";
}
}
