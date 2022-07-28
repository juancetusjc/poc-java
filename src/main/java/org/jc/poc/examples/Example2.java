package org.jc.poc.examples;

import java.util.Locale;

public class Example2 implements IDefaultSaludo
{
	@Override
	public String saludoCustom(String name) {
		return "Hola ".concat(name);
	}
}

interface IDefaultSaludo{
	 static Locale defaultLocale(){
		return Locale.getDefault();
	}
	 default String saludo(String name){
		if (name==null || name.isEmpty()){
			return "Hola Chulo";
		}
		return "Hola ".concat(name);
	}
	default Locale locale(){
	 	return defaultLocale();
	}

	 String saludoCustom(String name);
}
