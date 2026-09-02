package lab2_ex2.util;

import java.util.List;

public class NumberBox<T extends Number> {
	
	private T element;
	
	private NumberBox() {
		//Hides the default empty constructor to prevent creating a null NumberBox
	}
	
	public NumberBox(T data) {
		this.element = data;
	}
	
	public double add(NumberBox<?> other) {
		return this.element.doubleValue() + other.getElement().doubleValue();
	}
	
	public static double sum(List<NumberBox<Double>> lst) {
		if (lst == null) {
			throw new IllegalArgumentException("List is null");
		}
		
		double sum = 0;
		for (NumberBox<Double> n : lst) {  
			sum += n.getElement().doubleValue();
		}
		return sum;
	}
	
	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}
}
