package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.dagger;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.core.FoodDetailsViewModelFactory;

@Module
class FoodDetailsModule {

    @FoodDetailsScope
    @Provides
    FoodDetailsViewModelFactory provideFoodDetailsViewModelFactory(Gson gson) {
        return new FoodDetailsViewModelFactory(gson);
    }


}
