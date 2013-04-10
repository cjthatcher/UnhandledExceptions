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

	public Position findStrongestPheromone(Colony col, GroundCell gc,
			boolean hasFood, Ant ant) {
		int orig_x = gc.getPosition().getX();
		int orig_y = gc.getPosition().getY();
		List<Pheromone> pherList = new ArrayList<Pheromone>();
		Map<Pheromone, Position> pherMap = new HashMap<Pheromone, Position>();

		for (int i = -1; i <= 1; ++i) {
			int temp_x = orig_x + i;
			if (temp_x < 0 || temp_x >= width)
				continue;
			else {
				for (int j = -1; j <= 1; ++j) {
					int temp_y = orig_y + j;
					if (temp_y < 0 || temp_y >= height) {
						continue;
					} else if (temp_x == orig_x && temp_y == orig_y) {
						continue;
					} else {
						Pheromone temp_p = checkForPheromone(temp_x, temp_y,
								col);
						if (temp_p != null) {
							pherList.add(temp_p);
							pherMap.put(temp_p, new Position(temp_x, temp_y));
						}
					}
				}
			}
		}

		// Make a comparator here.
		Collections.sort(pherList, new PheromoneComparator());
		
		if (pherList.size() >= 2) {
			if (hasFood) {
				Pheromone strongest = pherList.get(1);
				return pherMap.get(strongest);
			} else {
				Pheromone weakest = pherList.get(pherList.size() - 1);
				return pherMap.get(weakest);
			}
		} else if (pherList.size() == 1) {
			// If he doesnt' have food, follow it.

			// if he does have food --->

			// Make a list of all the places the ant has been since it has had
			// food
			// Make sure it doesn't step on those places again (avoid cycles)
			// If he's out of viable options, just send him straight home.
			// Mercy, right?

			// if pheromoneList.size() == 0,

			// if has food --> 80% towards home
			// if no food --> random as can be.

			if (hasFood) {
				// No pheromones? Take an 80% chance towards home
				Position temp = takeStepTowardsHome(gc.getPosition(), col);
				if (!ant.getVisited().contains(temp)) {
					return temp;
				}
				
				List<Position> tempPList = getNearby(gc.getPosition());
				for (Position p : tempPList) {
					if (!ant.getVisited().contains(p)) {
						return p;
					}
				}
			}
			
			//return pathToColony(ant);
		} else // doesn't have food
		{
			if (pherList != null && pherList.size() > 0) {
				Pheromone strongest = pherList.get(0);
				return pherMap.get(strongest);
			}
		}
		
		List<Position> temp = getNearby(gc.getPosition());
		return temp.get((int) (Math.random() * temp.size()));
	}

	private Position pathToColony(Ant ant) {
		Position colPos = ant.getCol().getNest().getGroundCell().getPosition();
		Position antPos = ant.getLocation().getPosition();
		Position temp = new Position(antPos.getX(), antPos.getY());

		if (colPos.getX() > antPos.getX()) {
			temp.setPosition(temp.getX() + 1, temp.getY());
		} else if (colPos.getX() < antPos.getX()) {
			temp.setPosition(temp.getX() - 1, temp.getY());
		}

		if (colPos.getY() > antPos.getY()) {
			temp.setPosition(temp.getX() + 1, temp.getY());
		} else if (colPos.getY() < antPos.getY()) {
			temp.setPosition(temp.getX() - 1, temp.getY());
		}

		return temp;
	}

	private Position takeStepTowardsHome(Position pos, Colony col) {

		// Take a percent chance towards home -->

		Position nestPosition = col.getPosition();
		int nestX = nestPosition.getX();
		int nestY = nestPosition.getY();

		double randomX = Math.random();
		double randomY = Math.random();

		int x = 0;
		int y = 0;

		if (randomX < config.getDegreeOfRandomness()) {
			if (pos.getX() > nestX)
				x = -1;
			else if (pos.getX() < nestX)
				x = 1;
			else
				x = 0;
		} else {
			if (Math.random() < 0.5)
				x = 0;
			else
				x = 1;
		}

		if (randomY < config.getDegreeOfRandomness()) {
			if (pos.getY() > nestY)
				x = -1;
			else if (pos.getY() < nestY)
				x = 1;
			else
				x = 0;
		} else {
			if (Math.random() < 0.5)
				y = 0;
			else
				y = 1;
		}

		int nextX = pos.getX() + x;
		int nextY = pos.getY() + y;
		
		if (nextX >= 40)
			nextX--;
		if (nextX < 0)
			nextX++;
		
		if (nextY >= 40)
			nextY--;
		if (nextY < 0)
			nextY++;
		
		Position temp = new Position(nextX, nextY);

		return temp;
	}

	private List<Position> getNearby(Position pos) {
		List<Position> temp = new ArrayList<Position>();
		for (int i = -1; i <= 1; ++i) {
			int temp_x = pos.getX() + i;
			if (temp_x < 0 || temp_x >= width)
				continue;
			else {
				for (int j = -1; j <= 1; ++j) {
					int temp_y = pos.getY() + j;
					if (temp_y < 0 || temp_y >= height) {
						continue;
					} else if (temp_x == pos.getX() && temp_y == pos.getY()) {
						continue;
					} else {
						temp.add(new Position(temp_x, temp_y));
					}
				}
			}
		}

		Collections.shuffle(temp);
		return temp;
	}

	private Pheromone checkForPheromone(int x, int y, Colony col) {
		return cellArray[x][y].getColonyPheromone(col);
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
							a.moveDirection();
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
