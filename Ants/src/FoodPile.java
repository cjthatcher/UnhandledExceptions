
public class FoodPile {
    
    int foodAmount;
    
    public FoodPile(int amount)
    {
        foodAmount = amount;
    }
    
    public int getFoodAmount()
    {
        return foodAmount;
    }
    
    public void decrementFood()
    {
        foodAmount--;
    }
    
    public void setFoodAmount(int amount)
    {
        foodAmount = amount;
    }
    
}