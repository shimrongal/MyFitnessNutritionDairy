/*
package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.core;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;

public class FoodsViewModelFactory implements ViewModelProvider.Factory {

    private final ApplicationRepository applicationRepository;

    public FoodsViewModelFactory(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new FoodsViewModelImpl(applicationRepository);

    }
}
*/
