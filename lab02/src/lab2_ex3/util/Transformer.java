package lab2_ex3.util;

public interface Transformer<T, R> {
	R transform(T input);
}