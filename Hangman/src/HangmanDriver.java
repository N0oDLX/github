import java.util.Scanner; 


public class HangmanDriver {

	public static void main(String[] args) {
	
		Scanner reader = new Scanner(System.in);
		
		String guess, answer;
		boolean play = true;
		
		System.out.println("Try to guess the word in 6 tries.");
		System.out.println();
		
		while ( play ) {
			Hangman game = new Hangman();
			
			do {
				System.out.print(game.toString());
				guess = reader.next();
				game.compareGuess(guess);
			} while (!game.isOver());
		
			if ( game.isWinner() ) {
				System.out.println(game.toString());
				System.out.println("\nYOU GOT IT!");
			}
			else
				System.out.println("\nYou didn't guess the word '" + game.getWord() + "'. You lose.");

			System.out.print("\nPlay \"again\" or \"quit\"? ");
			answer = reader.next();
			if ( answer.equals("again"))
				play = true;
			else if ( answer.equals("quit"))
				play = false;
				
		}
	}
}
