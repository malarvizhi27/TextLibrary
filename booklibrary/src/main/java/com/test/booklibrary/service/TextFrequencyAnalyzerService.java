package com.test.booklibrary.service;

import java.util.List;

import com.test.booklibrary.domain.WordFrequency;

public interface TextFrequencyAnalyzerService {

	public int calculateHighestFrequency(String text);

	public int calculateWordFrequency(String text, String word);

	public List<WordFrequency> mostFrequentNWord(String text, int N);

}
