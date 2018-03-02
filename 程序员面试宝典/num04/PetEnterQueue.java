package com.java.num04;

public class PetEnterQueue {
	private Pet pet;
	private long count;
	public PetEnterQueue(Pet pet, long count) {
		super();
		this.pet = pet;
		this.count = count;
	}
	public PetEnterQueue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pet getPet() {
		return pet;
	}
	public long getCount() {
		return count;
	}
	
	public String getEnterPetType() {
		return pet.getPetType();
	}

}
