package com.koitt.model;

import java.util.List;

public class Owner {
	
	private String ownerName;
	private List<Pet> petList;
	
	public Owner() {}
	
	public Owner(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public List<Pet> getPetList() {
		return petList;
	}

	public void setPetList(List<Pet> petList) {
		this.petList = petList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Owner [ownerName=");
		builder.append(ownerName);
		builder.append(", petList=");
		builder.append(petList);
		builder.append("]");
		return builder.toString();
	}
}
