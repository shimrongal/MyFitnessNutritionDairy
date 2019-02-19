package workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.core;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;

public class WorkoutViewModelFactory implements ViewModelProvider.Factory {

    private final ApplicationRepository applicationRepository;

    public WorkoutViewModelFactory(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WorkoutViewModel.class))
            return (T) new WorkoutViewModel(applicationRepository);
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
