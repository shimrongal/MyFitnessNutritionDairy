package workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.core;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;

public class ExerciseViewModelFactory implements ViewModelProvider.Factory {

    private final ApplicationRepository applicationRepository;

    public ExerciseViewModelFactory(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ExerciseViewModelImpl.class))
            return (T) new ExerciseViewModelImpl(applicationRepository);
        throw new IllegalArgumentException("Wrong ViewModel class");

    }
}
