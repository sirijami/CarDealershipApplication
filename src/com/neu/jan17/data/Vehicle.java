package com.neu.jan17.data;

public class Vehicle {
	// id~webId~category~year~make~model~trim~type~price
	private String id;
	private String webId;
	private Integer year;
	private String make;
	private String model;
	private String trim;
	private Category category;
	private String bodyType;
	private Float price;
	private String photo;




	// getters
	public String getId() {
		return id;
	}

	public String getWebId(){ return webId;}

	public Integer getYear() {
		return year;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public String getTrim() {
		return trim;
	}

	public Category getCategory() {
		return category;
	}

	public String getBodyType() {
		return bodyType;
	}

	public Float getPrice() {
		return price;
	}

	public String getPhoto() {
		return photo;
	}

	//setters

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setWebId(String webId){this.webId = webId;}

	public void setId(String id) {
		this.id = id;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setTrim(String trim) {
		this.trim = trim;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public void setPrice(Float price) {
		this.price = price;
	}


	// set all details of the vehicle
	public void setAllDetails(String id,String webId,Category category,Integer year,
							  String make, String model,String trim, String bodyType, Float price, String photo ){
		this.setId(id);
		this.setWebId(webId);
		this.setCategory(category);
		this.setYear(year);
		this.setMake(make);
		this.setModel(model);
		this.setTrim(trim);
		this.setBodyType(bodyType);
		this.setPrice(price);
		this.setPhoto(photo);
	}

	@Override
	public String toString(){
		return id + "~" + webId + "~" + category.toString().toLowerCase() + "~" + year + "~" + make + "~" +
				 model + "~" + trim + "~" + bodyType + "~" + price + "~" + photo;
	}
}
