package org.jc.poc.examples;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Example1 {
	private static final String  TEXT_SALUDO="Hola";
	private static final String  TEXT_SPACE_SALUDO=" ";
	public String responseHello(String name){
		return TEXT_SALUDO.concat(TEXT_SPACE_SALUDO).concat(name);
	}

	public List<String> ordenarNombres(List<String> nombres) {
		Comparator<String> comparator= (o1,o2)->o1.length()-o2.length();
		 Collections.sort(nombres,comparator);
		 return nombres;
	}
}


