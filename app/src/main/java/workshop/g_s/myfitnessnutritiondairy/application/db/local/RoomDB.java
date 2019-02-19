package workshop.g_s.myfitnessnutritiondairy.application.db.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import workshop.g_s.myfitnessnutritiondairy.entities.Exercise;
import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;
import workshop.g_s.myfitnessnutritiondairy.entities.Mineral;
import workshop.g_s.myfitnessnutritiondairy.entities.Vitamin;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;

@Database(entities = {Food.class, Vitamin.class, Mineral.class, Workout.class, Exercise.class, ExercisePerWorkout.class}, version = 1, exportSchema = false)
abstract public class RoomDB extends RoomDatabase {
    public abstract ApplicationRoomDao applicationDao();
}
