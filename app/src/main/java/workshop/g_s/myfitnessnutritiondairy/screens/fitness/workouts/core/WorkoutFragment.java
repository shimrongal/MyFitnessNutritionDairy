package workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.core;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.App;
import workshop.g_s.myfitnessnutritiondairy.application.callbacks.WorkoutChosenCallback;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.dagger.DaggerWorkoutComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.dagger.WorkoutsModule;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.list.WorkoutAdapter;
import workshop.g_s.myfitnessnutritiondairy.utils.Coordinator;

public class WorkoutFragment extends Fragment implements WorkoutChosenCallback {
    @BindView(R.id.rcWorkouts)
    RecyclerView recyclerViewWorkouts;
    @Inject
    WorkoutViewModelFactory workoutViewModelFactory;
    @Inject
    Coordinator coordinator;
    private Context context;
    private WorkoutAdapter workoutAdapter;
    private Unbinder bindButterKnife;
    private WorkoutViewModel workoutViewModel;


    public static WorkoutFragment getInstance() {
        return new WorkoutFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerWorkoutComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .workoutsModule(new WorkoutsModule())
                .build()
                .inject(this);
        workoutAdapter = new WorkoutAdapter(context, this);

        workoutViewModel = ViewModelProviders.of(this, workoutViewModelFactory).get(WorkoutViewModel.class);

        initWorkoutList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bindButterKnife.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateWorkoutsView = inflater.inflate(R.layout.layout_fragment_workout, container, false);
        bindButterKnife = ButterKnife.bind(this, inflateWorkoutsView);
        initRecyclerView();
        return inflateWorkoutsView;
    }

    private void initRecyclerView() {
        recyclerViewWorkouts.setLayoutManager(new LinearLayoutManager(context));
        recyclerViewWorkouts.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
        recyclerViewWorkouts.setAdapter(workoutAdapter);
    }

    private void initWorkoutList() {
        LiveData<List<Workout>> liveDataWorkouts = workoutViewModel.getWorkouts();
        liveDataWorkouts.observe(this, workouts -> workoutAdapter.updateWorkoutList(workouts));
    }

    @Override
    public void getChosenWorkout(int workoutId) {
        coordinator.goToExerciseListFragment(getActivity(), workoutId);
    }
}
