package org.jc.poc.examples.lambdas;

import java.time.LocalDate;

public class Person {
	public enum Gender {
		FEMALE,
		MALE
	}
	private String name;
	private Gender gender;
	private LocalDate birthDay;

	public Person(String name, LocalDate birthDay, Gender gender) {
		this.name = name;
		this.birthDay = birthDay;
		this.gender = gender;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return LocalDate.now().getYear()- birthDay.getYear();
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void printPerson(){
		System.out.println(String.format("Hola soy %s y tengo %d años ", getName(), getAge()));
	}



	public String toString(){
		return "Nombre:"+getName()+ " Edad:"+getAge();
	}
}
