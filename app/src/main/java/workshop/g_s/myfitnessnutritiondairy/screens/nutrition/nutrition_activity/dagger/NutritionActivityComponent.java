package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.dagger;

import dagger.Component;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.ApplicationComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.NutritionActivity;

@NutritionActivityScope()
@Component(dependencies = {ApplicationComponent.class}, modules = {NutritionActivityModule.class})
public interface NutritionActivityComponent extends ApplicationComponent {

    void inject(NutritionActivity nutritionActivity);

}
