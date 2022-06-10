package fr.fms.serviceWeb;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

	@GetMapping("/greeting")
	public String greeting() {
		
		return "greeting";
	}
	
	
}
