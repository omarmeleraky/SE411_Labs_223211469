package lab2_ex3;

import lab2_ex3.util.Pipeline;
import lab2_ex3.util.Transformer;

public class MainAppEx3 {

	public static void main(String[] args) {
		
		Pipeline<Integer> start = new Pipeline<>(10);
		
		Pipeline<Integer> step1 = start.add(new Transformer<Integer, Integer>() {
			@Override
			public Integer transform(Integer input) {
				return input * 2;
			}
		});
		
		Pipeline<String> step2 = step1.add(new Transformer<Integer, String>() {
			@Override
			public String transform(Integer input) {
				return "Final Valu:" + input;
			}
		});
		
		System.out.println(step2.execute());
		
		
		String result = new Pipeline<>(5)
			.add((Integer x) -> x + 3)                
			.add((Integer x) -> "Value:" + x)      
			.execute();
		
		System.out.println(result);
	}
}