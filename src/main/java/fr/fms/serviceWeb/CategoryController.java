package fr.fms.serviceWeb;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@Controller
public class CategoryController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    // affiche les articles sur la page articles depuis le lien index
    @GetMapping("/categories")
    public String categories(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {

        Page<Category> categories = categoryRepository.findAll(PageRequest.of(page, 5));
        model.addAttribute("listCategories", categories.getContent());
        model.addAttribute("pages", new int[categories.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return "categories";
    }

    @GetMapping("/categoryArticles")
    public String categoryArticles(Model model, Article article, Category category, Long id,
            @RequestParam(name = "page", defaultValue = "0") int page) {

        Page<Article> articles = articleRepository.findByCategoryId(id, PageRequest.of(page, 5));
        Optional<Category> cat = categoryRepository.findById(id);
        List<Category> categories = categoryRepository.findAll();
        
        model.addAttribute("listCategoryArticles", articles.getContent());
        model.addAttribute("listCategories", categories);
        model.addAttribute("pages", new int[articles.getTotalPages()]);
        model.addAttribute("catName", cat.get().getName());
        return "articlesByCategory";
    }
}
