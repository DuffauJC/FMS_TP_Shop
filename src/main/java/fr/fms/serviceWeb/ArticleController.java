package fr.fms.serviceWeb;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/home")
    public String home() {
        return "home";
    }
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    // affiche les articles sur la page articles depuis le lien index
    @GetMapping("/articles")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "keyword", defaultValue = "") String kw) {
        Page<Article> articles = articleRepository.findByDescriptionContains(kw, PageRequest.of(page, 5));
        // Page<Article> articles = articleRepository.findAll(PageRequest.of(page, 5));

        model.addAttribute("listArticle", articles.getContent());
        model.addAttribute("pages", new int[articles.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);
       
        return "articles";
    }

    // supprime un article au clic sur le lien html
    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) {
        articleRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    // retourne formulaire ajout d'un article et retourne la liste des categories
    @GetMapping("/article")
    public String article(Model model,Article article
            
    ) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("listCategories", categories);
       // model.addAttribute("article",new Article());
        return "article";
    }

    // retourne formulaire ajout d'un article
    @PostMapping("/save")
    public String save(Model model, @Valid Article article, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "article";
        }

        articleRepository.save(article);
        return "redirect:/index";
    }

    @GetMapping("/editArticle")
    public String editArticle(Model model, Long id, Article article,String catName) {

        Optional<Article> art = articleRepository.findById(id);
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("listCategories", categories);
        model.addAttribute("article", art);
        model.addAttribute("catName", art.get().getCategory().getName());
        // System.out.println(art);
        // System.out.println(art.get().getCategory().getName());
        return "editArticle";
    }

    
}
