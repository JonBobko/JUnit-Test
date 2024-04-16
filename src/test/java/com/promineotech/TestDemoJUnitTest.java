package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {

	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")

	void assertThatTwoPositiveNumbersAreAddedCorrectly() {
		// Given: two numbers to test

		int a = 5;
		int b = 2;
		int expected = 7;
		boolean expectException = false;

		// When: the method to check if two numbers are positive and adding them is
		// called
		int actual = testDemo.addPositive(a, b);

		// Then: The numbers are added correctly
		if (!expectException) {

			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);

		} else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}

	}

	static Stream<Arguments> argumentsForAddPositive() {
		//@formatter:off
		return Stream.of(
			arguments(2, 4, 6, false),
			arguments(0, 1, 1, true),
			arguments(-1, 1, 0, true),
			arguments(-11, -1, -12, true),
			arguments(10, 10, 20, false)
			
				);
		//@formatter:on
	}

	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
		assertThat(testDemo.addPositive(50, 40)).isEqualTo(90);
		assertThat(testDemo.addPositive(10, 5)).isEqualTo(15);
		assertThat(testDemo.addPositive(25, 20)).isEqualTo(45);

	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddNegative")
	void assertThatTwoNegativeNumbersAreAddedCorrectly() {
		// Given: two number to test
		int a = -5;
		int b = -1;
		int expected = -6;
		boolean expectedException = false;

		// When: the method to check if two numbers are negative is called
		int actual = testDemo.addNegative(a, b);

		// Then: the numbers are added correctly

		if (!expectedException) {
			assertThat(testDemo.addNegative(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> testDemo.addNegative(a, b)).isInstanceOf(IllegalArgumentException.class);

		}
	}
	static Stream<Arguments> argumentsForAddNegative() {
		//@formatter:off
		return Stream.of(
			arguments(-1, -4, -5, false),
			arguments(0, -1, -1, true),
			arguments(-1, 1, 0, true),
			arguments(-11, -1, -12, false),
			arguments(10, 10, 20, true)
			
				);
		//@formatter:on
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		
		doReturn(5).when(mockDemo).getRandomInt();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);
	}

}
