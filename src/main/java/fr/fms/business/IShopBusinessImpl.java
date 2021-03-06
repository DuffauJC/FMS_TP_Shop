/**
 * 
 */
package fr.fms.business;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

/**
 * @author Stagiaires08P
 *
 */
@Service
public class IShopBusinessImpl implements IShopBusiness {

	private List<Article> itemsToBdd = null;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ArticleRepository articleRepository;

	public IShopBusinessImpl() {

	}

	@Override
	public void addItem(Article article) {
		// TODO Auto-generated method stub
		this.articleRepository.save(article);
	}
	@Override
	public Article readItem(Long id) {
		Article art = this.articleRepository.findById(id).get();
		if (art != null) {
			return art;
		} else {
			throw new RuntimeException("Article inexistant dans la bdd !");
		}
	}

	@Override
	public boolean updateItem(Article article, Long id) {
		Article art = this.readItem(id);
		if (art != null) {
			this.articleRepository.save(article);
			return true;
		} else {
			throw new RuntimeException("Article inexistant dans la bdd!");
		}
	}
	@Override
	public boolean deleteItem(Long id) {
		Article art = this.readItem(id);
		if (art != null) {
			this.articleRepository.delete(art);
			return true;
		} else {
			throw new RuntimeException("Article inexistant dans la bdd!");
		}

	}

	@Override
	public List<Article> readAllItems()throws Exception {
		// TODO Auto-generated method stub
		return this.articleRepository.findAll();
	}
	
	@Override
	public Page<Article> getArticlesPages(Pageable pageable) throws Exception {
		return articleRepository.findAll(pageable);
	}
	@Override
	public List<Article> readAllItemsByCategory(Long id) {
		this.itemsToBdd=this.articleRepository.findByCategoryId(id);
		if (this.itemsToBdd.isEmpty()) {
			throw new RuntimeException("Aucun articles dans cette cat??gorie !");
		}else {
			return this.itemsToBdd;
		}
		
	}
	@Override
	public List<Category> readAllCategories() {
		// TODO Auto-generated method stub
		return this.categoryRepository.findAll();
	}
	@Override
	public void addCategorie(Category category) {
		this.categoryRepository.save(category);
		
	}
	@Override
	public Category readCategory(Long id) {
		Category cat = this.categoryRepository.findById(id).get();
		if (cat !=null) {
			return cat;
		}else {
			throw new RuntimeException("Category inexistante dans la bdd!");
		}
		
	}


	@Override
	public boolean updateCategory(Category category,Long id) {
		Category cat = this.readCategory(id);
		if (cat != null) {
			this.categoryRepository.save(category);
			return true;
		} else {
			throw new RuntimeException("Category inexistante dans la bdd!");
		}
	}

	@Override
	public boolean deleteCategory(Long id) {
		Category cat = this.readCategory(id);
		if (cat != null) {
			this.categoryRepository.delete(cat);
			return true;
		} else {
			throw new RuntimeException("Categorie inexistante dans la bdd!");
		}
	}

}
