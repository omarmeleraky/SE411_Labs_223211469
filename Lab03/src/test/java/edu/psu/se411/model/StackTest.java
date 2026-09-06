package edu.psu.se411.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class StackTest {

	@Test
	public void defaultConstructor_pushAndPop_shouldFollowLifoOrder() {
		Stack<String> stringStack = new Stack<>();
		stringStack.push("Z");
		stringStack.push("A");

		assertEquals("A", stringStack.pop());
		assertEquals("Z", stringStack.pop());
	}

	@Test
	public void pushNull_shouldAllowNullValues() {
		Stack<String> stringStack = new Stack<>();
		stringStack.push(null);

		assertNull(stringStack.pop());
	}

	@ParameterizedTest
	@ValueSource(ints = {0, -1})
	public void constructor_withNonPositiveCapacity_shouldStillBehaveNormally(int capacity) {
		Stack<Integer> intStack = new Stack<>(capacity);
		intStack.push(1);
		intStack.push(2);

		assertEquals(2, intStack.pop());
		assertEquals(1, intStack.pop());
	}

	@Test
	public void constructor_withPositiveCapacity_shouldBehaveNormally() {
		Stack<String> stack = new Stack<>(1);
		stack.push("first");
		stack.push("second");

		assertEquals("second", stack.pop());
		assertEquals("first", stack.pop());
	}

	@Test
	public void pop_emptyStack_shouldThrowExactExceptionAndMessage() {
		Stack<String> stringStack = new Stack<>();

		NoSuchElementException thrown = assertThrowsExactly(
			NoSuchElementException.class,
			stringStack::pop
		);

		assertEquals("Stack is empty, cannot pop", thrown.getMessage());
	}

	@Test
	public void pushAndPopAcrossMultipleCalls_shouldUpdateStateEachTime() {
		Stack<Integer> intStack = new Stack<>();
		intStack.push(10);
		intStack.push(20);
		assertEquals(20, intStack.pop());

		intStack.push(30);
		assertEquals(30, intStack.pop());
		assertEquals(10, intStack.pop());
	}
}