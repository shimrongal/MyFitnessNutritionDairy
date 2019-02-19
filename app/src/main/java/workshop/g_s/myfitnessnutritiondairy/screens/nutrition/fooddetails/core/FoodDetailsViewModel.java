package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.core;

import android.arch.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.entities.Food;

class FoodDetailsViewModel extends ViewModel {

    private final Gson gson;

    FoodDetailsViewModel(Gson gson) {
        this.gson = gson;
    }

    String formatFloatToString(float d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }

    Food getCurrentFood(long foodId, String strFoodListAsJson) {
        Food currentFood = null;
        if (!strFoodListAsJson.isEmpty()) {
            List<Food> foodList = gson.fromJson(strFoodListAsJson, new TypeToken<List<Food>>() {
            }.getType());

            for (int index = 0; index < foodList.size(); index++) {
                Food food = foodList.get(index);
                if (food.getId() == foodId) {
                    currentFood = food;
                    break;
                }
            }
        }
        return currentFood;
    }
}