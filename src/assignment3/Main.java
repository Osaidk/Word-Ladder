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
	
	// static variables and constants only here.
	static boolean DFS_found;
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
		parse(KeyBoard);
		printLadder(getWordLadderDFS("money", "stone"));
		
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	     DFS_found = false;
	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList <String> Input = new ArrayList<String>();
		System.out.println("Enter the Start and End Words:");
		String input = keyboard.next();
		Input.add(input);
		input = keyboard.next();
		Input.add(input);
		return Input;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		Set<String> dict = makeDictionary();
		ArrayList<String> Ladder = new ArrayList<String>();
		if (start == null || end == null || start.length() == 0  
				|| start.length() != end.length() || start.equals(end))  
			return null;
		else {
			Ladder.add(start);
			DFS_Helper(Ladder, dict, start, end, new HashSet<String>());
			if (DFS_found == false) {
				return null;	
			}	
			//Ladder.add(end);
			DFS_found = false;
		}
		
		return Ladder; // replace this line later with real return      // Returned list should be ordered start to end.  Include start and end.
	}                                                               // If ladder is empty, return list with just start and end.
	                                                                 	// TODO some code
		                                                                //Set<String> dict = makeDictionary();
		                                                                // TODO more code
			
	
	public static void DFS_Helper (ArrayList<String> ladder, Set<String> dict, String start, String end, HashSet<String> Visited) {
		Visited.add(start);
		
		for (int i=0 ; i < start.length() ; i++) {
		     StringBuilder Word_Mod = new StringBuilder(start); 
		     for (char character ='a'; character<='z'; ++character) {
		    	 if (character == start.charAt(i)) 
		    		 continue;
		    	 Word_Mod.setCharAt(i, character);
		    	 String New_Word = Word_Mod.toString();
		    	 if (Visited.contains(New_Word)) {
		    		 continue;
		    	 }
				//	System.out.println(New_Word);
		    	 if (New_Word.equals(end)) {
		    		 ladder.add(New_Word);
		    		 DFS_found = true;
		    		 return;
		    	 }
		    	 else if (dict.contains(New_Word.toUpperCase()) && !Visited.contains(New_Word) && !DFS_found) {
		    		 ladder.add(New_Word);
		    		 DFS_Helper(ladder, dict, New_Word, end, Visited);
		    	 }
		     }
		}
		//Visited.remove(start);
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		// TODO some code
	//	Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
    
	
	public static void printLadder(ArrayList<String> ladder) {
		if (ladder == null || ladder.size()==0) {
			System.out.println("No ladder");

		}
		else {
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
