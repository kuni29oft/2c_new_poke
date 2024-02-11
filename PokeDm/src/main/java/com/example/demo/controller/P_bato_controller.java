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
	public String battle_html(Model model) {
		
		return "p_battle2" ;
		
	}
	
	@RequestMapping(path="/poke_damage",method=RequestMethod.POST)
	public String battle(String Poke_name1,String Poke_name2,String waza,Model model) {
		System.out.println(waza);
		List<Map<String, Object>> resultList1,resultList2,resultList3;
		
		
		resultList1=jdbcTemplate.queryForList("select * from poke_new where jname =?",Poke_name1);
		resultList2=jdbcTemplate.queryForList("select * from poke_new where jname =?",Poke_name2);
		resultList3=jdbcTemplate.queryForList("select * from waza where jname =?",waza);
		
		String category=(String)resultList3.get(0).get("category");
		
		int wazadata=(Integer)resultList3.get(0).get("power");
		if(wazadata<10) {
			wazadata=wazapower(resultList1,resultList2,wazadata);
		}
		//計算式のメソッドを使いこの下に計算するコードを書け
		
		
		
		
		//int DAMAGE=Keisan(a,b,Integer.parseInt(waza));
		
		
		
		model.addAttribute("selectPoke1",resultList1);
		model.addAttribute("selectPoke2",resultList2);
		
		
		return "p_battle2" ;
		
	}
	private static int Keisan(int a,int b,int waza) {
		int A=22*waza*a/b;
		int C=A/50+2;
		return C;
		
	}
	private static int wazapower(List<Map<String, Object>> Poke1,List<Map<String, Object>> Poke2,int power) {
		
		switch(power){
		case 1:{
			
			break;
		}
		case 2:{
			break;
		}
		case 3:{
			break;
		}
		case 4:{
			break;
		}
		case 5:{
			break;
		}
		case 6:{
			break;
		}
		case 7:{
			break;
		}
		case 8:{
			break;
		}
		default:{
			break;
		}
		}
		return power;
		
	}
	

}
  