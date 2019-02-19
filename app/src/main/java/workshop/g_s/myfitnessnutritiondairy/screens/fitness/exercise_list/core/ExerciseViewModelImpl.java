package workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.core;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;

public class ExerciseViewModelImpl extends ViewModel implements ExerciseViewModel {

    private final ApplicationRepository applicationRepository;

    ExerciseViewModelImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public LiveData<List<ExercisePerWorkout>> getExercisesPerWorkoutList(int currentWorkoutId) {
        return applicationRepository.loadExercisesPerWorkout(currentWorkoutId);
    }
}
