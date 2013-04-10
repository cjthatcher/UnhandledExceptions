
public class FoodPile {
    
    int foodAmount;
    
    public FoodPile(int amount)
    {
        this.foodAmount = amount;
    }
    
    public int getFoodAmount()
    {
        return foodAmount;
    }
    
    public void decrementFood()
    {
        if(foodAmount > 0)
        {
            foodAmount--;
        }
    }
    
    public void setFoodAmount(int amount)
    {
        foodAmount = amount;
    }
    
}