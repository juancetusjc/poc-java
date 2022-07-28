package org.jc.poc.examples;

public class Example3 {
	 public Constante devuelve5(){
			return ()->5;
		}

	public Operador operadorSuma(){
	    return (a,b)->(a+b);
	}
}


interface Constante {
	public int valor();
}

interface Operador {
	public int opera ( int a, int b);
}
