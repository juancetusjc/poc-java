package org.jc.poc.examples.lambdas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamExample {
	public static void main(String[] args) {
		System.out.println("Lambda init");
		List<Person> persons = Arrays.asList(new Person("Pedro", LocalDate.parse("12/01/1930", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Person.Gender.MALE),
				new Person("Andy", LocalDate.parse("15/03/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Person.Gender.MALE),
				new Person("Maria", LocalDate.parse("12/06/1983", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Person.Gender.FEMALE),
				new Person("Tobias", LocalDate.parse("12/06/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Person.Gender.MALE),
				new Person("Zule", LocalDate.parse("02/06/2012", DateTimeFormatter.ofPattern("dd/MM/yyyy")), Person.Gender.FEMALE)
		);

		List<Person> list2=new ArrayList<>();

		/*Collections.sort(persons, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o2.getName().compareTo(o1.getName());
			}
		});*/
		Collections.sort(persons, (p1,p2)-> p1.getAge()-p2.getAge());
		//Collections.sort(persons, Comparator.comparing(Person::getName));


		System.out.println("Print all order by name");
		printPersons(persons);
		for(Person p:persons){
			//if(p.getAge()>35){
				list2.add(p);
			//}
		}
		System.out.println("Print sin streams");
		printPersons(list2);
		System.out.println("Print con streams");
		List<Person> list3= list2.stream()
				//.sorted(Comparator.comparing(p -> p.getName()))
				//.sorted(Comparator.comparingInt(p -> p.getAge()))
				.sorted((p1,p2)-> p2.getAge()- p1.getAge())
				//.sorted(Comparator.comparingInt(Person::getAge).reversed())
				.filter(p ->p.getGender()== Person.Gender.MALE ).collect(Collectors.toList());
		printPersons(list3);
		System.out.println("MAP DE LA LISTA");
		Map<String,Person> mapPerson= new LinkedHashMap<>();
		for (Person p:persons) {
			mapPerson.put(p.getName(),p);
		}
		System.out.println("map:"+mapPerson);

		System.out.println("MAP CON STREAMS");
		Map<String,Person> mapStreams= new LinkedHashMap<>();
		mapStreams= list2.stream()
				.filter(p ->p.getGender()== Person.Gender.MALE )
				.sorted(Comparator.comparing(p -> p.getName()))
				.collect(Collectors.toMap(Person::getName,Function.identity()));
		System.out.println("map con stream:"+mapStreams);
		System.out.println("SUMA DE EDADES");
		int sumaEdades= list2.stream()
				.filter(p ->p.getGender()== Person.Gender.MALE )
				.sorted(Comparator.comparing(p -> p.getName()))
				.collect(Collectors.summingInt(Person::getAge));
		System.out.println("Total de edades:"+sumaEdades);

		System.out.println("FOR EACH");
		persons.stream()
				.filter(person ->person.getAge()>30)
				.forEach(p -> {p.printPerson();});

		System.out.println("FOR EACH TRASLADATE");
		List<PersonResponse> listPr= new ArrayList<>();
		persons.stream()
				//.map(p -> new PersonResponse(p))
				.map(PersonResponse::new)
				.filter(person ->person.getAge()>30)
				.forEach(pr -> {
					listPr.add(pr);
					System.out.println("Person Response:"+pr);
				});

	}

	static void printPersons(List<Person> persons){
		for(Person p: persons){
			p.printPerson();
		}
	}
}

class PersonResponse{
	private int age;
	private String name;

	public PersonResponse(String name, int age) {
		this.age=age;
		this.name=name;
	}
	public PersonResponse(Person person){
	 	this.age=person.getAge();
	 	this.name=person.getName();
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString(){
		return "Person Response-> Nombre:"+getName()+ " Edad:"+getAge();
	}
}
