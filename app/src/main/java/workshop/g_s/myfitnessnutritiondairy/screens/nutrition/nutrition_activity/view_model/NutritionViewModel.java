package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.view_model;

import android.arch.lifecycle.LiveData;

import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.entities.Food;

public interface NutritionViewModel {

    LiveData<List<Food>> initiateFoodsLocalRepositoryTable();
}
