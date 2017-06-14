
public class BreakthroughPiece extends GamePiece {
	
	private int rowDir;
	
	public BreakthroughPiece( int r, int c, boolean isWhite ) {
		super(r, c, isWhite ? "W" : "B");
		if ( isWhite )
			this.rowDir = -1;
		else
			this.rowDir = +1;
	}
	
	public BreakthroughPiece( int r, int c, String token ) {
		super(r, c, token );
	}
	
	public boolean canMove( int colDir ) {
		int newRow = r+rowDir, newCol = c+colDir;
		if ( ! board.canAdd(newRow, newCol) )
			return false;
		if ( board.get(newRow, newCol) == null )
			return true;
		BreakthroughBoard bb = (BreakthroughBoard)board;
		if ( colDir != 0 && bb.get(newRow, newCol).rowDir != this.rowDir )
			return true;
		
		return false;
	}
	
	public void move( int colDir ) {
		if ( ! canMove(colDir) )
			return;
		((BreakthroughBoard)board).movePiece(r, c, r+rowDir, c+colDir);
		r += rowDir;
		c += colDir;
	}

}
