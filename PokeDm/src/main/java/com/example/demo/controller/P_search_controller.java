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

public class P_search_controller {

		@Autowired
		JdbcTemplate jdbcTemplate;
		
		
		@RequestMapping(path = "/poke_Ai_select", method = RequestMethod.POST)
		public String P_serch_post(String Poke_select ,String button,HttpSession session, Model model) {

			String ID = (String) session.getAttribute("Poke_id");
			System.out.println(ID);
			System.out.println(Poke_select);
			if(button.equals("ノーマル")) {
				List<Map<String, Object>> resultList6=jdbcTemplate.queryForList("select * from poke2 where jname =?",Poke_select);
				String select_id=(String)resultList6.get(0).get("image");
				model.addAttribute("select_id",select_id);
			}else if(button.equals("AI")) {
				List<Map<String, Object>> resultList7=jdbcTemplate.queryForList("select * from poke_ai where jname =? and user_name=?",Poke_select,ID);
				String select_id=(String)resultList7.get(0).get("image_id");
				System.out.println(select_id);
				model.addAttribute("select_id",select_id);
			}
			List<Map<String, Object>> resultList=jdbcTemplate.queryForList("select * from poke_ai where user_name =?",ID);
			model.addAttribute("AI_list",resultList);
			
			
			return "p_Ai";
		}
}
