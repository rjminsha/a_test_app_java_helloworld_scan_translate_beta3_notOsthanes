package com.ibm.ids.example.tests;

/**
 * 
 */
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ibm.ids.example.WordCountFinder;

/**
 * @author pskhadke
 *
 */
public class WordCountFinderTest {
	
	private WordCountFinder wcFinder;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.wcFinder = new WordCountFinder();
	}

	@Test
	public void testCountWords_Null() {
		int count = this.wcFinder.countWords(null);
		assertTrue(count == 0);
	}
	
	@Test
	public void testCountWords_EmptyString() {
		int count = this.wcFinder.countWords("");
		assertTrue(count == 0);
	}
	
	@Test
	public void testCountWords_Success() {
		String text = "Alice was beginning to get very tired of sitting by her " + 
			"sister on the bank, and of having nothing to do: once or twice " +
			"she had peeped into the book her sister was reading, but it had " +
			"no pictures or conversations in it, 'and what is the use of a " +
			"book,' thought Alice 'without pictures or conversations?'";
		
		int count = this.wcFinder.countWords(text);
		assertTrue(count == 57);
	}
}
