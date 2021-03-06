package workshop.g_s.myfitnessnutritiondairy.entities;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ExercisePerWorkout", primaryKeys = "exerciseId")
public class ExercisePerWorkout {

    @SerializedName("workoutId")
    @Expose
    private int workoutId;
    @SerializedName("id")
    @Expose
    private int exerciseId;
    @SerializedName("name")
    @Expose
    private String exerciseName;
    @SerializedName("description")
    @Expose
    private String exerciseDescription;
    @SerializedName("level")
    @Expose
    private int exerciseLevel;
    @SerializedName("instruction")
    @Expose
    private String exerciseInstruction;
    @SerializedName("sets_amount")
    @Expose
    private int setsAmount;
    @SerializedName("reps_amount")
    @Expose
    private int repsAmount;

    public ExercisePerWorkout(int workoutId, int exerciseId, String exerciseName, String exerciseDescription, int exerciseLevel, String exerciseInstruction, int setsAmount, int repsAmount) {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseLevel = exerciseLevel;
        this.exerciseInstruction = exerciseInstruction;
        this.setsAmount = setsAmount;
        this.repsAmount = repsAmount;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public int getExerciseLevel() {
        return exerciseLevel;
    }

    public String getExerciseInstruction() {
        return exerciseInstruction;
    }

    public int getSetsAmount() {
        return setsAmount;
    }

    public int getRepsAmount() {
        return repsAmount;
    }

}
