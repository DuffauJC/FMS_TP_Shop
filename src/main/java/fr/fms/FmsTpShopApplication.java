package fr.fms;

import java.util.List;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import fr.fms.business.IShopBusinessImpl;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class FmsTpShopApplication implements CommandLineRunner {

	private static Scanner scan = new Scanner(System.in);

	@Autowired
	private IShopBusinessImpl shopJob;

	public static void main(String[] args) {
		SpringApplication.run(FmsTpShopApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		welcome();
		while (true) {
			showMenu();
			mainFunction();
		}
	}

//////
	private static void welcome() {
		System.out.println();
		System.out.println("************************************");
		System.out.println("   WELCOME TO SHOPPING-SHOPPANG");
		System.out.println("************************************");
		System.out.println();
	}

///////
	public static void showMenu() {

		System.out.println("Welcome, what do you want to do ?");
		System.out.println("*******ARTICLES*******");
		System.out.print("1.Voir les articles - " + "2.Voir un  article - " + "3.Ajouter un article - "
				+ "4.Modifier un article - " + "5.Supprimer un article - " + "6.Voir les articles par category -\n");

		System.out.println("*******CATEGORY*******");
		System.out.print("7.Voir les catégories - " + "8.Voir une categorie - " + "9.Ajouter une catégorie - "
				+ "10.Modifier une catégorie - " + "11.Supprimer une catégorie - " + "12.Sortir \n");

	}

///////
	public void mainFunction() {

		int action = 0;
		Long index;

		while (action != 12) {
			try {

				action = scan.nextInt();

				switch (action) {
				case 1: // show all items
					showAllItems();
					//displayArticlesPaginate(scan);
					showMenu();
					break;

				case 2: // show item by id
					showItemById();
					showMenu();
					break;

				case 3: // add item to bdd
					addItem();
					break;

				case 4: // update item on bdd
					updateItem();
					break;
				case 5: // delete item on bdd
					deleteItem();
					break;
				case 6: // show items by category
					showAllItemsByCategory();
					break;
				///// ****Category****//////
				case 7: // show all category
					showAllCategory();
					showMenu();
					break;

				case 8: // show item by id
					showCategoryById();
					showMenu();
					break;

				case 9: // add item to bdd
					addCategory();
					showMenu();
					break;

				case 10: // update item on bdd
					updateCategory();
					showMenu();
					break;
				case 11: // delete item on bdd
					deleteCategory();
					showMenu();
					break;
				case 12: // Exit account
					System.out.println("Exit shop.");
					break;

				default:
					System.out.println(" Wrong entry, your choice: " + action + " does not exist in the menu");
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

	}
//////////////
	private void menuPaginate() {
		System.out.println("EXIT    pour sortir de la pagination");
		System.out.println("PREV    pour afficher la page précédente");
		System.out.println("NEXT    pour afficher la page suivante");
		System.out.println("PAGE 7  pour afficher 7 articles par page (par défaut c'est 5)");
	}
/////////////
	private void displayArticlesPaginate(Scanner scan) {
		int currentPage = 0;
		int nbPage = 5;
		boolean flag = true;
		
		menuPaginate();
		
		while(flag) {
			try {
				Page<Article> art = shopJob.getArticlesPages(PageRequest.of(currentPage, nbPage));						
				if(art.getTotalPages() != 0) {
					//displayTitles();
					
						
						System.out.println("List of articles.\n");
					System.out.println("---------------------------------------------------------------------------------");
					System.out.printf("| %-15s | %-14s | %-30s | %-10s |%n", "ID", "BRAND", "DESCRIPTION", "PRICE");
					System.out.println("|-----------------|----------------|---------------------------------------------|");

					art.stream().forEach(a -> 
						System.out.printf("| %-15s | %-14s | %-30s | %-10s |%n", a.getId(), a.getBrand(),
								a.getDescription(), a.getUnitaryPrice()));
					
					System.out.println("---------------------------------------------------------------------------------");
						
				
					//articles.stream().forEach(a -> System.out.println(a));
					System.out.print("\n PREV [");
					for(int i=0 ; i<art.getTotalPages() ; i++) {
						if(i == currentPage)	System.out.print(" {" + i + "} ");
						else System.out.print(" "+i+" ");
					}
					System.out.println("] NEXT");
					System.out.println("Tapez 0 pour Afficher le menu de pagination \n");
					
					String action = scan.next();			
					if(action.equalsIgnoreCase("PREV")) {
						if(currentPage > 0) currentPage--; 
					}
					else if	(action.equalsIgnoreCase("NEXT")) {
						if(currentPage < art.getTotalPages()-1)	currentPage++;		
					}
					else if(action.equalsIgnoreCase("EXIT")) {
						flag = false;
					}
					else if(action.equalsIgnoreCase("PAGE")) {
						System.out.println("saisissez le nombre d'articles par page :");
						nbPage = scan.nextInt();
						currentPage = 0;
					}
					else if(action.equalsIgnoreCase("0")) {
						menuPaginate();
					}
				}
				else {
					System.out.println("TABLE VIDE EN BDD !");
					flag = false;
				}
			}
			catch(Exception e) {
				System.out.println("Erreur de communication avec la base : " + e.getMessage());
				flag = false;
			}
		}
	}

//////////////
	public void showAllItems() {
		List<Article> art;
		try {
			art = shopJob.readAllItems();
			System.out.println("List of articles.\n");
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-14s | %-30s | %-10s |%n", "ID", "BRAND", "DESCRIPTION", "PRICE");
		System.out.println("|-----------------|----------------|---------------------------------------------|");

		for (int i = 0; i < art.size(); i++) {
			System.out.printf("| %-15s | %-14s | %-30s | %-10s |%n", art.get(i).getId(), art.get(i).getBrand(),
					art.get(i).getDescription(), art.get(i).getUnitaryPrice());
		}
		System.out.println("---------------------------------------------------------------------------------");
			
		} catch (Exception e) {
			System.out.println("Erreur de bdd" +e.getMessage());
			
		}
		
	}

/////////////
	public void showAllCategory() {
		List<Category> cat = shopJob.readAllCategories();
		System.out.println("List of catégories.\n");
		System.out.println("---------------------------------------------------------------------");
		System.out.printf("| %-15s | %-14s | %-30s |%n", "ID", "NOM", "DESCRIPTION");
		System.out.println("|-----------------|----------------|--------------------------------|");

		for (int i = 0; i < cat.size(); i++) {
			System.out.printf("| %-15s | %-14s | %-30s |%n", cat.get(i).getId(), cat.get(i).getName(),
					cat.get(i).getDescription());
		}
		System.out.println("---------------------------------------------------------------------");

	}

/////////////
	public void showItemById() {
		System.out.println("Type the id.");
		while (!scan.hasNextLong()) {
			System.out.println("The entered value is incorrect, enter a new entry.");
			scan.next();
		}
		Long index = scan.nextLong();
		Article art = shopJob.readItem(index);
		System.out.println("---------------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-14s | %-30s | %-10s |%n", "ID", "BRAND", "DESCRIPTION", "PRICE");
		System.out.println("|-----------------|----------------|---------------------------------------------|");
		System.out.printf("| %-15s | %-14s | %-30s | %-10s |%n", art.getId(), art.getBrand(), art.getDescription(),
				art.getUnitaryPrice());
		System.out.println("---------------------------------------------------------------------------------");
	}

/////////////
	public void showCategoryById() {
		System.out.println("Type the id.");
		while (!scan.hasNextLong()) {
			System.out.println("The entered value is incorrect, enter a new entry.");
			scan.next();
		}
		Long index = scan.nextLong();
		Category cat = shopJob.readCategory(index);
		System.out.println("---------------------------------------------------------------------");
		System.out.printf("| %-15s | %-14s | %-30s |%n", "ID", "NOM", "DESCRIPTION");
		System.out.println("|-----------------|----------------|--------------------------------|");
		System.out.printf("| %-15s | %-14s | %-30s |%n", cat.getId(), cat.getName(), cat.getDescription());
		System.out.println("---------------------------------------------------------------------");
	}

/////////
	public void deleteItem() {
		showAllItems();

		System.out.println("Type the id.");
		while (!scan.hasNextLong()) {
			System.out.println("The entered value is incorrect, enter a new entry.");
			scan.next();
		}
		Long index = scan.nextLong();
		boolean ok = shopJob.deleteItem(index);
		if (ok) {
			System.out.println("Article delete");
		}
	}

/////////
	public void deleteCategory() {
		showAllCategory();

		System.out.println("Type the id.");
		while (!scan.hasNextLong()) {
			System.out.println("The entered value is incorrect, enter a new entry.");
			scan.next();
		}
		Long index = scan.nextLong();
		boolean ok = shopJob.deleteCategory(index);
		if (ok) {
			System.out.println("Article delete");
		}
	}

/////////	
	public void showAllItemsByCategory() {
		showAllCategory();
		System.out.println("Type the id.");
		while (!scan.hasNextLong()) {
			System.out.println("The entered value is incorrect, enter a new entry.");
			scan.next();
		}
		Long index = scan.nextLong();
		List<Article> art = shopJob.readAllItemsByCategory(index);
		System.out.println("List of articles on category.\n");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------");
		System.out.printf("| %-15s | %-14s | %-30s | %-10s | %-20s |%n", "ID", "BRAND", "DESCRIPTION", "PRICE",
				"CATEGORY");
		System.out.println(
				"|-----------------|----------------|--------------------------------------------------------------------|");

		for (int i = 0; i < art.size(); i++) {
			System.out.printf("| %-15s | %-14s | %-30s | %-10s | %-20s |%n", art.get(i).getId(), art.get(i).getBrand(),
					art.get(i).getDescription(), art.get(i).getUnitaryPrice(), art.get(i).getCategory().getName());
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------------");
	}
/////////	

	public void addItem() {

		int rep = 1;

		while (rep != 2) {

			System.out.println("1 : To create item.");
			System.out.println("2 : Leave.");

			while (!scan.hasNextInt()) {
				System.out.println("The entered value is incorrect, enter a new entry.");
				scan.next();
			}
			rep = scan.nextInt();

			switch (rep) {
			case 1: // Create item
				System.out.println("Type brand.");
				String brand = scan.next();

				System.out.println("Type description.");
				scan.nextLine();
				String desc = scan.nextLine();

				System.out.println("Type price.");
				double price = scan.nextDouble();

				System.out.println("Choose category : type id.");
				showAllCategory();
				Long id = scan.nextLong();
				Category cat = shopJob.readCategory(id);

				shopJob.addItem(new Article(brand, desc, price, cat));
				System.out.println("Article ajouté");
				break;

			case 2: // Exit
				System.out.println("Exit.");
				showMenu();
				break;

			default:
				System.out.println("Wrong entry, your choice: " + rep + " does not exist in the menu");
			}
		}
	}

//////////
	public void updateItem() {

		int rep = 1;

		while (rep != 2) {

			System.out.println("1 : To update item.");
			System.out.println("2 : Leave.");

			while (!scan.hasNextInt()) {
				System.out.println("The entered value is incorrect, enter a new entry.");
				scan.next();
			}
			rep = scan.nextInt();

			switch (rep) {
			case 1: // Update item

				showAllItems();
				System.out.println("Type the id.");
				Long index = scan.nextLong();
				Article art = shopJob.readItem(index);

				/////////////////////////////////

				int rep2 = 1;

				while (rep2 != 5) {

					System.out.println("1 : To update brand.");
					System.out.println("2 : To update description.");
					System.out.println("3 : To update price.");
					System.out.println("4 : To update category.");
					System.out.println("5 : Leave.");

					while (!scan.hasNextInt()) {
						System.out.println("The entered value is incorrect, enter a new entry.");
						scan.next();
					}
					rep2 = scan.nextInt();

					switch (rep2) {
					case 1: // Update brand

						System.out.println("Type brand.");
						String brand = scan.next();
						art.setBrand(brand);
						break;

					case 2: // Update description
						System.out.println("Type description.");
						scan.nextLine();
						String desc = scan.nextLine();
						art.setDescription(desc);
						break;
					case 3: // Update price
						System.out.println("Type price.");
						double price = scan.nextDouble();
						art.setUnitaryPrice(price);
						break;
					case 4: // Update category
						System.out.println("Choose category : type id.");
						showAllCategory();
						Long id = scan.nextLong();
						Category cat = shopJob.readCategory(id);
						art.setCategory(cat);
						break;
					case 5: // Exit
						System.out.println("Exit.");
						break;
					default:
						System.out.println("Wrong entry, your choice: " + rep + " does not exist in the menu");

					}

				}

				////////////////////////////////////
				boolean ok = shopJob.updateItem(art, art.getId());
				if (ok) {
					System.out.println("Article modifié");
				}
				break;

			case 2: // Exit
				System.out.println("Exit.");
				showMenu();
				break;

			default:
				System.out.println("Wrong entry, your choice: " + rep + " does not exist in the menu");
			}
		}
	}
/////////	

	public void addCategory() {

		int rep = 1;

		while (rep != 2) {

			System.out.println("1 : To create category.");
			System.out.println("2 : Leave.");

			while (!scan.hasNextInt()) {
				System.out.println("The entered value is incorrect, enter a new entry.");
				scan.next();
			}
			rep = scan.nextInt();

			switch (rep) {
			case 1: // Create category
				System.out.println("Type name.");
				String name = scan.next();

				System.out.println("Type description.");
				scan.nextLine();
				String desc = scan.nextLine();

				shopJob.addCategorie(new Category(name, desc));
				System.out.println("Catégorie ajoutée.");
				break;
			case 2: // Exit
				System.out.println("Exit.");
				showMenu();
				break;
			default:
				System.out.println("Wrong entry, your choice: " + rep + " does not exist in the menu");
			}
		}
	}

//////////
	public void updateCategory() {

		int rep = 1;

		while (rep != 2) {

			System.out.println("1 : To update category.");
			System.out.println("2 : Leave.");

			while (!scan.hasNextInt()) {
				System.out.println("The entered value is incorrect, enter a new entry.");
				scan.next();
			}
			rep = scan.nextInt();

			switch (rep) {
			case 1: // Update item

				showAllCategory();
				System.out.println("Type the id.");
				Long index = scan.nextLong();
				Category cat = shopJob.readCategory(index);

				/////////////////////////////////

				int rep2 = 1;

				while (rep2 != 3) {

					System.out.println("1 : To update name.");
					System.out.println("2 : To update description.");
					System.out.println("3 : Leave.");

					while (!scan.hasNextInt()) {
						System.out.println("The entered value is incorrect, enter a new entry.");
						scan.next();
					}
					rep2 = scan.nextInt();

					switch (rep2) {
					case 1: // Update brand

						System.out.println("Type name.");
						String name = scan.next();
						cat.setName(name);
						break;

					case 2: // Update description
						System.out.println("Type description.");
						scan.nextLine();
						String desc = scan.nextLine();
						cat.setDescription(desc);
						break;
					case 3: // Exit
						System.out.println("Exit.");
						break;
					default:
						System.out.println("Wrong entry, your choice: " + rep + " does not exist in the menu");
					}
				}
				////////////////////////////////////
				boolean ok = shopJob.updateCategory(cat, cat.getId());
				if (ok) {
					System.out.println("Categorie modifiée");
				}
				break;

			case 2: // Exit
				System.out.println("Exit.");
				showMenu();
				break;

			default:
				System.out.println("Wrong entry, your choice: " + rep + " does not exist in the menu");
			}
		}
	}
}
