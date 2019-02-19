package workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.exercise_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseViewHolder> {
    private final Context context;
    private ArrayList<ExercisePerWorkout> exerciseArrayList = new ArrayList<>();

    public ExerciseAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflateExerciseListRow = LayoutInflater.from(context).inflate(R.layout.layout_exercise_list_row, parent, false);
        return new ExerciseViewHolder(inflateExerciseListRow, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        holder.bind(exerciseArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public void updateExerciseList(List<ExercisePerWorkout> exercises) {
        exerciseArrayList.clear();
        exerciseArrayList.addAll(exercises);
        notifyDataSetChanged();
    }
}
