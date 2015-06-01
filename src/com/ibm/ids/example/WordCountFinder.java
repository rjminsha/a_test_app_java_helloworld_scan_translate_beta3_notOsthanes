/**
 * 
 */
package com.ibm.ids.example;

/**
 * @author pskhadke
 *
 */
public class WordCountFinder {
	public int countWords(String content) {
		return (content == null || content.trim().length() == 0) ? 0 : content.split("\\s+").length;			
	}
}
