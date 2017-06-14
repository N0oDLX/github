
public class TicTacPiece extends GamePiece {
	
	private static int whichOne = 0;
	
	public TicTacPiece ( int r, int c ) {
		super( r,c, whichOne%2 == 0 ? "X" : "O" );
		if ( whichOne == 8 )
			whichOne = whichOne-2;
		else
			whichOne++;
	}

}
