package workshop.g_s.myfitnessnutritiondairy.application.db.remote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;

/**
 * Created by g-s on 18/03/2018.
 */

public interface ApplicationRestApi {

    @POST("getFoods.php")
    Observable<List<Food>> getFoods();

    @POST("getWorkoutList.php")
    Observable<List<Workout>> getWorkout();

    @FormUrlEncoded
    @POST("getExerciseListPerWorkout.php")
    Observable<List<ExercisePerWorkout>> getExerciseListPerWorkout(@Field("workoutId") int currentWorkoutId);

/*    @FormUrlEncoded
    @POST("getFoods.php")
    Observable<Food> getFoodDetails(@Field("foodId") long foodId);

    @POST("getExercisesPerWorkoutList.php")
    Observable<List<Exercise>> getExerciseList();*/
}

