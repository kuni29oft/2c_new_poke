//package com.example.demo.controller;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.client.RestTemplate;
//
//@Controller
//public class P_sinki_controller {
//
//	@Autowired
//	JdbcTemplate jdbcTemplate;
//	
//	
//	@Autowired
//	RestTemplate restTemplate ;
//
//	@RequestMapping(path="/poke_sinki",method=RequestMethod.GET)
//	public String battle(Model model) {
//		
//		return "p_sinki" ;
//		
//	}
//	@RequestMapping(path="/poke_sinki",method=RequestMethod.POST)
//	public String battle_gra(String Poke_id,String Poke_pass,Model model) {
//		
//			jdbcTemplate.update("insert into login values(?,?)",Poke_id,Poke_pass);
//			
//			
//			
//			com.example.demo.bean.SendData senddata = new com.example.demo.bean.SendData();
//			
//			
//			senddata.setKey("APIキー");
//
//			senddata.setPrompt("画像生成に使うワード");
//			//restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random");
//		
//				return "poke_sinki";
//				
//
//		}
//}
