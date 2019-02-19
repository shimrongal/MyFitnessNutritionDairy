package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.dagger;

import dagger.Module;
import dagger.Provides;
import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.NutritionActivity;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.view_model.NutritionViewModelFactory;

@Module
public class NutritionActivityModule {

    private final NutritionActivity nutritionActivity;

    public NutritionActivityModule(NutritionActivity nutritionActivity) {
        this.nutritionActivity = nutritionActivity;

    }

    @NutritionActivityScope
    @Provides
    NutritionViewModelFactory provideNutritionViewModelFactory(ApplicationRepository applicationRepository) {
        return new NutritionViewModelFactory(applicationRepository);
    }
}
