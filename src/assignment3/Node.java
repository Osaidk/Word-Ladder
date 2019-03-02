/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Osaid Kadim
 * omk226
 * 16220
 * Aditya Khanna
 * ak34642
 * 16220
 * Slip days used: <0>
 * Git URL: https://github.com/EE422C/project-3-wordladder-pair-57.git
 * Spring 2019
 */

package assignment3;

public class Node 
{	
	private String word;
	private Node previous;
	
	public Node(String word)
	{
		this.word = word;
		previous = null;
	}

	public String getWord() 
	{
		return word;
	}

	public Node getPrevious() 
	{
		return previous;
	}

	public void setPrevious(Node previous) 
	{
		this.previous = previous;
	}
	
	
}
