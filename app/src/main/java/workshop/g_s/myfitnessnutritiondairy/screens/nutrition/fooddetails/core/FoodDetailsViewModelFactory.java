package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.core;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

public class FoodDetailsViewModelFactory implements ViewModelProvider.Factory {

    private final Gson gson;

    public FoodDetailsViewModelFactory(Gson gson) {
        this.gson = gson;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(FoodDetailsViewModel.class))
            return (T) new FoodDetailsViewModel(gson);
        throw new IllegalArgumentException();
    }
}
