package workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.WorkoutChosenCallback;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutsViewHolder> {
    private final Context context;
    private final WorkoutChosenCallback workoutChosenCallback;
    private ArrayList<Workout> workoutArrayList = new ArrayList<>();

    public WorkoutAdapter(Context context, WorkoutChosenCallback workoutChosenCallback) {
        this.context = context;
        this.workoutChosenCallback = workoutChosenCallback;
    }

    @NonNull
    @Override
    public WorkoutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflateWorkoutListRow = LayoutInflater.from(context).inflate(R.layout.layout_workout_list_row, parent, false);
        return new WorkoutsViewHolder(inflateWorkoutListRow, context);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsViewHolder holder, int position) {
        holder.bind(workoutChosenCallback, workoutArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return workoutArrayList.size();
    }

    public void updateWorkoutList(List<Workout> foodList) {
        workoutArrayList.clear();
        workoutArrayList.addAll(foodList);
        notifyDataSetChanged();
    }
}
