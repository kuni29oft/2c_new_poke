package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
@Controller
//@RestController
//@CrossOrigin(origins="*")
public class P_AiInsertController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	

@RequestMapping(path = "/poke_Ai/Insert", method = RequestMethod.POST)
public String AIInsert(String Poke_image,String Poke_name,String type1,String type2,String hp,String atk,String def,String satk,String sdef,String sp,String height,String weight, Model model,HttpSession session)  {
	List<Map<String, Object>> resultList6,resultList7;
	Integer.parseInt(hp);
	Integer.parseInt(atk);
	Integer.parseInt(def);
	Integer.parseInt(satk);
	Integer.parseInt(sdef);
	Integer.parseInt(sp);
	Integer.parseInt(sdef);
	Integer.parseInt(height);
	Integer.parseInt(weight);
	System.out.println(weight);
	if(Objects.equals(type2,null)) {
		type2=" ";
		System.out.println(type2);
	}
	String ID = (String) session.getAttribute("Poke_id");
	resultList6=jdbcTemplate.queryForList("select * from poke_ai where user_name =?",ID);
	model.addAttribute("AI_list",resultList6);
	
	resultList7=jdbcTemplate.queryForList("select * from poke_ai where jname =? and user_name=?",Poke_name,ID);
	if (resultList7.size() >= 1) {
		String K = "その名前は登録済みです";
		model.addAttribute("message", K);
		
		return "p_Ai";
	}else {
		System.out.println(Poke_image);
		System.out.println(ID);
		jdbcTemplate.update("insert into poke_ai(image_id,jname,types1,types2,hp,atk,defense,satk,sdef,sp,height,weight,user_name) values (?,?,?,?,?,?,?,?,?,?,?,?,?)",Poke_image,Poke_name,type1,type2,hp,atk,def,satk,sdef,sp,height,weight,ID);
		String K="登録完了";	
		model.addAttribute("message",K);
		resultList6=jdbcTemplate.queryForList("select * from poke_ai where user_name =?",ID);
		model.addAttribute("AI_list",resultList6);
	   return "p_Ai";
	}
	
	
	
}
}
