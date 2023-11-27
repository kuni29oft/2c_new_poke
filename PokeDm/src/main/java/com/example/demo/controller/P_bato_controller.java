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
public class P_bato_controller {
	
		@Autowired
		JdbcTemplate jdbcTemplate;
		
	@RequestMapping(path="/poke_damage",method=RequestMethod.GET)
	public String battle(Model model) {
		
		return "p_battle" ;
		
	}
	@RequestMapping(path="/poke_damage",method=RequestMethod.POST)
	public String battle_gra(String Poke_name1,String Poke_name2,Model model) {
		List<Map<String,Object>> resultList1,resultList2;
		
			resultList1=jdbcTemplate.queryForList("select * from demo where name =?",Poke_name1);
			model.addAttribute("selectResult1",resultList1);
			resultList2=jdbcTemplate.queryForList("select * from demo where name=? ",Poke_name2);
			model.addAttribute("selectResult2",resultList2);
		
		return "p_battle";
		
	}

}
 