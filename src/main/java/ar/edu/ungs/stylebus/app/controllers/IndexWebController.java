package ar.edu.ungs.stylebus.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public final class IndexWebController {
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
