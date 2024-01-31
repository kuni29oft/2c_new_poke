package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class P_AIcreate_controller {


	@RequestMapping(path = "/poke_Ai", method = RequestMethod.GET)
	public String battle(Model model) {

		return "p_Ai";

	}


}
