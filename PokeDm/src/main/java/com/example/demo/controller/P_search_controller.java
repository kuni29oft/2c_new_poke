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
public class P_search_controller {

	//DBへつなぐために必要
		@Autowired
		JdbcTemplate jdbcTemplate;
		
		
		@RequestMapping(path = "/P_search", method = RequestMethod.GET)
		public String search(Model model) {

			//SELECT文の結果をしまうためのリスト
			List<Map<String, Object>> resultList;

			//SELECT文の実行
			resultList = jdbcTemplate.queryForList("select * from demo where P_name=?");

			//実行結果をmodelにしまってHTMLで出せるようにする。
			model.addAttribute("selectResult", resultList);

			return "p_search";
		}
		@RequestMapping(path = "/P_search", method = RequestMethod.POST)
		public String P_serch_post(String user_id, String user_pass, String user_name, Model model) {

			jdbcTemplate.update("INSERT INTO users (user_id,user_pass,user_name,user_image) VALUES(?,?,?,?);", user_id,
					user_pass, user_name, "");
			
			
			
			return "redirect:/P_serch";
		}
}
