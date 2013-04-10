
public class ConfigurationClass {
	int boardWidth;
	int boardHeight;
	int numberOfFoodPiles;
	int amountOfFoodPerPile;
	int numberOfColonies;
	float degreeOfRandomness;
	int antLifeSpan;
	int antBirthRate;
	int pheromoneStrength;
	int millisecondDelay;
	int startingAnts;
	
	public int getMillisecondDelay() {
		return millisecondDelay;
	}
	public void setMillisecondDelay(int millisecondDelay) {
		this.millisecondDelay = millisecondDelay;
	}
	public int getBoardWidth() {
		return boardWidth;
	}
	public void setBoardWidth(int boardWidth) {
		this.boardWidth = boardWidth;
	}
	public int getBoardHeight() {
		return boardHeight;
	}
	public void setBoardHeight(int boardHeight) {
		this.boardHeight = boardHeight;
	}
	public int getNumberOfFoodPiles() {
		return numberOfFoodPiles;
	}
	public void setNumberOfFoodPiles(int numberOfFoodPiles) {
		this.numberOfFoodPiles = numberOfFoodPiles;
	}
	public int getAmountOfFoodPerPile() {
		return amountOfFoodPerPile;
	}
	public void setAmountOfFoodPerPile(int amountOfFoodPerPile) {
		this.amountOfFoodPerPile = amountOfFoodPerPile;
	}
	public int getNumberOfColonies() {
		return numberOfColonies;
	}
	public void setNumberOfColonies(int numberOfColonies) {
		this.numberOfColonies = numberOfColonies;
	}
	public float getDegreeOfRandomness() {
		return degreeOfRandomness;
	}
	public void setDegreeOfRandomness(float degreeOfRandomness) {
		this.degreeOfRandomness = degreeOfRandomness;
	}
	public int getAntLifeSpan() {
		return antLifeSpan;
	}
	public void setAntLifeSpan(int antLifeSpan) {
		this.antLifeSpan = antLifeSpan;
	}
	public int getAntBirthRate() {
		return antBirthRate;
	}
	public void setAntBirthRate(int antBirthRate) {
		this.antBirthRate = antBirthRate;
	}
	public int getPheromoneStrength() {
		return pheromoneStrength;
	}
	public void setPheromoneStrength(int pheromoneStrength) {
		this.pheromoneStrength = pheromoneStrength;
	}
	
	public void setStartingAnts(int num)
	{
		startingAnts=num;
	}
	
	public int getStartingAnts()
	{
		return startingAnts;
	}

}
