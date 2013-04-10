import java.util.Comparator;


public class PheromoneComparator implements Comparator<Pheromone>{

	@Override
	public int compare(Pheromone o1, Pheromone o2) {
		int s1 = o1.getStrength();
		int s2 = o2.getStrength();
		
		if (s1 > s2)
			return 1;
		else if (s1 < s2)
			return -1;
		
		return 0;
	}

}
