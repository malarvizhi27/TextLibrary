package com.test.booklibrary.service.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.test.booklibrary.domain.WordFrequency;
import com.test.booklibrary.exception.TextProcessorException;
import com.test.booklibrary.service.TextFrequencyAnalyzerService;

/**
 * This Class Contains Method For Text Processing
 *
 */
public class TextFrequencyAnalyzerServiceImpl implements TextFrequencyAnalyzerService {
	
	/**
	 * This Method returns Highest Frequency of word in the given text
	 * @param text
	 * @return - Highest Frequency Number
	 */
	@Override
	public int calculateHighestFrequency(String text) {

		if (StringUtils.isBlank(text)) {
			throw new TextProcessorException("Input Text can not be null or empty");
		}
		return Arrays.stream(text.split("[\\W]"))   // Split Non Word Characters
				.collect(Collectors.groupingBy(w -> w.toLowerCase(), HashMap::new, Collectors.counting())).values() //Convert to Lowercase and count number of words in a string
				.stream().max(Comparator.naturalOrder()).orElse(Long.valueOf(0)).intValue();  // Order it in ASC order and return Highest Value
	}
	/**
	 * This Method returns Frequency of specified word in the given text
	 * @param text and word
	 * @return - Frequency of word 
	 */
	@Override
	public int calculateWordFrequency(String text, String word) {
		if (StringUtils.isBlank(text)) {
			throw new TextProcessorException("Input Text can not be null or empty");
		}
		System.out.println(""+(Arrays.stream(text.split("[\\W]"))// Split on Nonword Characters
				.collect(Collectors.groupingBy(w -> w.toLowerCase(), HashMap::new, Collectors.counting())).entrySet())+"\r\n");
		return Arrays.stream(text.split("[\\W]"))// Split on Nonword Characters
				.collect(Collectors.groupingBy(w -> w.toLowerCase(), HashMap::new, Collectors.counting())).get(word).intValue();
				
			

	}
	
	/**
	 * This Method returns Most Frequent N words in the given Text
	 * @param text and N
	 * @return - List of Words with the Frequency
	 */

	@Override
	public List<WordFrequency> mostFrequentNWord(String text, int N) {

		if (StringUtils.isBlank(text)) {
			throw new TextProcessorException("Input Text can not be null or empty");
		}

		return Arrays.stream(text.split("[\\W]"))
				.filter(word -> !StringUtils.isBlank(word)) // Filter Spaces in a text
				.collect(Collectors.groupingBy(w -> w.toLowerCase(), HashMap::new, Collectors.counting())).entrySet() //Convert to Lower case and count number of words
				.stream().map(entry -> new WordFrequency(entry.getKey(), entry.getValue().intValue()))
				.sorted(Comparator.comparing(WordFrequency::getFrequency)// Sort on the basis of Freq Natural Sorting
						.reversed().thenComparing(WordFrequency::getWord)) // Sort the Text 
				.limit(N).collect(Collectors.toList()); // Limit the number of elements extracted in stream
		
	}

}
