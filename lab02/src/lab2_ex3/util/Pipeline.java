package lab2_ex3.util;

public class Pipeline<T> {
	
	private T value;
	
	public Pipeline(T initialValue) {
		this.value = initialValue;
	}
	
	public <R> Pipeline<R> add(Transformer<T, R> transformer) {
		R transformed = transformer.transform(this.value);
		return new Pipeline<>(transformed);
	}
	
	public T execute() {
		return this.value;
	}
}