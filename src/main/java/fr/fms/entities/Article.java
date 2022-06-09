package fr.fms.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long id;
	private String description;
	private String brand;
	private double unitaryPrice;
	private int qty=1;


	@ManyToOne
	private Category category;
	
	public Article() {
		super();
	}
	/**
	 * 
	 * @param id
	 * @param description
	 * @param brand
	 * @param unitaryPrice
	 */
	public Article(Long id,String brand,String description, double unitaryPrice,Category category) {

		setId(id);
		setDescription(description);
		setBrand(brand);
		setUnitaryPrice(unitaryPrice);
		setQty(qty);
		setCategory(category);

	}
	/**
	 * 
	 * @param description
	 * @param brand
	 * @param unitaryPrice
	 */
	public Article(String brand, String description,  double unitaryPrice,Category category) {

		setDescription(description);
		setBrand(brand);
		setUnitaryPrice(unitaryPrice);
		setCategory(category);
	}



	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}


	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", description=" + description + ", brand=" + brand
				+ ", unitaryPrice=" + unitaryPrice + ", qty=" + qty + ", category=" + category + "]";
	}


}
