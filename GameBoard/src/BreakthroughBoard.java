
public class BreakthroughBoard extends GameBoard {
	
	public BreakthroughBoard() {
		super(8,8);
		for ( int c=0; c<8; c++ ) {
			createPieceAt(0,c,false);
			createPieceAt(1,c,false);
			createPieceAt(6,c,true);
			createPieceAt(7,c,true);
		}
	}
	
	@Override
	public BreakthroughPiece get( int r, int c ) {
		return (BreakthroughPiece)super.get(r, c);
	}
	
	public void createPieceAt( int r, int c, boolean isWhite ) {
		BreakthroughPiece bp = new BreakthroughPiece(r,c,isWhite);
		bp.addSelfToBoard(this);
	}
	
	public void createTokenAt( int r, int c, String token ) {
		BreakthroughPiece bt = new BreakthroughPiece(r,c,token);
		add(bt,r,c);
	}
	
	public void showMoves( BreakthroughBoard board, String player, BreakthroughPiece pick ) {
		int r = pick.getRow();
		int c = pick.getCol();
		int rowDir = player.equals("W") ? -1 : +1;
		
		BreakthroughPiece X = get(r,c);
		BreakthroughPiece A = pick.canMove(-1) ? get(r+rowDir,c-1) : null;
		BreakthroughPiece B = get(r+rowDir,c);
		BreakthroughPiece C = pick.canMove(1) ? get(r+rowDir,c+1) : null;
		
		System.out.println("The coordinates of the piece are " + r + "," + c);
		
		if ( pick.canMove(-1) ) {
			System.out.println("Copy 'A' created");
			System.out.println("The piece can move forward/left");
			createTokenAt(r+rowDir,c-1,"#");
		}
		if ( pick.canMove(0) ) {
			System.out.println("Copy 'B' created");
			System.out.println("The piece van move forward/forward");
			createTokenAt(r+rowDir,c,"#");
		}
		if ( pick.canMove(1) ) {
			System.out.println("Copy 'C' created");
			System.out.println("The piece can move forward/right");
			createTokenAt(r+rowDir,c+1,"#");
		}
		
		if ( player.equals("W") )
			createTokenAt(r,c,"w");
		else
			createTokenAt(r,c,"b");
		
		System.out.println("\n" + board);
		
		remove(r,c);
		add(X,r,c);
			
		if ( pick.canMove(-1) ) {
			remove(r+rowDir,c-1);
			add(A,r+rowDir,c-1);
			System.out.println("Copy A restored.");
		}
		
		remove(r+rowDir,c);
		add(B,r+rowDir,c);
		System.out.println("Copy B restored.");
		
		if ( pick.canMove(1) ) {
			remove(r+rowDir,c+1);
			add(C,r+rowDir,c+1);
			System.out.println("Copy C restored.");
		}
	}
	
	public void movePiece( int curRow, int curCol, int newRow, int newCol ) {
		if ( canAdd(curRow, curCol) && canAdd(newRow, newCol) ) {
			g[newRow][newCol] = g[curRow][curCol];
			g[curRow][curCol] = null;
		}
	}
	
	public boolean isOver() {
		return isWinner("W") || isWinner("B");
	}
	
	public boolean isWinner( String symbol ) {
		int targetRow = symbol.equals("W") ? 0 : 7;
		for ( int c=0; c<8; c++ )
			if ( g[targetRow][c].getSymbol().equals(symbol) )
				return true;
		return false;
	}
	
	@Override
	public String toString() {
		String out = "";
		for ( int r=0; r<g.length; r++ ) {
			out += "\t" + (g.length-r) + " ";
			for ( int c=0; c<g[0].length; c++ ) {
				if ( g[r][c] == null )
					out += ".";
				else
					out += g[r][c].getSymbol();
			}
			out += "\n";
		}
		out += "\t  ";
		for ( int c=0; c<g[0].length; c++ )
			out += (char)('A'+c);
		return out+"\n";
	}

}
