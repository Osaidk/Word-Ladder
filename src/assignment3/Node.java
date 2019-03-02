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
