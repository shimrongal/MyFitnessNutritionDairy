package workshop.g_s.myfitnessnutritiondairy.entities;

public class FoodListEntity {

    private long foodId;
    private String foodName;
    private String foodBrandName;

    public FoodListEntity(long foodId, String foodName, String foodBrandName) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodBrandName = foodBrandName;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodBrandName() {
        return foodBrandName;
    }
}
