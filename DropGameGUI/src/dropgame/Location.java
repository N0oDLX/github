package dropgame;

public class Location implements Comparable<Location> {
	
	public int row;
	public int col;
	
	public Location() {
		row = col = 0;
	}
	
	public Location( int r, int c ) {
		row = r;
		col = c;
	}
	
	public boolean equals( Object obj ) {
		Location other = (Location)obj;
		return this.row == other.row && this.col == other.col;
	}
	
	public int compareTo( Location other ) {
		if ( this.row == other.row )
			return this.col - other.col;
		return this.row - other.row;
	}
	
	public Location below() {
		return new Location(this.row+1, this.col);
	}
	
	public Location below2() {
		return new Location(this.row+2, this.col);
	}
	
	public Location below3() {
		return new Location(this.row+3, this.col);
	}
	
	public Location above() {
		return new Location(this.row-1, this.col);
	}
	
	public Location left() {
		return new Location(this.row, this.col-1);
	}
	
	public Location right() {
		return new Location(this.row, this.col+1);
	}
	
	public Location right2() {
		return new Location(this.row, this.col+2);
	}
	
	public Location right3() {
		return new Location(this.row, this.col+3);
	}
	
	public Location counter() {
		return new Location(this.row-1,this.col+1);
	}
	
	public Location counter2() {
		return new Location(this.row-2,this.col+2);
	}
	
	public Location counter3() {
		return new Location(this.row-3,this.col+3);
	}
	
	public Location main() {
		return new Location(this.row+1,this.col+1);
	}
	
	public Location main2() {
		return new Location(this.row+2,this.col+2);
	}
	
	public Location main3() {
		return new Location(this.row+3,this.col+3);
	}

	public String toString() {
		return "(" + row + ", " + col + ")";
	}
}
