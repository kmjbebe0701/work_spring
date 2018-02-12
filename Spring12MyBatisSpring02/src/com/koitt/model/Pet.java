package com.koitt.model;

import java.util.Date;

public class Pet {
	
	private Integer petNo;
	private String petName;
	private String ownerName;
	private Integer price;
	private Date birthDate;
	
	public Pet() {
	}
	
	public Pet(Integer petNo, String petName, String ownerName, Integer price, Date birthDate) {
		super();
		this.petNo = petNo;
		this.petName = petName;
		this.ownerName = ownerName;
		this.price = price;
		this.birthDate = birthDate;
	}

	public Integer getPetNo() {
		return petNo;
	}

	public void setPetNo(Integer petNo) {
		this.petNo = petNo;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pet [petNo=");
		builder.append(petNo);
		builder.append(", petName=");
		builder.append(petName);
		builder.append(", ownerName=");
		builder.append(ownerName);
		builder.append(", price=");
		builder.append(price);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append("]");
		return builder.toString();
	}
	
	

	

}
