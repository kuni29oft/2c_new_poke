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
		List<Map<String, Object>> resultList6=jdbcTemplate.queryForList("select name from waza ");
		model.addAttribute("selectPoke3",resultList6);
		return "p_battle2" ;
		
	}
	
	@RequestMapping(path="/poke_damage",method=RequestMethod.POST)
	public String battle(String Poke_name1,String Poke_name2,String waza,Model model) {
		System.out.println(waza);
		List<Map<String, Object>> resultList1,resultList2,resultList3,resultList4,resultList5;
		
		
		resultList1=jdbcTemplate.queryForList("select * from poke_new where jname =?",Poke_name1);
		resultList2=jdbcTemplate.queryForList("select * from poke_new where jname =?",Poke_name2);
		
		resultList3=jdbcTemplate.queryForList("select * from waza where name =?",waza);
		System.out.println(resultList3);
		
		int damage=0;
		
		int wazadata=(Integer)resultList3.get(0).get("power");
		if(wazadata<10) {
			wazadata=wazapower(resultList1,resultList2,wazadata);
			System.out.println(wazadata);
		}
		
		
		String user_type1=(String)resultList1.get(0).get("types1");
		System.out.println(user_type1);
		System.out.println(resultList1);
		
		String user_type2=(String)resultList1.get(0).get("types2");
		System.out.println(user_type2);
		
		
		String enemy_type1=(String)resultList2.get(0).get("types1");
		System.out.println(enemy_type1);
		
		String enemy_type2=(String)resultList2.get(0).get("types2");
		System.out.println(enemy_type2);
		
		String wazatype=(String)resultList3.get(0).get("types");
		System.out.println(wazatype);
		
		resultList4=jdbcTemplate.queryForList("select * from type where usertype =? and npctype=? ",wazatype,enemy_type1);
		System.out.println(resultList4);
		
		resultList5=jdbcTemplate.queryForList("select * from type where usertype =? and npctype=?",wazatype,enemy_type2);
		System.out.println(resultList5);
		double mag1=1.0;
		double mag2=1.0;
		if (resultList4.size() >= 1) {
			mag1=(double)resultList4.get(0).get("magnification");
			System.out.println(mag1);
		}
		if (resultList5.size() >= 1) {
			mag2=(double)resultList5.get(0).get("magnification");
			System.out.println(mag2);
		}
		
	
		
		double typemag=Types(mag1,mag2);
		System.out.println(typemag);
		
		String category=(String)resultList3.get(0).get("category");
		if(category.equals("特殊")) {
            int a=(Integer)resultList1.get(0).get("satk");
            int b=(Integer)resultList2.get(0).get("sdef");
            damage=Keisan(a,b,wazadata);
        }else if(category.equals("物理")) {
            int a=(Integer)resultList1.get(0).get("atk");
            int b=(Integer)resultList2.get(0).get("defense");
    		damage=Keisan(a,b,wazadata);
            }

		damage*=typemag;
		
		if (wazatype.equals(user_type1) || wazatype.equals(user_type2)) {
			damage *= 1.5;
		}		
		System.out.println(damage);
		
		model.addAttribute("selectPoke1",resultList1);
		model.addAttribute("selectPoke2",resultList2);
		
		model.addAttribute("damage",damage);
		
		
		
		return "p_battle2" ;
		
	}
	private static int Keisan(int a,int b,int power) {
		int A=22*power*a/b;
		int C=A/50+2;
		return C;
		
	}
	private static int wazapower(List<Map<String, Object>> Poke1,List<Map<String, Object>> Poke2,int power) {
		
		switch(power){
		case 1:{
			int weight=(Integer)Poke2.get(0).get("weight");
				if(weight<10) {
					power=20;
				}else if(weight<25) {
					power=40;
				}else if(weight<50) {
					power=60;
				}else if(weight<100) {
					power=80;
				}else if(weight<200) {
					power=100;
				}else {
					power=200;
				}
			
			break;
		}
		case 2:{
			int speed1=(Integer)Poke1.get(0).get("sp");
			int speed2=(Integer)Poke2.get(0).get("sp");
			speed1/=speed2;
			if(speed1<1) {
				power=40;
			}else if(speed1<2) {
				power=60;
			}else if(speed1<3) {
				power=80;
			}else if(speed1<4) {
				power=120;
			}else {
				power=150;
			}
			break;
		}
		case 3:{
			int hp1=(Integer)Poke1.get(0).get("hp");
			power=hp1;
			break;
		}
		case 4:{
			int weight1=(Integer)Poke1.get(0).get("weight");
			int weight2=(Integer)Poke2.get(0).get("weight");
			weight1/=weight2;
			if(weight1<1) {
				power=40;
			}else if(weight1<2) {
				power=60;
			}else if(weight1<3) {
				power=80;
			}else if(weight1<4) {
				power=100;
			}else {
				power=120;
			}
			break;
		}
		case 5:{
			int hp1=(Integer)Poke2.get(0).get("hp");
			power=100*hp1/hp1;
			break;
		}
		case 6:{
			int hp1=(Integer)Poke2.get(0).get("hp");
			power=120*hp1/hp1;
			break;
		}
		case 7:{
			int speed1=(Integer)Poke1.get(0).get("sp");
			int speed2=(Integer)Poke2.get(0).get("sp");
			power=(25*speed2/speed1)+1;
			if(power>150) {
				power=150;
			}
			break;
		}
		case 8:{
			int hp1=(Integer)Poke1.get(0).get("hp");
			int hp2=(Integer)Poke2.get(0).get("hp");
			power=hp2-hp1;
			break;
		}
		default:{
			break;
		}
		}
		return power;
		
	}
	

	private static double Types(double mag1,double mag2) {
			mag1*=mag2;
		return mag1;
		
	}
	
}