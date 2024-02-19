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
	public String battle(String Poke_name1,String Poke_name2,String waza,String terasu1,String terasu2,String hp1,String hp2,String atk1,String atk2,String satk1,String satk2,String def1,String def2,String sdef1,String sdef2,String sp1,String sp2,String button1,String button2,HttpSession session,Model model) {
		List<Map<String, Object>> resultList1,resultList2,resultList3,resultList4,resultList5,resultList6;
		System.out.println(waza);
		
		
		System.out.println(hp1);
		String ID = (String) session.getAttribute("Poke_id");
		resultList1=jdbcTemplate.queryForList("select * from poke2 where jname =?",Poke_name1);
		
		if(button1.equals("ノーマル")) {
			resultList1=jdbcTemplate.queryForList("select * from poke2 where jname =?",Poke_name1);
			System.out.println(resultList1);
		} else if (button1.equals("AI")) {
			resultList1=jdbcTemplate.queryForList("select * from poke_ai where jname=? and user_name =?",Poke_name1,ID);
			System.out.println(resultList1);
		}
		
		resultList2=jdbcTemplate.queryForList("select * from poke2 where jname =?",Poke_name2);
		
		if(button2.equals("ノーマル")) {
			resultList2=jdbcTemplate.queryForList("select * from poke2 where jname =?",Poke_name2);
			System.out.println(resultList1);
		} else if (button2.equals("AI")) {
			resultList2=jdbcTemplate.queryForList("select * from poke_ai where jname=? and user_name =?",Poke_name2,ID);
			System.out.println(resultList1);
		}
		
		resultList3=jdbcTemplate.queryForList("select * from waza where name =?",waza);
		System.out.println(resultList3);
		
		int damage=0;
		
		int wazadata=(Integer)resultList3.get(0).get("power");
		
		
		if(wazadata<10) {
			wazadata=wazapower(resultList1,resultList2,wazadata,hp1,hp2,sp1,sp2);
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
		
		if(terasu1.equals("あり")) {
			user_type1=wazatype;
			user_type2=null;
		}
		if(!(terasu2.equals("なし"))) {
			enemy_type1=terasu2;
			enemy_type2=null;
		}
		System.out.println(user_type1);
		System.out.println(user_type2);
		
		System.out.println(enemy_type1);
		System.out.println(enemy_type2);		
		
		resultList4=jdbcTemplate.queryForList("select * from type where usertype =? and npctype=? ",wazatype,enemy_type1);
		System.out.println(resultList4);
		
		resultList5=jdbcTemplate.queryForList("select * from type where usertype =? and npctype=?",wazatype,enemy_type2);
		System.out.println(resultList5);
		double mag1=1.0;
		double mag2=1.0;
		if (resultList4.size() >= 1) {
			mag1=(double)resultList4.get(0).get("magnification");
			System.out.println("mag1は"+mag1);
		}
		if (resultList5.size() >= 1) {
			mag2=(double)resultList5.get(0).get("magnification");
			System.out.println("mag2は"+mag2);
		}
		
	
		
		double typemag=Types(mag1,mag2);
		System.out.println(typemag);
		
		String category=(String)resultList3.get(0).get("category");
		if(category.equals("特殊")) {
			System.out.println("特殊");
            int a=(Integer)resultList1.get(0).get("satk");
			if (!(Objects.equals(satk1,null))) {
				a += 32;
			}
            int b=(Integer)resultList2.get(0).get("sdef");
            if (!(Objects.equals(sdef2,null))) {
				b += 32;
			}
            damage=Keisan(a,b,wazadata);
        }else if(category.equals("物理")) {
        	System.out.println("物理");
            int a=(Integer)resultList1.get(0).get("atk");
            if (!(Objects.equals(atk1,null))) {
            	a += 32;
            }
            int b=(Integer)resultList2.get(0).get("defense");
            if (!(Objects.equals(def2,null))) {
            	b += 32;
            }
    		damage=Keisan(a,b,wazadata);
            }
			System.out.println(damage);
			
			damage*=typemag;
			
		
		if (wazatype.equals(user_type1) || wazatype.equals(user_type2)) {
			damage *= 1.5;
			if (terasu1.equals("ステラ")) {
				damage *= 2.0;
				}
			
		}else {
			if (terasu1.equals("ステラ")) {
				damage *= 1.2;
				}
		}
		System.out.println(damage);
		
		int hp=(Integer)resultList2.get(0).get("hp");
		
		if (!(Objects.equals(hp2,null))) {
			hp += 32;
			System.out.println("努力値hp");
		}
		
		if(wazadata==3) {
			int user_hp=(Integer)resultList1.get(0).get("hp");
			if (!(Objects.equals(hp1,null))) {
				hp += 32;
			}
			damage=user_hp;;
		}
		hp/=damage;
		if(hp<1) {
			String K="相手のポケモンは倒れた。技の威力は"+damage+"だった。";
			model.addAttribute("damage",K);
		}else if(hp>1) {
			hp+=1;
            String K="相手のポケモンを倒すには"+hp+"回「"+waza+"」を使う必要があるよ。";
            model.addAttribute("damage",K);
            }
		model.addAttribute("selectPoke1",resultList1);
		model.addAttribute("selectPoke2",resultList2);
		
		resultList6=jdbcTemplate.queryForList("select name from waza ");
		model.addAttribute("selectPoke3",resultList6);
		
		
		
		return "p_battle2" ;
		
	}
	private static int Keisan(int a,int b,int power) {
		int A=22*power*a/b;
		int C=A/50+2;
		return C;
		
	}
	private static int wazapower(List<Map<String, Object>> Poke1,List<Map<String, Object>> Poke2,int power,String check1,String check2,String check3,String check4) {
		
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
			if(!(Objects.equals(check3,null))) {
				speed1+=32;
			}
			
			int speed2=(Integer)Poke2.get(0).get("sp");
			if(!(Objects.equals(check4,null))) {
				speed2+=32;
			}
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
			if (!(Objects.equals(check2,null))) {
				hp1 += 32;
			}
			power=100*hp1/hp1;
			break;
		}
		case 6:{
			int hp1=(Integer)Poke2.get(0).get("hp");
			if (!(Objects.equals(check2,null))) {
				hp1 += 32;
			}
			power=120*hp1/hp1;
			break;
		}
		case 7:{
			int speed1=(Integer)Poke1.get(0).get("sp");
			if (!(Objects.equals(check3,null))) {
				speed1 += 32;
			}
			int speed2=(Integer)Poke2.get(0).get("sp");
			if (!(Objects.equals(check4,null))) {
				speed2 += 32;
			}
			power=(25*speed2/speed1)+1;
			if(power>150) {
				power=150;
			}
			break;
		}
		case 8:{
			int hp1=(Integer)Poke1.get(0).get("hp");
			if (!(Objects.equals(check1,null))) {
				hp1 += 32;
			}
			int hp2=(Integer)Poke2.get(0).get("hp");
			if (!(Objects.equals(check2,null))) {
				hp2 += 32;
			}
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