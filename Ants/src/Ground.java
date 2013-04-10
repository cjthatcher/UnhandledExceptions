import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.swing.Timer;

public class Ground extends Observable {
	int width;
	int height;
	GroundCell[][] cellArray;
	static ConfigurationClass config;
	static Ground instance;
	Timer time;
	int delay = config.getMillisecondDelay(); // this is in milliseconds
	boolean hasObserver = false;

	public void setFirstObserver(DrawingPane dp) {
		hasObserver = true;
		this.addObserver(dp);
	}

	public boolean hasObserver() {
		return hasObserver;
	}

	public ConfigurationClass getConfig() {
		return config;
	}

	public static void setConfigurationClass(ConfigurationClass c) {
		config = c;
	}

	public static Ground getInstance() {
		if (instance != null)
			return instance;
		else if (instance == null) {
			instance = new Ground(config);
		}

		return instance;
	}

	// public static void CreateGround(ConfigurationClass config)
	// {
	// instance = new Ground(config);
	// }

	public GroundCell[][] getCellArray() {
		return cellArray;
	}

	private Ground(ConfigurationClass config) {
		this.width = config.getBoardWidth();
		this.height = config.getBoardHeight();

		cellArray = new GroundCell[height][width];

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cellArray[i][j] = new GroundCell(new Position(j, i));
			}
		}

		int numColonies = config.getNumberOfColonies();
		int numFood = config.getNumberOfFoodPiles();

		List<Position> colonyPositions = new ArrayList<Position>();
		List<Position> foodPositions = new ArrayList<Position>();

		while (colonyPositions.size() < numColonies) {
			int x = (int) (Math.random() * config.getBoardWidth());
			int y = (int) (Math.random() * config.getBoardHeight());

			Position position = new Position(x, y);

			boolean inList = false;

			for (int i = 0; i < colonyPositions.size(); ++i) {
				Position temp = colonyPositions.get(i);
				if (temp.x == position.x && temp.y == position.y) {
					inList = true;
				}
			}

			if (inList == false) {
				colonyPositions.add(position);
			}

		}

		while (foodPositions.size() < numFood) {
			int x = (int) (Math.random() * config.getBoardWidth());
			int y = (int) (Math.random() * config.getBoardHeight());

			Position position = new Position(x, y);

			if (!foodPositions.contains(position)) {
				foodPositions.add(position);
			}

		}

		for (Position p : foodPositions) {
			int x = p.getX();
			int y = p.getY();

			int foodPileSize = config.getAmountOfFoodPerPile();

			GroundCell gc = cellArray[y][x];

			FoodPile fp = new FoodPile(foodPileSize);

			gc.setFoodPile(fp);

			// cellArray[y][x].setFoodPile(new FoodPile(foodPileSize));
		}

		int counter = 0;

		for (Position p : colonyPositions) {
			int x = p.getX();
			int y = p.getY();

			Nest tempNest = new Nest(new Colony(counter++, new Position(x, y)),
					cellArray[y][x]);

			for (int i = 0; i < config.getStartingAnts(); ++i) {
				tempNest.getColony().addAnt(cellArray[y][x]);
			}

			cellArray[y][x].setNest(tempNest);
		}

		time = new Timer(delay, new timeListener());
		time.start();

	}

	public Position findStrongestPheromone(Ant ant)
	{
		List<Pheromone> pheromoneList=nearbyPheromone(ant);
		Collections.sort(pheromoneList, new PheromoneComparator());
		if(ant.isCarryingFood())
		{
			if(pheromoneList.size()>0)
			{
				for(int i=0;i<pheromoneList.size();++i)
				{
					if(!ant.getVisited().contains(pheromoneList.get(i).getLocation().getPosition()))
					{
						return pheromoneList.get(i).getLocation().getPosition();
					}
				}
				return pathToColony(ant);
			}
			else
			{
				List<Position> temp=getNearby(ant.getLocation().getPosition());
				if(Math.random()<=config.getDegreeOfRandomness())
				{
					return pathToColony(ant);
				}
				int tempInd=(int)(Math.random()*temp.size());
				return temp.get(tempInd);
			}
		}
		else
		{
			for(int i=0;i<pheromoneList.size();++i)
			{
				if(!ant.getVisited().contains(pheromoneList.get(i).getLocation().getPosition()))
				{
					return pheromoneList.get(i).getLocation().getPosition();
				}
			}
			List<Position> temp=getNearby(ant.getLocation().getPosition());
			int tempInd=(int)(Math.random()*temp.size());
			return temp.get(tempInd);
		}
	}
	
	private List<Pheromone> nearbyPheromone(Ant ant)
	{
		List<Pheromone> pheromoneList=new ArrayList<Pheromone>();
		Position antPos=ant.getLocation().getPosition();
		for(int y=-1;y<=1;++y)
		{
			for(int x=-1;x<=1;++x)
			{
				if((y==0&&x==0)||(antPos.getX()+x<0||antPos.getX()+x>=width)||(antPos.getY()+y<0||antPos.getY()+y>=height))
				{
					continue;
				}
				for(Pheromone pher:cellArray[antPos.getY()+y][antPos.getX()+x].getPheromone())
				{
					if(pher.getColony()==ant.getCol())
					{
						pheromoneList.add(pher);
					}
				}
			}
		}
		return pheromoneList;
	}
	
	private Position pathToColony(Ant ant) {
		Position colPos = ant.getCol().getPosition();
		Position antPos = ant.getLocation().getPosition();
		Position temp = new Position(antPos.getX(), antPos.getY());

		if (colPos.getX() > antPos.getX()) {
			temp.setPosition(temp.getX() + 1, temp.getY());
		} else if (colPos.getX() < antPos.getX()) {
			temp.setPosition(temp.getX() - 1, temp.getY());
		}

		if (colPos.getY() > antPos.getY()) {
			temp.setPosition(temp.getX(), temp.getY()+1);
		} else if (colPos.getY() < antPos.getY()) {
			temp.setPosition(temp.getX(), temp.getY()-1);
		}

		return temp;
	}

	private List<Position> getNearby(Position pos) 
	{
		List<Position> temp = new ArrayList<Position>();
		for(int y=-1;y<=1;++y)
		{
			for(int x=-1;x<=1;++x)
			{
				if((y==0&&x==0)||(pos.getX()+x<0||pos.getX()+x>=width)||(pos.getY()+y<0||pos.getY()+y>=height))
				{
					continue;
				}
				temp.add(new Position(pos.getX()+x, pos.getY()+y));
			}
		}
		return temp;
	}

	private class timeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					List<Pheromone> tempPList = new ArrayList<Pheromone>(
							cellArray[i][j].getPheromone());

					if (tempPList != null && tempPList.size() > 0) {
						for (Pheromone p : tempPList) {
							p.agePheromone();
						}
					}

					List<Ant> tempAntList = new ArrayList<Ant>(
							cellArray[i][j].getAnt());

					if (tempAntList != null && tempAntList.size() > 0) {
						for (Ant a : tempAntList) {
							if(!a.getMoved())
							{
								a.moveDirection();
								a.setMoved(true);
							}
						}
					}
				}
			}
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					
					List<Ant> tempAntList = new ArrayList<Ant>(
							cellArray[i][j].getAnt());

					if (tempAntList != null && tempAntList.size() > 0) {
						for (Ant a : tempAntList) {
							a.setMoved(false);
						}
					}
				}
			}

			System.out.println("Timer hit!");

			setChanged();
			notifyObservers();
		}

	}
}
