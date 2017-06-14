import java.util.Scanner;
import java.util.Random;

public class NimC {
	
	public static String Player1, Player2 = "NimBot", CPile, AStar, BStar, CStar;
	public static int PileA = 3, PileB = 4, PileC = 5, PileM, PileAx, PileBx, PileCx, 
			CCounter, turn = 0, A, B, C, Ax, Bx, Cx, counter, autoPile;
	public static Random gen = new Random();
	
	public static void main( String[] args ) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Player 1, enter your name: ");
		Player1 = keyboard.next();
		System.out.println(Player1 + ", you will play against " + Player2 + ". Good luck!");
		showPile();
		
		do {
			
			if ( turn == 0 ) {
				
				System.out.print("\n" + Player1 + ", choose a pile: ");
				CPile = keyboard.next();
				while ( emptyPile() == true ) {
					System.out.print("\nNice try, " + Player1 + ". That pile is empty. Choose again: ");
					CPile = keyboard.next();
				}
				
				System.out.print("How many to remove from pile " + CPile + ": ");
				CCounter = keyboard.nextInt();
				while ( tooLow() == true || tooHigh() == true ) {
					if ( tooLow() == true ) {
						System.out.print("\nYou must choose at least 1. How many? " );
						CCounter = keyboard.nextInt();
					}
					else {
						System.out.print("\nPile " + CPile + " doesn't have that many. Try again: ");
						CCounter = keyboard.nextInt();
					}
				}
				
				alterPile();
				showPile();
				turn++;
				
				if ( earlyWin() == true )
					break;
			}
			else {
				
				pickPile();
				pickStones();
	 			System.out.println("\n" + Player2 + " has decided to remove " + CCounter + " stone(s) from pile " + CPile);
	 			
				alterPile();
				showPile();
				turn--;
				
				if ( earlyWin() == true )
					break;
			}
			
		} while ( PileA > 0 || PileB > 0 || PileC > 0 );
		
		if ( turn == 0 )
			System.out.println(showWinner(Player2));
		else
			System.out.println(showWinner(Player1));
		
	}
	
	public static void maxPile() {
		if ( PileA > PileB && PileA > PileC )
			PileM = PileA;
		else if ( PileB > PileA && PileB > PileC )
			PileM = PileB;
		else
			PileM = PileC;
	}
	
	public static void showPile() {
		Ax = 0;
		PileAx = 0;
		Bx = 0;
		PileBx = 0;
		Cx = 0;
		PileCx = 0;
		counter = 0;
		
		maxPile();
		
		if ( PileA < PileM )
			PileAx = PileM - PileA;
		if ( PileB < PileM )
			PileBx = PileM - PileB;
		if ( PileC < PileM )
			PileCx = PileM - PileC;
		
		System.out.println();
		while ( counter != PileM ) {
			if ( PileAx != Ax ) {
				System.out.print("   ");
				Ax++;
			}
			else {
				System.out.print(" * ");
			}
			if ( PileBx != Bx ) {
				System.out.print("   ");
				Bx++;
			}
			else {
				System.out.print(" * ");
			}
			if ( PileCx != Cx ) {
				System.out.print("   ");
				Cx++;
			}
			else {
				System.out.print(" * ");
			}
			System.out.print("\n");
			counter++;
		}
		System.out.println(" A " + " B " + " C ");
	}
	
	public static String pickPile() {
		if ( winningMove() == false) {
			do {
				autoPile = gen.nextInt(3)+1;
				if ( autoPile == 1 )
					CPile = "A";
				else if ( autoPile == 2 )
					CPile = "B";
				else
					CPile = "C";
			} while ( emptyPile() == true );
		}
		return CPile;
	}
	
	public static int pickStones() {
		if ( winningMove() == false ) {
			do {
				CCounter = gen.nextInt(5)+1;
			} while ( tooHigh() == true );
		}
		return CCounter;
	}
	
	public static boolean winningMove() {
		if ( PileA > 1 && PileB == 1 && PileC == 1 ) {
			CPile = "A";
			CCounter = PileA - 1;
			return true;
		}
		if ( PileA > 1 && PileB == 0 && PileC == 1 ) {
			CPile = "A";
			CCounter = PileA;
			return true;
		}
		if ( PileA > 1 && PileB == 1 && PileC == 0 ) {
			CPile = "A";
			CCounter = PileA;
			return true;
		}
		if ( PileA == 1 && PileB > 1 && PileC == 1 ) {
			CPile = "B";
			CCounter = PileB - 1;
			return true;
		}
		if ( PileA == 1 && PileB > 1 && PileC == 0 ) {
			CPile = "B";
			CCounter = PileB;
			return true;
		}
		if ( PileA == 0 && PileB > 1 && PileC == 1 ) {
			CPile = "B";
			CCounter = PileB;
			return true;
		}
		if ( PileA == 1 && PileB == 1 && PileC > 1 ) {
			CPile = "C";
			CCounter = PileC - 1;
			return true;
		}
		if ( PileA == 0 && PileB == 1 && PileC > 1 ) {
			CPile = "C";
			CCounter = PileC;
			return true;
		}
		if ( PileA == 1 && PileB == 0 && PileC > 1 ) {
			CPile = "C";
			CCounter = PileC;
			return true;
		}
		return false;
	}
	
	public static String showWinner( String Player ) {
		if ( earlyWin() == true && Player.equals(Player1) )
			return "\n" + Player2 + ", you must take the last remaining counter, so you lose. " + Player1 + " wins!";
		else if ( earlyWin() == true && Player.equals(Player2) )
			return "\n" + Player1 + ", you must take the last remaining counter, so you lose. " + Player2 + " wins!";
		else
			return "\n" + Player + ", there are no counters left, so you WIN!";
	}
	
	public static boolean emptyPile() {
		if ( CPile.equals("A") && PileA == 0 )
			return true;
		else if ( CPile.equals("B") && PileB == 0 )
			return true;
		else if ( CPile.equals("C") && PileC == 0 )
			return true;
		else
			return false;
	}
	
	public static boolean tooLow() {
		if ( CCounter <= 0 )
			return true;
		else
			return false;
	}
	
	public static boolean tooHigh() {
		if ( CPile.equals("A") && CCounter > PileA )
			return true;
		else if ( CPile.equals("B") && CCounter > PileB )
			return true;
		else if ( CPile.equals("C") && CCounter > PileC )
			return true;
		else
			return false;
	}
	
	public static void alterPile() {
		if ( CPile.equals("A") )
			PileA = PileA - CCounter;
		else if ( CPile.equals("B") )
			PileB = PileB - CCounter;
		else
			PileC = PileC - CCounter;
	}
	
	public static boolean earlyWin() {
		if ( PileA == 1 && PileB == 0 && PileC == 0 )
			return true;
		else if ( PileA == 0 && PileB == 1 && PileC == 0 )
			return true;
		else if ( PileA == 0 && PileB == 0 && PileC == 1 )
			return true;
		else
			return false;
	}
}
