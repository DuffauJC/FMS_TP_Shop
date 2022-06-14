package fr.fms.serviceWeb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestArticleController {

	@Autowired
	private ArticleRepository articleRepository;

	@GetMapping("/articles")
	public List<Article> getArticles() {
		return (List<Article>) articleRepository.findAll();
	}

}
