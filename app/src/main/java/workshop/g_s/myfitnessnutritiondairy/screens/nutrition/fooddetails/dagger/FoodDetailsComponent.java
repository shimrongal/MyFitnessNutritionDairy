package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.dagger;

import dagger.Component;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.ApplicationComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.core.FoodDetailsFragment;

@FoodDetailsScope
@Component(dependencies = {ApplicationComponent.class}, modules = {FoodDetailsModule.class})
public interface FoodDetailsComponent {

    void inject(FoodDetailsFragment foodDetailsFragment);
}
