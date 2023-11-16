package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class P_search_controller {

	//DBへつなぐために必要
		@Autowired
		JdbcTemplate jdbcTemplate;
		
		public String search(Model model) {

			//SELECT文の結果をしまうためのリスト
			List<Map<String, Object>> resultList;

			//SELECT文の実行
			resultList = jdbcTemplate.queryForList("select * from demo");

			//実行結果をmodelにしまってHTMLで出せるようにする。
			model.addAttribute("selectResult", resultList);

			return "p_search";
		}
}
