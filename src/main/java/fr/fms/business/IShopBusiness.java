/**
 * 
 */
package fr.fms.business;

import java.util.List;
import java.util.Optional;

import fr.fms.entities.Article;
import fr.fms.entities.Category;

/**
 * @author Stagiaires08P
 *
 */
public interface IShopBusiness {
	
	//// article

	public void addItem(Article article); // ajoute un article en bdd

	public Article readItem(Long id); // retourne un article depuis la bdd

	public boolean updateItem(Article article,Long id); // met à jour un article dans la bdd

	public boolean deleteItem(Long id); // supprime un article de la bdd

	public List<Article> readAllItems(); // retourne la liste des articles de la bdd

	public List<Article> readAllItemsByCategory(Long id); // retourne la liste des articles d'une catégorie
	
	
	
	
	////// category
	public Category readCategory(Long id); // retourne un article depuis la bdd
	public List<Category> readAllCategories(); // retourne la liste des category de la bdd
	

}
