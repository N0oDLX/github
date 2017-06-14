
public class DropGamePieceTester {
	
	public static void main( String[] args ) {
		DropGamePiece one = new DropGamePiece(5,5, "A");
		Location oneLoc = one.getLocation();
		DropGamePiece two = new DropGamePiece(oneLoc.below(), "B");
		System.out.println( one + "\n" + two );
		two.translate(-1,0);
		System.out.println( one + "\n" + two );
		System.out.println( one.compareTo(two) );
		
		DropGamePiece three = new DropGamePiece(5,5, "A");
		DropGamePiece four = new DropGamePiece(6,0, "A");
		DropGamePiece five = new DropGamePiece(5,5, "C");
		
		System.out.println( one.equals(three) );
		System.out.println( three.equals(four) );
		System.out.println( three.equals(five) );
	}

}
