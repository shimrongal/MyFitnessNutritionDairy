package workshop.g_s.myfitnessnutritiondairy.application.db;

import android.arch.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Observable;
import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;
import workshop.g_s.myfitnessnutritiondairy.entities.Mineral;
import workshop.g_s.myfitnessnutritiondairy.entities.Vitamin;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;
import workshop.g_s.myfitnessnutritiondairy.utils.rx.RxSchedulers;

public interface ApplicationRepository {

    LiveData<List<Food>> getFoods();

    LiveData<List<Workout>> loadWorkouts();

    RxSchedulers getRxSchedulers();

    LiveData<List<ExercisePerWorkout>> loadExercisesPerWorkout(int currentWorkoutId);


    interface Remote {
        Observable<List<Food>> provideFoodList();

        Observable<List<Workout>> provideWorkoutList();

        Observable<List<ExercisePerWorkout>> provideExerciseListPerWorkout(int currentWorkoutId);
    }

    interface Local {

        void insertFoodList(List<Food> foodList);

        void insertVitaminList(List<Vitamin> vitamins);

        void insertMineralesList(List<Mineral> minerales);

        void insertWorkouts(List<Workout> workouts);

        void insertExerciseListPerWorkout(List<ExercisePerWorkout> exercises);

        LiveData<List<Food>> loodFoodList();

        LiveData<List<ExercisePerWorkout>> loadExercisesPerWorkout(int currentWorkoutId);
    }
}
