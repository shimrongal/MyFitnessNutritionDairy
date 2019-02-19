package workshop.g_s.myfitnessnutritiondairy.application.db;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import workshop.g_s.myfitnessnutritiondairy.application.db.local.LocalRepository;
import workshop.g_s.myfitnessnutritiondairy.application.db.remote.RemoteRepository;
import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;
import workshop.g_s.myfitnessnutritiondairy.utils.rx.RxSchedulers;

public class ApplicationRepositoryImpl implements ApplicationRepository {
    private static final String TAG = "ApplicationRepositoryIm";
    private final RxSchedulers rxSchedulers;
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public ApplicationRepositoryImpl(RemoteRepository remoteRepository, LocalRepository localRepository, RxSchedulers rxSchedulers) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
        this.rxSchedulers = rxSchedulers;
    }

    public LiveData<List<Food>> getFoods() {
        refreshFoodList();
        return localRepository.loodFoodList();
    }

    @Override
    public LiveData<List<Workout>> loadWorkouts() {
        refreshWorkoutList();
        return localRepository.loadWorkoutList();
    }

    private void refreshWorkoutList() {

        Observable<List<Workout>> listWorkouts = remoteRepository.provideWorkoutList();
        listWorkouts.observeOn(rxSchedulers.io()).subscribeOn(rxSchedulers.androidMainThread())
                .doOnNext(p -> {
                    localRepository.insertWorkouts(p);
                    Log.e(TAG, "refreshWorkoutList: doOnNext() )");
                })
                .doOnComplete(() -> Log.e(TAG, "refreshWorkoutList: onComplete "))
                .subscribe();
    }

    private void refreshFoodList() {
        Observable<List<Food>> foodListObservable = remoteRepository.provideFoodList();
        foodListObservable.observeOn(rxSchedulers.io())
                .subscribeOn(rxSchedulers.androidMainThread())
                .doOnNext(foodArrayList -> localRepository.insertFoodList(foodArrayList))
                .subscribe();
    }

    public RxSchedulers getRxSchedulers() {
        return rxSchedulers;
    }

    @Override
    public LiveData<List<ExercisePerWorkout>> loadExercisesPerWorkout(int currentWorkoutId) {
        refreshExercisesPerWorkout(currentWorkoutId);
        return localRepository.loadExercisesPerWorkout(currentWorkoutId);
    }

    private void refreshExercisesPerWorkout(int currentWorkoutId) {
        Observable<List<ExercisePerWorkout>> exerciseListPerWorkout = remoteRepository.provideExerciseListPerWorkout(currentWorkoutId);
        exerciseListPerWorkout
                .observeOn(rxSchedulers.io())
                .subscribeOn(rxSchedulers.androidMainThread())
                .doOnNext(exercises -> localRepository.insertExerciseListPerWorkout(exercises))
                .subscribe();
    }
}

