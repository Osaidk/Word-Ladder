/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Spring 2019
 */


package assignment3;
import java.util.*;
import java.io.*;


public class Main {
	
	static boolean DFS_found;
	public static Set<String> dict;
	public static ArrayList<String> Ladder;
	public static ArrayList<String> Visited;

	public static void main(String[] args) throws Exception {
		
		Scanner KeyBoard;	// input Scanner for commands
		PrintStream ps;	// output file, for student testing and grading only
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			KeyBoard = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			KeyBoard = new Scanner(System.in);// default input from Stdin
			ps = System.out;			// default output to Stdout
		}
		initialize();
		ArrayList<String> User_Input = parse(KeyBoard);
		if (User_Input.size() != 0) {
		printLadder(getWordLadderDFS(User_Input.get(0).toLowerCase(), User_Input.get(1).toLowerCase()));
		}
		
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
		dict = makeDictionary();
		Visited = new ArrayList<String>();
		Ladder = new ArrayList<String>();
	    DFS_found = false;
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner KeyBoard) {
		ArrayList <String> Tokens = new ArrayList<String>();  // Arraylist for words entered
		System.out.println("Enter the Start and End Words:");
		String Input = KeyBoard.next();    // stores first input
		if (Input.equals("/quit"))   // user prompt
			return Tokens;    // returns empty arraylist when command /quit is entered
		Tokens.add(Input);
		Tokens.add(KeyBoard.next());
		return Tokens;
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
		if (WordSet.get(WordSet.size() - 1).equals(end)) {
			DFS_found= true;
			Ladder = new ArrayList<String>(WordSet);
			return;
		}
		
		for (int i=0 ; i < start.length() ; i++) {
			if (start.charAt(i) == end.charAt(i))
				continue;
		     StringBuilder Word_Mod = new StringBuilder(start); 
		     for (char character ='a'; character<='z'; character++) {
		    	 if (character == start.charAt(i)) 
		    		 continue;
		    	 Word_Mod.setCharAt(i, character);
		    	 String New_Word = Word_Mod.toString();
		    	 if (Visited.contains(New_Word))
		    		 continue;
		    	 if (dict.contains(New_Word.toUpperCase())&&!Visited.contains(New_Word)&&!WordSet.contains(New_Word)) {
		    		 WordSet.add(New_Word);
		    		 Visited.add(New_Word);
		    		 DFS_Helper(New_Word, end, WordSet);
		    		 WordSet.remove(WordSet.size()-1);
		    	 }
		     }
		}
	}
	
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
	//	Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
    
	
	public static void printLadder(ArrayList<String> ladder) {
		if (ladder == null) {
			System.out.println("Input is invalid");
			return;
		}
		else if (ladder.size()==2) {
			System.out.println("no word ladder can be found between "+ladder.get(0)+ " and "+ladder.get(1)+".");
		}
		else {
			System.out.println("a " +(ladder.size()-2)+"-rung word ladder exists between smart and money.");
			for (int i=0; i < ladder.size() ; i++) {
			System.out.println(ladder.get(i));
			}
		}
	}
	// TODO
	// Other private static methods here


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
