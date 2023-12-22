package com.example.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.example.demo.bean.Response_Poke;
import com.example.demo.bean.SendData;

@Controller
public class P_AIcreate_controller {

	
	
	@Autowired
	RestTemplate restTemplate ;
	
	@RequestMapping(path="/poke_Ai",method=RequestMethod.GET)
	public String battle(Model model) {
		
		return "p_Ai" ;
		
	}
	
	@RequestMapping(path="/poke_Ai",method=RequestMethod.POST)
	public String battle_gra(String Poke_id,String Poke_pass,Model model) {
		
			com.example.demo.bean.SendData senddata = new com.example.demo.bean.SendData();
			
			
			senddata.setKey("ffd8e7f0-2901-4358-bc8d-f9d37375f189");

			//リクエスト内容
			senddata.setModel("v1-5-pruned-emaonly.safetensors [d7049739]");
			senddata.setPrompt(Poke_pass);
			senddata.setNegative_prompt("badly drawn");
			senddata.setModel("");
			senddata.setStyle_preset("anime");
			
			
			 try {
				RequestEntity<SendData> request = RequestEntity
						.post(new URI("https://api.prodia.com/v1/sd/models"))
						.accept(MediaType.APPLICATION_JSON)
						.body(senddata);
				

				restTemplate.getForObject("https://api.prodia.com/v1/sd/models",Response_Poke.class,request);//保存
				System.out.println("test");
			} catch (URISyntaxException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
				//リクエスト内容格納と送信↓
			
			
		
				return "p_Ai";
				

		}
	
}
