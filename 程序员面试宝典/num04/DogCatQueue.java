package com.java.num04;

import java.util.LinkedList;
import java.util.Queue;

public class DogCatQueue {
	private Queue<PetEnterQueue> dogQ;
	private Queue<PetEnterQueue> catQ;
	private long count;
	
	public DogCatQueue() {
		dogQ=new LinkedList<PetEnterQueue>();
		catQ=new LinkedList<PetEnterQueue>();
		count=0;
	}
	
	public void add(Pet pet) {
		if(pet.getPetType().equals("dog")) {
			dogQ.add(new PetEnterQueue(pet,count++));
		}else if(pet.getPetType().equals("cat")) {
			catQ.add(new PetEnterQueue(pet,count++));
		}else {
			throw new RuntimeException("err,not dog or cat");
		}
	}
	
	public Pet pollAll() {
		if(!this.dogQ.isEmpty() && !this.catQ.isEmpty()) {
			if(dogQ.peek().getCount()<catQ.peek().getCount()) {
				return dogQ.poll().getPet();
			}else {
				return catQ.poll().getPet();
			}
		}else if(!dogQ.isEmpty()) {
			return dogQ.poll().getPet();
		}else if(!catQ.isEmpty()) {
			return catQ.poll().getPet();
		}else {
			throw new RuntimeException();
		}
		
	}
	public boolean isEmpty() {
		return this.dogQ.isEmpty() && this.catQ.isEmpty();
	}
	
	public boolean isDogQueueEmpty() {
		return this.dogQ.isEmpty();
	}
	
	public boolean isCatQueueEmpty() {
		return this.catQ.isEmpty();
	}
	
	public Dog pollDog() {
		if(!this.isDogQueueEmpty()) {
			return (Dog) this.dogQ.poll().getPet();
		}else {
			throw new RuntimeException();
		}
	}
	
	public Cat pollCat() {
		if(!this.isCatQueueEmpty()) {
			return (Cat)this.catQ.poll().getPet();
		}else {
			throw new RuntimeException();
		}
	}

}
