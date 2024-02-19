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
public class P_login_controller {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(path="/poke_login",method=RequestMethod.GET)
	public String battle(Model model,HttpSession session) {
		session.removeAttribute("Poke_id");
		return "p_login" ;
		
	}
	@RequestMapping(path="/poke_login",method=RequestMethod.POST)
	public String battle_gra(String Poke_id,String Poke_pass,Model model,HttpSession session) {
		List<Map<String,Object>> resultList;
			resultList=jdbcTemplate.queryForList("select * from user1 where ID =? and password=?",Poke_id,Poke_pass);
			
			int x = resultList.size();
			if(1<=x) {
				session.setAttribute("Poke_id",Poke_id);
				return "redirect:/poke_damage";
				
			}else {
				System.out.println("ログインに失敗しました");
				return "p_login";
				
			}
		

		}
	@RequestMapping(path="/poke_logout",method=RequestMethod.POST)
	public String logout(Model model,HttpSession session) {
		session.removeAttribute("Poke_id");
		
		return "p_login" ;
		
	}
	}


 
