package org.jc.poc.examples.lambdas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LambdaExample {
	public static void main(String[] args) {
		System.out.println("Lambda init");
		List<Person> persons = Arrays.asList(new Person("Pedro", LocalDate.parse("12/01/1930", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Person.Gender.MALE),
				new Person("Jose", LocalDate.parse("15/03/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Person.Gender.MALE),
				new Person("Maria", LocalDate.parse("12/06/1983", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Person.Gender.FEMALE),
				new Person("Tobias", LocalDate.parse("12/06/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Person.Gender.MALE),
				new Person("Lidia", LocalDate.parse("02/06/2012", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Person.Gender.FEMALE)
		);
		//showMayorDe(persons,40);
		//showCondition(persons, new CheckAgeMayorDe30());
		//showCondition(persons, new CheckAgeMayorDe20AndMale());
		showCondition(persons, new CheckedPerson(){
			@Override
			public boolean check(Person p){
				return p.getGender()== Person.Gender.FEMALE && p.getAge()==10;
			}
		});

		//showCondition(persons, p -> p.getGender()== Person.Gender.MALE && p.getAge()==32);
		//showConditionStandart(persons, p -> p.getGender()== Person.Gender.FEMALE && p.getAge()==39);

		List<Person> list2=new ArrayList<Person>();

		/*Collections.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o2.getName().compareTo(o1.getName());
			}
		});*/
		Collections.sort(persons,(p1, p2) -> p1.getName().compareTo(p2.getName()));


		System.out.println("Print all order by name");
		printPersons(persons);
		for(Person p:persons){
			if(p.getAge()>35){
				list2.add(p);
			}
		}
		System.out.println("Print sin streams");
		printPersons(list2);
		System.out.println("Print con streams");
		List<Person> list3= persons.stream()
				.filter(p ->p.getGender()== Person.Gender.FEMALE ).collect(Collectors.toList());
		printPersons(list3);

	}

	 static void showMayorDe(List<Person> persons, int age) {
		for (Person p : persons) {
			if (p.getAge() > age) {
				p.printPerson();
			}
		}
	}
	 static void showCondition(List<Person> persons, CheckedPerson checked){
		for(Person p:persons){
			if(checked.check(p)){
				p.printPerson();
			}
		}
	}

	static void printPersons(List<Person> persons){
		for(Person p: persons){
			p.printPerson();
		}
	}

	static void showConditionStandart(List<Person> persons, Function<Person,Boolean> f){
		for(Person p:persons){
			if(f.apply(p)){
				p.printPerson();
			}
		}
	}

	public interface CheckedPerson{
		boolean check(Person p);
	}

}
class CheckAgeMayorDe30 implements LambdaExample.CheckedPerson {
	@Override
	public boolean check(Person p) {
		return p.getAge()>30;
	}
}

class CheckAgeMayorDe20AndMale implements LambdaExample.CheckedPerson {
	@Override
	public boolean check(Person p) {
		return p.getAge()>20 && p.getGender()== Person.Gender.MALE;
	}
}
