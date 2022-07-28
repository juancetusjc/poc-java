package org.jc.poc.examples.test;


import org.jc.poc.examples.Example2;
import org.jc.poc.examples.Example3;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class TestExample2 {

	@Test
	public void saludoDefault(){
		Example2 example2 =new Example2();
		Assert.assertEquals("Hola Chulo",example2.saludo(null));
	}

	@Test
	public void defaltLocale(){
		org.jc.poc.examples.Example2  example2=new Example2();
		Assert.assertEquals(new Locale("es","AR"),example2.locale());
	}
	@Test
	public void test_sumador () {
		Example3 example3= new Example3();
		assertEquals(10, example3.operadorSuma());
	}

	@Test
	public void test_devuelve5 () {
		Example3 example3= new Example3();
		Assert.assertEquals(5, example3.devuelve5());
	}
	public static final SimpleDateFormat DATE_FORMAT_BACEN = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
	public static final SimpleDateFormat DATE_FORMAT_PIX = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
	@Test
	public void test_fecha(){
			String inputDate="2022-05-15T00:16:00Z";
					//"2022-05-04T09:45:33";

				        	//"2022-05-04T02:59:33.990Z";
			              // "2022-05-04T03:02:00.51Z";
			                 //2022-05-10T17:33:00Z
							 //2022-05-10T17:33:00Z
							//2022-05-04T02:59:33.990Z
			String outDate= inputDate.length() == 24 ? inputDate : parseDate(inputDate,DATE_FORMAT_BACEN, TimeZone.getTimeZone("UTC"), DATE_FORMAT_PIX, TimeZone.getTimeZone("UTC"));
			Assert.assertEquals("2022-05-10T17:33:00Z",outDate);
	}
	public static String parseDate(String originalDate, SimpleDateFormat originalFormat, TimeZone originalTimeZone, SimpleDateFormat parsedFormat, TimeZone parsedTimeZone) {
		Date date;
		originalFormat.setTimeZone(originalTimeZone);
		parsedFormat.setTimeZone(parsedTimeZone);
		try {
			date = originalFormat.parse(originalDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return parsedFormat.format(date);
	}
}
