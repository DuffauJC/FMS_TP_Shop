package fr.fms.entities;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long id;
	private String name;
	private String description;
	
	
	@OneToMany(mappedBy="category")
	private Collection<Article>articles;
	
	public Category() {
		super();
	}
	/**
	 * 
	 * @param id
	 * @param catName
	 * @param description
	 */
	public Category(Long id, String name, String description) {
		setId(id);
		setName(name);
		setDescription(description);
		
	}
	/**
	 * 
	 * @param catName
	 * @param description
	 */
	public Category( String name, String description) {
		setName(name);
		setDescription(description);
		
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
