package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

public class deletecontroller {
	@RequestMapping(path = "/poke_delete", method = RequestMethod.GET)
	public String battle(HttpSession session,Model model) {

		session.removeAttribute("Poke_id");
		return "p_login";

	}

}
