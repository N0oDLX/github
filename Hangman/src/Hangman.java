import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Hangman {
	
	private String word;
	private int lives;
	private ArrayList<String> wordList;
	private ArrayList<String> guesses;
	private ArrayList<String> misses;
	private String[] dots;

	public Hangman() {
		wordList = getListFromFile("wordlist.txt");
		Collections.shuffle(wordList);
		word = wordList.get(0);
		System.out.println("The secret word is: " + word);
		
		dots = new String[word.length()];
		for ( int i=0; i<dots.length; i++ )
			dots[i] = "_";
		
		guesses = new ArrayList<String>();
		misses = new ArrayList<String>();
		lives = 6;
	}

	public ArrayList<String> getListFromFile( String filename ) {
		ArrayList<String> wordList = new ArrayList<String>();
		Scanner input = null;
		
		try {
			input = new Scanner(new java.io.File(filename));
		}
		catch (java.io.FileNotFoundException e) {
			System.out.println("Couldn't open '" + filename + "': " + e);
			System.exit(1);
		}
		while ( input.hasNext() ) {
			String word = input.next();
			wordList.add(word);
		}
		input.close();
		return wordList;
	}
	
	public void compareGuess( String guessWord ) {
		if ( guessWord.length() > 1 ) {
			if ( word.equals(guessWord) ) {
				for ( int i=0; i<word.length(); i++ ) {
					dots[i] = ""+word.charAt(i);
				}
			}
			else if ( !word.equals(guessWord) && !guesses.contains(guessWord) ) {
				System.out.println("\nYou have guessed the wrong word. Try again.");
				guesses.add(guessWord);
				misses.add(guessWord);
				lives--;
			}
			else {
				System.out.println("\nYou have already guessed that word. Try again.");
			}
		}
		if ( guessWord.length() == 1 ) {
			char guessLetter = guessWord.charAt(0);
			ArrayList<Integer> positions = new ArrayList<Integer>();
			
			if ( word.contains(""+guessLetter) && !guesses.contains(guessLetter) ) {
				for ( int i=0; i<word.length(); i++ ) {
					if ( guessLetter == word.charAt(i) ) {
						positions.add(i);
					}
				}
				for ( int p : positions ) {
					dots[p] = ""+guessLetter;
				}
				guesses.add(""+guessLetter);
			}
			else if ( !word.contains(""+guessLetter) && !guesses.contains(guessLetter) ) {
				System.out.println("\nYou have guessed the wrong letter. Try again.");
				guesses.add(""+guessLetter);
				misses.add(""+guessLetter);
				lives--;
			}
			else {
				System.out.println("\nYou have already guessed that letter. Try again.");
			}
		}
	}
	
	public boolean isOver() {
		return isWinner() || lives == 0;
	}
	
	public boolean isWinner() {
		if ( !Arrays.asList(dots).contains("_") ) {
			
			return true;
		}
		return false;
	}
	
	public String getWord() {
		return word;
	}
	
	public String toString() {
		String out = "";
		out += "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-\n";
		out += "\nword:   ";
		for ( String d : dots ) {
			out += d + " ";
		}
		out += "\nmisses: ";
		for ( String m : misses ) {
			out += m + " ";
		}
		if ( !isWinner() ) {
			out += "\nguess:  ";
		}
		return out;
	}
}
