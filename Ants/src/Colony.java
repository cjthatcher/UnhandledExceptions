import java.util.*;

public class Colony {
	List<Ant> ants = new ArrayList<Ant>();
	Nest nest;
	Position position;
	int idNumber;

	public List<Ant> getAnts() {
		return ants;
	}
	
	public void setAnts(ArrayList<Ant> ants) {
		this.ants = ants;
	}
	
	public Nest getNest() {
		return nest;
	}
	
	public void setNest(Nest nest) {
		this.nest = nest;
	}
	
	public void addAnt(GroundCell gc)
	{
		ants.add(new Ant(this, gc));
	}
	
	public Colony(int id, Position p)
	{
		this.idNumber = id;
		this.position = p;
	}
	
	public int getIdNumber()
	{
		return idNumber;
	}

}