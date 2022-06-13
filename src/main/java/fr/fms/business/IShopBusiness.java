/**
 * 
 */
package fr.fms.business;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

/**
 * @author Stagiaires08P
 *
 */
public interface IShopBusiness<T> {
	
	
//	public List<T> readAll()throws Exception;
//	public void addOne(T t);
//	public boolean updateOne(T t,Long id);
//	public boolean deleteOne(T t,Long id) throws Exception;
//	public Optional<T> getOneById(Long id);

	//// article

	public void addItem(Article article); // ajoute un article en bdd

	public Article readItem(Long id); // retourne un article depuis la bdd

	public boolean updateItem(Article article, Long id); // met à jour un article dans la bdd

	public boolean deleteItem(Long id); // supprime un article de la bdd

	public List<Article> readAllItems()throws Exception; // retourne la liste des articles de la bdd
	
	Page<Article> getArticlesPages(Pageable pageable) throws Exception;
	
	public List<Article> readAllItemsByCategory(Long id); // retourne la liste des articles d'une catégorie
	
	

	////// category
	public void addCategorie(Category category); // ajoute une categorie en bdd

	public Category readCategory(Long id); // retourne une categorie depuis la bdd

	public boolean updateCategory(Category category, Long id); // met à jour une categorie dans la bdd

	public boolean deleteCategory(Long id); // supprime une categorie de la bdd

	public List<Category> readAllCategories(); // retourne la liste des category de la bdd

}
