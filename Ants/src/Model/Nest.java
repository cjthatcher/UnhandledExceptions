package Model;

public class Nest {
	Colony itsColony;
	GroundCell itsGroundCell;
	
	public Nest(Colony c, GroundCell gc)
	{
		this.itsGroundCell = gc;
		this.itsColony = c;
	}
	
	public GroundCell getGroundCell()
	{
		return itsGroundCell;
	}
	
	public Colony getColony()
	{
		return itsColony;
	}
}
