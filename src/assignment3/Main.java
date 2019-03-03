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
		ArrayList<String> userInput = parse(keyboard);		// gets user inputs and puts them into arraylist
		while (userInput.size() != 0) 						// continually prints outputs and prompts user until /quit 
		{
			printLadder(getWordLadderDFS(userInput.get(0).toLowerCase(), userInput.get(1).toLowerCase()));
			System.out.println();
			printLadder(getWordLadderBFS(userInput.get(0).toLowerCase(), userInput.get(1).toLowerCase()));
			System.out.println();
			initialize();
			userInput = parse(keyboard);
		}
	}
	
	/**
	 * 
	 */
	public static void initialize() 
	{
		dictionary = makeDictionary();
		Visited = new HashSet<String>();
	    Ladder = new ArrayList<String>();
	    DFS_found = false;
	}
	
	/**Parses user inputs and places them into an ArrayList
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) 
	{
		ArrayList <String> tokens = new ArrayList<String>();  		// arraylist for words entered
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
	
	/**BFS search for shortest path between two words
	 * 
	 * @param start - String to start BFS with 
	 * @param end -  String to find with BFS
	 * @return ArrayList<String> ladder - Returns list of words connecting start and end Strings
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		if (start == null || end == null || start.length() == 0  			// check if DFS is valid to run
				|| start.length() != end.length() || start.equals(end))  
			return null;
		else {
			ArrayList<String> WordSet = new ArrayList<String>();
			WordSet.add(start);
		    Ladder.add(start);
			DFS_Helper(start, end, WordSet);
			if (Ladder.size()==1)
				Ladder.add(end);
			Visited.clear();
			DFS_found = false;
			ArrayList<String> ladder = new ArrayList<String>(Ladder);
			Ladder.clear();
			return ladder; 
		}                                                               // If ladder is empty, return list with just start and end.
	                                                                 	// TODO some code
	}	                                                                //Set<String> dict = makeDictionary();
		                                                                // TODO more code
	
	/**DFS helper function for path between two words
	 * 
	 * @param start - String to start BFS with 
	 * @param end -  String to find with BFS
	 * @param ArrayList<String> WordSet - Returns list of words connecting start and end Strings
	 */
	public static void DFS_Helper (String start, String end, ArrayList<String> WordSet) {
		if (DFS_found)													//checks base case of end is found
			return;
		for (int position=0; position < start.length(); position++) {	// cycles through every character in word
			if (start.charAt(position) == end.charAt(position))			// next character if character at position in start and end is the same
				continue;
		     StringBuilder Word_Mod = new StringBuilder(start); 
		     for (char character ='a'; character<='z'; character++) {	// cycles through every letter
		    	 if (character == start.charAt(position)) 				// next character if letter is the same as character in word at the position 
		    		 continue;
		    	 Word_Mod.setCharAt(position, character);
		    	 String New_Word = Word_Mod.toString();
		    	 if (Visited.contains(New_Word))						// checks if word was already visited
		    		 continue;
		    	 if (dictionary.contains(New_Word.toUpperCase())&&!Visited.contains(New_Word)&&!WordSet.contains(New_Word)) {
		    		 WordSet.add(New_Word);
		    		 Visited.add(New_Word);								// marked as visited
		    		 if (WordSet.get(WordSet.size()-1).equals(end)) {
		    			DFS_found = true;
		    			Ladder = new ArrayList<String>(WordSet);
		    			return;
		    		 }
		    		 DFS_Helper(New_Word, end, WordSet);				// recursive call for DFS search
		    		 WordSet.remove(WordSet.size()-1);
		    	 }
		     }
		}
	}
	
	/**BFS search for shortest path between two words
	 * 
	 * @param start - String to start BFS with 
	 * @param end -  String to find with BFS
	 * @return ArrayList<String> ladder - Returns list of words connecting start and end Strings
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) 
    {
    	Set<String> dict = makeDictionary();					// creates dictionary set
    	LinkedList<Node> queue = new LinkedList<Node>();		// queue for BFS
    	ArrayList<String> ladder = new ArrayList<String>();		// ladder of words to be returned
    	Node last = new Node(end);								// last node to backtrack for ladder
    	
    	Node first = new Node(start);							// starting word is put in queue
        queue.add(first);
        
        while(!(queue.isEmpty()))								// loops while queue has elements
        {
        	Node current = queue.poll();						// remove first node in queue
        	dict.remove(current.getWord().toUpperCase());		// remove word from dictionary (VISITED)
        	if (current.getWord().equals(end))					// base case - checks if end word is found
        	{
        		last.setPrevious(current.getPrevious());		// last node has previous node of current
        		break;											// exit BFS
        	}
        	
        	char[] wordArray = current.getWord().toCharArray();		// creates char array of current word
            
        	for(int i = 0; i < wordArray.length; i++)			// cycles through every character in word
            {
                for(char c = 'a'; c <= 'z'; c++)				// cycles through every letter
                {
                    char originalLetter = wordArray[i];			// stores original letter
                    wordArray[i] = c;							// changes letter to new letter
                    String adjacentWord = new String(wordArray);	// converts array to new adjacent word
                    
                    if(dict.contains(adjacentWord.toUpperCase()))	// checks if dictionary contains adjacent word
                    {
                        Node adjacent = new Node(adjacentWord);	// creates node for adjacent word
                    	adjacent.setPrevious(current);			// sets previous node to original word
                    	queue.add(adjacent);					// adds adjacent Node to queue for BFS
                    }
                    wordArray[i] = originalLetter;				// reverts array back to original
                }
            }
        }
        
        while (last.getPrevious() != null)		// goes through every connecting Node from start to end
        {
        	ladder.add(0, last.getWord());		// adds word to front of ladder
        	last = last.getPrevious();			// Node becomes previous Node
        }
        ladder.add(0, last.getWord());			// accounts for adding start word to ladder
        
        if (ladder.size() == 1)					// accounts for if there is NO LADDER possible	
        {
        	ladder.add(0, start);
        }
        
        return ladder;
	}
    
	/** Prints whether there is a ladder (and if there is, it prints the words in the ladder) 
	 * 
	 * @param ArrayList<String> ladder - list of words connecting start to end words
	 */
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
