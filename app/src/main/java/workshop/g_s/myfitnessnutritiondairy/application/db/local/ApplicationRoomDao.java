package workshop.g_s.myfitnessnutritiondairy.application.db.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;
import workshop.g_s.myfitnessnutritiondairy.entities.Mineral;
import workshop.g_s.myfitnessnutritiondairy.entities.Vitamin;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;

@Dao
public interface ApplicationRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWorkouts(List<Workout> workouts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFoodList(List<Food> foods);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVitaminsList(List<Vitamin> vitamins);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMineralesList(List<Mineral> minerals);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExerciseListPerWorkout(List<ExercisePerWorkout> exercisePerWorkoutList);


    @Query("SELECT * FROM Food")
    LiveData<List<Food>> getFoodList();

    @Query("SELECT * FROM Workout")
    LiveData<List<Workout>> getWorkoutList();

    @Query("SELECT * FROM ExercisePerWorkout WHERE workoutId = :currentWorkoutId")
    LiveData<List<ExercisePerWorkout>> loadExercisesPerWorkout(int currentWorkoutId);

/*
    @Query("SELECT * FROM Food WHERE id = :foodId")
    LiveData<Food> getFoodDetails(long foodId);

    @Query("SELECT * FROM Exercise")
    LiveData<List<Exercise>> getExerciseList();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateFoodList(List<Food> foods);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertFood(Food food);

        @Query("SELECT * FROM Vitamin WHERE foodId = :foodId ")
    LiveData<List<Vitamin>> getVitaminsList(long foodId);

    @Query("SELECT * FROM Mineral WHERE foodId = :foodId")
    LiveData<List<Mineral>> getMineralesList(long foodId);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertExerciseList(List<Exercise> exerciseList);
*/
}
