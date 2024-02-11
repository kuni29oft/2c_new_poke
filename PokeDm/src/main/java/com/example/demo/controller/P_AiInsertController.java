package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
@Controller
//@RestController
//@CrossOrigin(origins="*")
public class P_AiInsertController {
	@Autowired
	JdbcTemplate jdbcTemplate;

@RequestMapping(path = "/poke_Ai_Insert/{Poke_Image}", method = RequestMethod.GET)
public String AIInsert(@PathVariable String Poke_Image, Model model,HttpSession session)  {
	
	String ID = (String) session.getAttribute("Poke_id");
	System.out.println(Poke_Image);
	System.out.println(ID);
	jdbcTemplate.update("insert into sample_poke(name,imgId) values(?,?)",ID,Poke_Image);
	String K="登録完了";	
   return K;
}
}
