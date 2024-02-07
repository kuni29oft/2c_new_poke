package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class P_login_controller {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(path="/poke_login",method=RequestMethod.GET)
	public String battle(Model model) {
		
		return "p_login" ;
		
	}
	@RequestMapping(path="/poke_login",method=RequestMethod.POST)
	public String battle_gra(String Poke_id,String Poke_pass,Model model) {
		List<Map<String,Object>> resultList;
		

			resultList=jdbcTemplate.queryForList("select * from login where id =? and password=?",Poke_id,Poke_pass);
			
			int x = resultList.size();
			if(1<=x) {
				return "redirect:/poke_base";
				
			}else {
				String message="ログインに失敗しました";
				model.addAttribute("kekka",message);
				return "poke_login";
				
			}
		

		}
		
	}


 