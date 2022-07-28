package org.jc.poc.examples.test;

import org.jc.poc.examples.Example1;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class TestExample1 {

	@Test
	public void validaSaludo(){
		Example1 example1 =new Example1();
		Assert.assertEquals("Hola Juan",example1.responseHello("Juan"));

	}

	@Test
	public void eliminarClients(){
		Example1 example1 =new Example1();
		List<String> nombres= Arrays.asList("Juan","Antonia","Pedro");
		List<String> espected= Arrays.asList("Juan","Pedro","Antonia");

		Assert.assertEquals(espected,example1.ordenarNombres(nombres));
	}
}
