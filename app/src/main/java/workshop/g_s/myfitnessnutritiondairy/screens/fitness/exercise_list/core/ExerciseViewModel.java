package workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.core;

import android.arch.lifecycle.LiveData;

import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;

public interface ExerciseViewModel {

    LiveData<List<ExercisePerWorkout>> getExercisesPerWorkoutList(int currentWorkoutId);
}
