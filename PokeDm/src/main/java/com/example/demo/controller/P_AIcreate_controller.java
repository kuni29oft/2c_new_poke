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
public class P_AIcreate_controller {


	@Autowired
	JdbcTemplate jdbcTemplate;
	@RequestMapping(path = "/poke_Ai", method = RequestMethod.GET)
	public String battle(HttpSession session,Model model) {

		String ID = (String) session.getAttribute("Poke_id");
		List<Map<String, Object>> resultList6=jdbcTemplate.queryForList("select * from poke_ai where user_name =?",ID);
		model.addAttribute("AI_list",resultList6);
		
		return "p_Ai";

	}
	@RequestMapping(path = "/poke_Ai_Insert", method = RequestMethod.GET)
	public String battle_insert(HttpSession session,Model model) {

		String ID = (String) session.getAttribute("Poke_id");
		List<Map<String, Object>> resultList6=jdbcTemplate.queryForList("select * from poke_ai where user_name =?",ID);
		model.addAttribute("AI_list",resultList6);
		
		return "p_Ai";

	}
	@RequestMapping(path = "/poke_Ai_select", method = RequestMethod.GET)
	public String battle_select(HttpSession session,Model model) {

		String ID = (String) session.getAttribute("Poke_id");
		List<Map<String, Object>> resultList6=jdbcTemplate.queryForList("select * from poke_ai where user_name =?",ID);
		model.addAttribute("AI_list",resultList6);
		
		return "p_Ai";

	}


}
