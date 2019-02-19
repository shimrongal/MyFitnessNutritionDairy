package workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.core;

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
import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.dagger.DaggerExerciseComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.dagger.ExerciseModule;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.exercise_list.ExerciseAdapter;
import workshop.g_s.myfitnessnutritiondairy.utils.Constants;

public class ExerciseFragment extends Fragment {

    @BindView(R.id.rcExercise)
    RecyclerView rcExercise;
    @Inject
    ExerciseViewModelFactory exerciseViewModelFactory;
    private Context context;
    private ExerciseAdapter exerciseAdapter;
    private Unbinder bindButterKnife;
    private ExerciseViewModel exerciseViewModel;

    public static ExerciseFragment getInstance(int id) {
        ExerciseFragment exerciseFragment = new ExerciseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.WORKOUT_ID, id);
        exerciseFragment.setArguments(bundle);
        return exerciseFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDagger();
        Bundle arguments = getArguments();
        if (getArguments() != null) {
            int currentWorkoutId = arguments.getInt(Constants.WORKOUT_ID);
            exerciseAdapter = new ExerciseAdapter(context);
            exerciseViewModel = ViewModelProviders.of(this, exerciseViewModelFactory).get(ExerciseViewModelImpl.class);
            initExerciseList(currentWorkoutId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateExerciseFragment = inflater.inflate(R.layout.layout_exercise_fragment, container, false);
        bindButterKnife = ButterKnife.bind(this, inflateExerciseFragment);
        initRecyclerView();
        return inflateExerciseFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bindButterKnife.unbind();
    }

    private void initRecyclerView() {
        rcExercise.setLayoutManager(new LinearLayoutManager(context));
        rcExercise.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL));
        rcExercise.setAdapter(exerciseAdapter);
    }

    private void initDagger() {
        DaggerExerciseComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .exerciseModule(new ExerciseModule())
                .build()
                .inject(this);
    }

    private void initExerciseList(int currentWorkoutId) {
        LiveData<List<ExercisePerWorkout>> exerciseList = exerciseViewModel.getExercisesPerWorkoutList(currentWorkoutId);
        exerciseList.observe(this, exercisePerWorkoutList -> exerciseAdapter.updateExerciseList(exercisePerWorkoutList));
    }
}
