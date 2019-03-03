/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * 
 * Osaid Kadim
 * omk226
 * 16220
 * Aditya Khanna
 * ak34642
 * 16220
 * Slip days used: 0
 * Git URL: https://github.com/EE422C/project-3-wordladder-pair-57.git
 * Spring 2019
 */

package assignment3;

public class Node 
{	
	private String word;
	private Node previous;
	
	/**
	 * Constructor to make a Node object
	 * @param word - String of the word of the Node
	 */
	public Node(String word)
	{
		this.word = word;
		previous = null;
	}

	/**
	 * Get method for the word
	 * @return word - returns String of the word
	 */
	public String getWord() 
	{
		return word;
	}

	/**
	 * Get method for the previous node
	 * @return node - returns node object of the previous
	 */
	public Node getPrevious() 
	{
		return previous;
	}

	/**
	 * Sets previous Node as Node object passed in
	 * @param previous - previous node object
	 */
	public void setPrevious(Node previous) 
	{
		this.previous = previous;
	}
	
	
}
