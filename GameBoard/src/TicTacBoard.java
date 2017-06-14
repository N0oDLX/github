
public class TicTacBoard extends GameBoard {
	
	private int numTurns;
	
	public TicTacBoard() {
		super(3,3);
		numTurns = 0;
	}
	
	@Override
	public boolean canAdd( int r, int c ) {
		return super.canAdd(r,c) && g[r][c] == null;
	}
	
	@Override
	public void add( GamePiece a, int r, int c ) {
		if ( canAdd(r,c) ) {
			super.add(a, r, c);
			numTurns++;
		}
	}
	
	public String currentPlayer() {
		return numTurns%2 == 0 ? "X" : "O";
	}
	
	public boolean isWinner() {
		return isWinner("X") || isWinner("O");
	}
	
	public boolean isOver() {
		return isWinner() || numTurns == 9;
	}
	
	public boolean isWinner( String p ) {
		if ( winCheck(p, 0,0, 0,1, 0,2) ) return true;
		if ( winCheck(p, 1,0, 1,1, 1,2) ) return true;
		if ( winCheck(p, 2,0, 2,1, 2,2) ) return true;
		if ( winCheck(p, 0,0, 1,0, 2,0) ) return true;
		if ( winCheck(p, 0,1, 1,1, 2,1) ) return true;
		if ( winCheck(p, 0,2, 1,2, 2,2) ) return true;
		if ( winCheck(p, 0,0, 1,1, 2,2) ) return true;
		if ( winCheck(p, 2,0, 1,1, 0,2) ) return true;
		return false;
	}
	
	private boolean winCheck( String p, int a, int b, int c, int d, int e, int f ) {
		if ( g[a][b] == null || g[c][d] == null || g[e][f] == null )
			return false;
		String A = g[a][b].getSymbol();
		String B = g[c][d].getSymbol();
		String C = g[e][f].getSymbol();
		return ( p.equals(A) && A.equals(B) && B.equals(C) );
	}
	
	public TicTacPiece lastPiece(GameBoard board) {
		if ( canAdd(0,0) ) {
			TicTacPiece XO = new TicTacPiece(0,0);
			XO.addSelfToBoard(board);
			return XO;
		}
		else if ( canAdd(1,0) ) {
			TicTacPiece XO = new TicTacPiece(1,0);
			XO.addSelfToBoard(board);
			return XO;
		}
		else if ( canAdd(0,1) ) {
			TicTacPiece XO = new TicTacPiece(0,1);
			XO.addSelfToBoard(board);
			return XO;
		}
		else if ( canAdd(1,1) ) {
			TicTacPiece XO = new TicTacPiece(1,1);
			XO.addSelfToBoard(board);
			return XO;
		}	
		else if ( canAdd(2,0) ) {
			TicTacPiece XO = new TicTacPiece(2,0);
			XO.addSelfToBoard(board);
			return XO;
		}
		else if ( canAdd(0,2) ) {
			TicTacPiece XO = new TicTacPiece(0,2);
			XO.addSelfToBoard(board);
			return XO;
		}
		else if ( canAdd(2,2) ) {
			TicTacPiece XO = new TicTacPiece(2,2);
			XO.addSelfToBoard(board);
			return XO;
		}
		else if ( canAdd(2,1) ) {
			TicTacPiece XO = new TicTacPiece(2,1);
			XO.addSelfToBoard(board);
			return XO;
		}
		else {
			TicTacPiece XO = new TicTacPiece(1,2);
			XO.addSelfToBoard(board);
			return XO;
		}
	}
	
	public boolean lastTurn(GameBoard board) {
		if ( numTurns == 8 ) {
			String p = currentPlayer();
			TicTacPiece XO = lastPiece(board);
			if ( isWinner(p) == false )
				return true;
			else {
				XO.removeSelfFromBoard();
				numTurns--;
			return false;
			}
		}
		return false;
	}

}
