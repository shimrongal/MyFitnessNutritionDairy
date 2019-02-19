package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;

public class NutritionViewModelImpl extends ViewModel implements NutritionViewModel {

    public static final String TAG = "NutritionViewModel";
    private final ApplicationRepository applicationRepository;


    NutritionViewModelImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public LiveData<List<Food>> initiateFoodsLocalRepositoryTable() {
        return applicationRepository.getFoods();
    }
}
