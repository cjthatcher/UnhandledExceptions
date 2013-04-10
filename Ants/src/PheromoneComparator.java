import java.util.Comparator;


public class PheromoneComparator implements Comparator{

	@Override
	public int compare(Object p1, Object p2) {
		
		int s1 = ((Pheromone)p1).getStrength();
		int s2 = ((Pheromone)p2).getStrength();
		
		if (s1 > s2)
			return 1;
		else if (s1 < s2)
			return -1;
	
		return 0;
	}

}
