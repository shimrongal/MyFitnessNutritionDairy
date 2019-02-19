package workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.core;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;

public class WorkoutViewModel extends ViewModel {

    private ApplicationRepository applicationRepository;

    WorkoutViewModel(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public LiveData<List<Workout>> getWorkouts() {
        return applicationRepository.loadWorkouts();
    }
}
