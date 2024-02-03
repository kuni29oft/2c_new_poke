package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class falsecontroller {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(path="/poke_false/{poke1}/{poke2}",method=RequestMethod.GET)
	public List<Map<String, Object>> battle_sa(@PathVariable String poke1,@PathVariable String poke2,Model model) {
	
	List<Map<String,Object>> resultList = jdbcTemplate.queryForList("SELECT * FROM demo WHERE name IN (?,?)",poke1,poke2);
		
		
		return resultList ;
		
		}
}
