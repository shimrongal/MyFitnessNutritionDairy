package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.view_model;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;

public class NutritionViewModelFactory implements ViewModelProvider.Factory {

    private ApplicationRepository applicationRepository;

    public NutritionViewModelFactory(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NutritionViewModelImpl.class)) {
            return (T) new NutritionViewModelImpl(applicationRepository);

        }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }

}
