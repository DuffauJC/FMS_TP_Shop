package fr.fms;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.dao.ArticleRepository;

@SpringBootApplication
public class FmsTpShop_Test implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ArticleRepository articleRepository;

	public static void main(String[] args) {
		SpringApplication.run(FmsTpShop_Test.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//
//		Category laptop = categoryRepository.save(new Category("Laptop","Ordinateur portable ou pas"));
//		Category smartphone = categoryRepository.save(new Category("Smartphone","Pour faire plus qu'appeler"));
//		Category mat_info = categoryRepository.save(new Category("Materiel info","Tout pour votre tour"));
//		Category logiciel = categoryRepository.save(new Category("Logiciel","Solutions pour se sentir fort"));
//
//		articleRepository.save(new Article("Souris", "Logitech", 85, mat_info));
//		articleRepository.save(new Article("Clavier", "Corsair", 49.5, mat_info));
//		articleRepository.save(new Article("Système d'exploitation", "Window", 100, logiciel));
//		articleRepository.save(new Article("Antivirus", "Norton", 34.99, logiciel));
//		articleRepository.save(new Article("Corsair II", "Corsair", 2350, laptop));
//		articleRepository.save(new Article("Notebook", "HP", 1350, laptop));
//		articleRepository.save(new Article("S22", "Samsung", 850, smartphone));
//		articleRepository.save(new Article("Iphone45", "Xiaomi", 8450, smartphone));
		
//	  for (Article article : articleRepository.findAll()) {
//		  System.out.println(article);
//	  
//		  } 

	}

}
