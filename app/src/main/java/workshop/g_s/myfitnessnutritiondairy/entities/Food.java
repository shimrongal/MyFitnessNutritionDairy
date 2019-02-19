package workshop.g_s.myfitnessnutritiondairy.entities;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Food", primaryKeys = "id")
public class Food {

    @SerializedName("foodId")
    @Expose
    private long id;
    @SerializedName("foodName")
    @Expose
    private String foodName;
    @SerializedName("foodBrandName")
    @Expose
    private String foodBrandName;
    @Expose
    private float calories;
    @Expose
    private float proteins;
    @Expose
    private float totalFats;
    @SerializedName("fatSaturated")
    @Expose
    private float saturated;
    @SerializedName("fatTrans")
    @Expose
    private float trans;
    @SerializedName("fatPolyunsaturated")
    @Expose
    private float polyunsaturated;
    @SerializedName("fatMonounsaturated")
    @Expose
    private float monounsaturated;
    @SerializedName("carbohydrates")
    @Expose
    private float totalCarbohydrates;
    @Expose
    private float suger;
    @Expose
    private float diatryFiber;
    @Expose
    private float cholesterol;
    @Expose
    private String servingSize;
    @SerializedName("listMinerals")
    @Expose
    private String strListMinerals;
    @SerializedName("listVitamins")
    @Expose
    private String strListVitamins;

    public Food(long id, String foodName, String foodBrandName, float calories, float totalFats,
                float saturated, float trans, float polyunsaturated, float monounsaturated, float proteins,
                float totalCarbohydrates, float suger, float diatryFiber, float cholesterol, String
                        servingSize, String strListMinerals, String strListVitamins) {
        this.id = id;
        this.foodName = foodName;
        this.foodBrandName = foodBrandName;
        this.calories = calories;
        this.totalFats = totalFats;
        this.saturated = saturated;
        this.trans = trans;
        this.polyunsaturated = polyunsaturated;
        this.monounsaturated = monounsaturated;
        this.proteins = proteins;
        this.totalCarbohydrates = totalCarbohydrates;
        this.suger = suger;
        this.diatryFiber = diatryFiber;
        this.cholesterol = cholesterol;
        this.servingSize = servingSize;
        this.strListMinerals = strListMinerals;
        this.strListVitamins = strListVitamins;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodBrandName() {
        return foodBrandName;
    }

    public float getCalories() {
        return calories;
    }

    public float getTotalFats() {
        return totalFats;
    }

    public float getSaturated() {
        return saturated;
    }

    public float getTrans() {
        return trans;
    }

    public float getPolyunsaturated() {
        return polyunsaturated;
    }

    public float getMonounsaturated() {
        return monounsaturated;
    }

    public float getProteins() {
        return proteins;
    }

    public float getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public float getSuger() {
        return suger;
    }

    public float getDiatryFiber() {
        return diatryFiber;
    }

    public float getCholesterol() {
        return cholesterol;
    }

    public String getServingSize() {
        return servingSize;
    }

    public String getStrListMinerals() {
        return strListMinerals;
    }

    public String getStrListVitamins() {
        return strListVitamins;
    }
}
