package workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.exercise_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;
import workshop.g_s.myfitnessnutritiondairy.utils.ApplicationFunctions;

public class ExerciseViewHolder extends RecyclerView.ViewHolder {

    private final View itemView;
    private final Context context;
    @BindView(R.id.tvExerciseName)
    TextView tvExerciseName;
    @BindView(R.id.tvExerciseLevel)
    TextView tvExerciseLevel;
    @BindView(R.id.tvExerciseSetsAmount)
    TextView tvExerciseSetsAmount;
    @BindView(R.id.tvExerciseRepsAmount)
    TextView tvExerciseRepsAmount;
    @BindView(R.id.ivExerciseImage)
    ImageView ivExerciseImage;

    ExerciseViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        ButterKnife.bind(this, this.itemView);
    }

    public void bind(ExercisePerWorkout exercise) {
        String strExerciseName = exercise.getExerciseName();
        String strImageName = strExerciseName.toLowerCase().replace(" ", "_");

        tvExerciseName.setText(strExerciseName);
        tvExerciseLevel.setText(context.getString((R.string.exercise_level), exercise.getExerciseLevel()));
        tvExerciseRepsAmount.setText(context.getString((R.string.exercise_sets), exercise.getRepsAmount()));
        tvExerciseSetsAmount.setText(context.getString((R.string.exercise_reps), exercise.getSetsAmount()));
        Glide.with(itemView)
                .load(getImageByStringName(strImageName))
                .apply(ApplicationFunctions.getInstance().getRequestOptions(itemView))
                .into(ivExerciseImage);
    }

    private int getImageByStringName(String strImageName) {
        return context.getResources().getIdentifier(strImageName, "drawable", context.getPackageName());
    }
}
