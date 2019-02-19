package workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.WorkoutChosenCallback;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;
import workshop.g_s.myfitnessnutritiondairy.utils.ApplicationFunctions;

public class WorkoutsViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private final View itemView;
    @BindView(R.id.workoutName)
    TextView tvWorkoutName;
    @BindView(R.id.workoutDuration)
    TextView tvWorkoutDurationInMinutes;
    @BindView(R.id.ivWorkoutImage)
    ImageView ivWorkoutImage;
    private WorkoutChosenCallback workoutChosenCallback;
    private int currentWorkoutId;


    WorkoutsViewHolder(View itemView, Context context) {
        super(itemView);
        this.itemView = itemView;
        ButterKnife.bind(this, itemView);
        this.context = context;
    }


    public void bind(WorkoutChosenCallback workoutChosenCallback, Workout currentWorkout) {
        this.workoutChosenCallback = workoutChosenCallback;
        currentWorkoutId = currentWorkout.getId();
        String workoutName = currentWorkout.getName();
        tvWorkoutName.setText(workoutName);
        tvWorkoutDurationInMinutes.setText(context.getString((R.string.workout_duration), currentWorkout.getDurationInMinutes()));

        Glide.with(itemView)
                .load(getImageByStringName(workoutName.replace(" ", "_").toLowerCase()))
                .apply(ApplicationFunctions.getInstance().getRequestOptions(itemView))
                .into(ivWorkoutImage);
    }

    @OnClick(R.id.llWorkoutListRow)
    void onWorkoutClick() {
        workoutChosenCallback.getChosenWorkout(currentWorkoutId);
    }

    private int getImageByStringName(String strImageName) {
        return context.getResources().getIdentifier(strImageName, "drawable", context.getPackageName());
    }
}
