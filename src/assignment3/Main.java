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
import java.util.*;
import java.io.*;


public class Main 
{
	
	private static boolean DFS_found;
	private static Set<String> dictionary;
	private static ArrayList<String> Ladder;
	private static HashSet<String> Visited;

	public static void main(String[] args) throws Exception 
	{
		
		Scanner keyboard;							// input Scanner for commands
		PrintStream ps;								// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) 
		{
			keyboard = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);						// redirect output to ps
		} 
		else 
		{
			keyboard = new Scanner(System.in);		// default input from Stdin
			ps = System.out;						// default output to Stdout
		}
		initialize();
		ArrayList<String> userInput = parse(keyboard);		// gets user inputs anf puts them into arraylist
		while (userInput.size() != 0) 						// continually prints outputs and prompts user until /quit 
		{
			printLadder(getWordLadderDFS(userInput.get(0).toLowerCase(), userInput.get(1).toLowerCase()));
			System.out.println();
			printLadder(getWordLadderBFS(userInput.get(0).toLowerCase(), userInput.get(1).toLowerCase()));
			initialize();
			userInput = parse(keyboard);
		}
	}
	
	public static void initialize() 
	{
		dictionary = makeDictionary();
		Visited = new HashSet<String>();
	    Ladder = new ArrayList<String>();
	    DFS_found = false;
	    //BFS_found = false;
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) 
	{
		ArrayList <String> tokens = new ArrayList<String>();  		// Arraylist for words entered
		System.out.println("Enter the Start and End Words:");
		String Input = keyboard.next();    							// stores first input
		if (Input.equals("/quit"))   								// user prompt
		{
			return tokens;    										// returns empty arraylist when command /quit is entered
		}
		tokens.add(Input);
		tokens.add(keyboard.next());
		return tokens;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		if (start == null || end == null || start.length() == 0  
				|| start.length() != end.length() || start.equals(end))  
			return null;
		else {
			ArrayList<String> WordSet = new ArrayList<String>();
			WordSet.add(start);
		    Ladder.add(start);
			DFS_Helper(start, end, WordSet);
			if (Ladder.size()==1)
				Ladder.add(end);
			return Ladder; 
		}                                                               // If ladder is empty, return list with just start and end.
	                                                                 	// TODO some code
	}	                                                                //Set<String> dict = makeDictionary();
		                                                                // TODO more code
			
	
	public static void DFS_Helper (String start, String end, ArrayList<String> WordSet) {
		if (DFS_found)
			return;
		for (int position=0; position < start.length(); position++) {
			if (start.charAt(position) == end.charAt(position))
				continue;
		     StringBuilder Word_Mod = new StringBuilder(start); 
		     for (char character ='a'; character<='z'; character++) {
		    	 if (character == start.charAt(position)) 
		    		 continue;
		    	 Word_Mod.setCharAt(position, character);
		    	 String New_Word = Word_Mod.toString();
		    	 if (Visited.contains(New_Word))
		    		 continue;
		    	 if (dictionary.contains(New_Word.toUpperCase())&&!Visited.contains(New_Word)&&!WordSet.contains(New_Word)) {
		    		 WordSet.add(New_Word);
		    		 Visited.add(New_Word);
		    		 if (WordSet.get(WordSet.size()-1).equals(end)) {
		    			DFS_found = true;
		    			Ladder = new ArrayList<String>(WordSet);
		    			return;
		    		 }
		    		 DFS_Helper(New_Word, end, WordSet);
		    		 WordSet.remove(WordSet.size()-1);
		    	 }
		     }
		}
	}
	
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) 
    {
    	Set<String> dict = makeDictionary();
    	LinkedList<Node> queue = new LinkedList<Node>();
    	ArrayList<String> ladder = new ArrayList<String>();
    	Node last = new Node(end);
    	
    	Node first = new Node(start);
        queue.add(first);
        
        while(!(queue.isEmpty()))
        {
        	Node current = queue.poll();
        	dict.remove(current.getWord());
        	
        	if (current.getWord().equals(end))
        	{
        		last.setPrevious(current.getPrevious());
        		break;
        	}
        	
        	for (String next : dict)	
        	{
        		if (differsByOne(current.getWord(), next))
        		{
        			Node adjacent = new Node(next);
        			adjacent.setPrevious(current);
        			queue.add(adjacent);
        		}
        	}
        }
        
        while (last.getPrevious() != null)
        {
        	ladder.add(0, last.getWord());
        	last = last.getPrevious();
        }
        
        return ladder;
	}
    
	
	public static void printLadder(ArrayList<String> ladder) 
	{
		if (ladder == null) 
		{
			System.out.println("Input is invalid");
			return;
		}
		else if (ladder.size() == 2) 
		{
			System.out.println("no word ladder can be found between " + ladder.get(0) + " and " + ladder.get(1) + ".");
		}
		else if (ladder.size() >= 2)
		{
			System.out.println("a " + (ladder.size() - 2) + "-rung word ladder exists between " + ladder.get(0) + " and " + ladder.get(ladder.size() - 1) + ".");
			for (int i=0; i < ladder.size() ; i++) 
			{
				System.out.println(ladder.get(i));
			}
		}
	}
	
	// TODO
	// Other private static methods here
	/**
	 * A method to check if the words are adjacent 
	 * @param first
	 * @param second
	 * @return
	 */
	private static boolean differsByOne(String first, String second)
	{
		int count = 0;									//number of letter differences
		
		if (first.equals(second))						//checks if the strings are the same
		{
			return false;
		}
		for (int i = 0; i < first.length(); i++)		//cycles through every character
		{
			if (first.charAt(i) != second.charAt(i))
			{
				count++;
				/*if (count > 1)							//checks if word is not ADJACENT
				{
					return false;
				}*/
			}
			if (count > 1)
			{
				return false;
			}
		}
		return true;
		
	}

	/* Do not modify makeDictionary */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
}
