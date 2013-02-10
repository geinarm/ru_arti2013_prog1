
public class Position{
	public int x, y;
	
	Position()
	{
		this.x = 0;
		this.y = 0;
	}
	
	Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object pos) {
		if (pos==null) return false;
		Position p = (Position) pos;
		return (this.x == p.x && this.y == p.y);
	}
}