package org.jc.poc.examples.test;

import org.jc.poc.examples.Example2;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestExample3
{
	@Test
	public void orderByStream(){
		List<String> list = Arrays.asList("D", "A", "Z", "R", "B", "Y", "4", "a", "c");
		List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
		sortedList.forEach(System.out::println);
	}

}
