package com.java.num04;

public class Test {
	public static void main(String[] args) {
		DogCatQueue queue=new DogCatQueue();
		Dog dog1=new Dog();
		Dog dog2=new Dog();
		Dog dog3=new Dog();
		Dog dog4=new Dog();
		Cat cat1=new Cat();
		Cat cat2=new Cat();
		Cat cat3=new Cat();
		Cat cat4=new Cat();
		
		queue.add(cat4);
		queue.add(dog1);
		queue.add(cat1);
		queue.add(dog4);
		queue.add(dog2);
		queue.add(cat3);
		queue.add(dog3);
		queue.add(cat2);
		
		System.out.println(queue.pollAll().getPetType());
	}

}
