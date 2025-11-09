package com.ananya.ECommerce.dto;

public class ProductDTO {
	private Long id;
	private String name;
	private String description;
	private Double price;
	private int stock;
	private String category;
	private String imageUrl;
	private Double rating;
	public ProductDTO(){
		
	}
	public ProductDTO(Long id,String name,String description,Double price,int stock,String category,String imageUrl,Double rating) {
		this.id=id;
		this.name=name;
		this.description=description;
		this.price=price;
		this.stock=stock;
		this.category=category;
		this.imageUrl=imageUrl;
		this.rating=rating;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	 @Override
	    public String toString() {
	        return "ProductDTO{" +"id=" + id +", name='" + name + '\'' +", description='" + description + '\'' +", price=" + price +", stock=" + stock +", category='" + category + '\'' +", imageUrl='" + imageUrl + '\'' +", rating=" + rating +
	        '}';
	    }
	}

