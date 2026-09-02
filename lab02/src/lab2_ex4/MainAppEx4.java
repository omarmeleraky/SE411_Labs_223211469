package lab2_ex4;

import java.util.Arrays;
import java.util.List;

public class MainAppEx4 {

	public static void main(String[] args) {
		
		List<String> words = Arrays.asList("apple", "banana", "cherry");
		printList(words);
		System.out.println("");
		List<Integer> integers = Arrays.asList(10, 20, 30);
		printList(integers);
		
		List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);
		System.out.println("");
		System.out.println(sumNumbers(doubles));
		
		List<Number> mixed = Arrays.asList(10, 20.5);
		System.out.println("");
		System.out.println(sumNumbers(mixed));
	}
	
	public static void printList(List<?> list) {
		for (Object item : list) {
			System.out.println(item);
		}
	}
	
	public static double sumNumbers(List<? extends Number> list) {
		double sum = 0;
		for (Number n : list) {
			sum += n.doubleValue();
		}
		return sum;
	}
}