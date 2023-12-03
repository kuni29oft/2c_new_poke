package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class P_sample_controller {
	
		@Autowired
		JdbcTemplate jdbcTemplate;
		
	@RequestMapping(path="/poke_damage_sample",method=RequestMethod.POST)
	public String battle_gra(String Poke_name1,String Poke_name2,Model model) {
		
		//画面からの値を取得
		
		//画像ファイル名を取得して画面に返す。
		
		return "aiueo";
		
	}

}
 