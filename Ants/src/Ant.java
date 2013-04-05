
public class Ant {

	private Position pos;
	private Colony col;
	private Direction dir;
	
	public Ant(Colony col, Position pos, Direction dir){
		this.pos = pos;
		this.col = col;
		this.dir = dir;
	}
	
	public Position getPos() {
		return pos;
	}
	public void setPos(Position pos) {
		this.pos = pos;
	}
	public Colony getCol() {
		return col;
	}
	public void setCol(Colony col) {
		this.col = col;
	}
	public Direction getDir() {
		return dir;
	}
	public void setDir(Direction dir) {
		this.dir = dir;
	}
}

