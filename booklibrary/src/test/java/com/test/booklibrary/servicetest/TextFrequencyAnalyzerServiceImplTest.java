package com.test.booklibrary.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.test.booklibrary.domain.WordFrequency;
import com.test.booklibrary.exception.TextProcessorException;
import com.test.booklibrary.service.TextFrequencyAnalyzerService;
import com.test.booklibrary.service.impl.TextFrequencyAnalyzerServiceImpl;

public class TextFrequencyAnalyzerServiceImplTest {

	TextFrequencyAnalyzerService textFrequencyAnalyzerService = new TextFrequencyAnalyzerServiceImpl();

	@ParameterizedTest
	@MethodSource("provideStringsForCalculateHighestFrequency")
	public void givenWordNotNull_whenCalculateHighestFrequency_thenReturnFrequency(String input, int frequency) {
		assertEquals(frequency, textFrequencyAnalyzerService.calculateHighestFrequency(input));

	}

	@ParameterizedTest
	@MethodSource("provideNullStringForCalculateHighestFrequency")
	public void givenWordNull_whenCalculateHighestFrequency_thenThrowException(String input) {
		assertThrows(TextProcessorException.class, () -> textFrequencyAnalyzerService.calculateHighestFrequency(input));
	}
	
	@ParameterizedTest
	@MethodSource("provideNullStringForFindingFrequencyOfWord")
	public void givenWordNull_whenFindingFrequencyofWord_thenThrowException(String input, String word) {
		assertThrows(TextProcessorException.class, () -> textFrequencyAnalyzerService.calculateWordFrequency(input, word));
	}
	
	@ParameterizedTest
	@MethodSource("provideNullForComparingWordForFindingFrequencyOfWord")
	public void givenComapraingWordNull_whenFindingFrequencyofWord_thenThrowException(String input, String word) {
		assertThrows(TextProcessorException.class, () -> textFrequencyAnalyzerService.calculateWordFrequency(input, word));
	}
	
	//TO-DO Need to check this Testcase
	@ParameterizedTest
	@MethodSource("provideNotAvailableStringForFindingFrequencyOfWord")
	public void givenInputTextNotAvailable_whenFindingFrequencyOfWordthenReturnZero(String input, String word, int frequencyExpected) {
		assertEquals(frequencyExpected, textFrequencyAnalyzerService.calculateWordFrequency(input, word));
	}
	
	@ParameterizedTest
	@MethodSource("provideStringForCalculateWordFrequecny")
	public void givenInputTextAnd_thenReturnWordFrequency(String input, String word, int frequencyExpected) {
		assertEquals(frequencyExpected, textFrequencyAnalyzerService.calculateWordFrequency(input, word));
	}
	
	@ParameterizedTest(name = "{index} => input=''{0}'',N=''{1}''")
	@MethodSource("provideListForFindingNFrequentWord")
	@DisplayName("Should return List of N frequent Words")
	public void givenInputTextAndN_whenMostFrequentNWord_thenReturnList(String input, int N,
			List<WordFrequency> wordFrequenciesExpected) {
		assertEquals(wordFrequenciesExpected, textFrequencyAnalyzerService.mostFrequentNWord(input, N));
	}
	
	private static Stream<Arguments> provideStringsForCalculateHighestFrequency() {
		return Stream.of(Arguments.of("I love Java java programming", 2), Arguments.of("I love Java  programming", 1),
				Arguments.of("I love Java java Love java Programming", 3), Arguments.of("welcome to Java world", 1));
	}

	private static Stream<Arguments> provideNullStringForCalculateHighestFrequency() {
		return Stream.of(Arguments.of(" "), Arguments.of("   "), Arguments.of(""));
	}
	
	private static Stream<Arguments> provideNullStringForFindingFrequencyOfWord(){
		return Stream.of(Arguments.of(" ","hello"), Arguments.of("   ","java"), Arguments.of("","java"));
	}
	
	private static Stream<Arguments> provideNullForComparingWordForFindingFrequencyOfWord(){
		return Stream.of(Arguments.of("",""));
	}
	
	private static Stream<Arguments> provideNotAvailableStringForFindingFrequencyOfWord(){
		return Stream.of(Arguments.of("Hello Java","program",0), Arguments.of("welcome to the world ","java",0)
				);
	}
	private static Stream<Arguments> provideListForFindingNFrequentWord() {
		return Stream.of(Arguments.of("I love Java java programming", 1, Arrays.asList(new WordFrequency("java", 2))),
				Arguments.of("I love Java java Love java Programming programming programming", 3,
						Arrays.asList(new WordFrequency("java", 3), new WordFrequency("programming", 3),
								new WordFrequency("love", 2)))

		);
	}

	private static Stream<Arguments> provideStringForCalculateWordFrequecny() {
		return Stream.of(Arguments.of("I love Java java program", "java", 2),
				Arguments.of("I love Java java Love java Programming programming programming", "programming", 3));
	}

}
