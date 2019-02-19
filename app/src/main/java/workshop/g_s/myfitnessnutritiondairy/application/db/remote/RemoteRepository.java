package workshop.g_s.myfitnessnutritiondairy.application.db.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;

public class RemoteRepository implements ApplicationRepository.Remote {

    private final ApplicationRestApi applicationRestApi;

    @Inject
    public RemoteRepository(ApplicationRestApi applicationRestApi) {
        this.applicationRestApi = applicationRestApi;
    }

    public Observable<List<Food>> provideFoodList() {
        return applicationRestApi.getFoods();
    }

    public Observable<List<Workout>> provideWorkoutList() {
        return applicationRestApi.getWorkout();
    }

    @Override
    public Observable<List<ExercisePerWorkout>> provideExerciseListPerWorkout(int currentWorkoutId) {
        return applicationRestApi.getExerciseListPerWorkout(currentWorkoutId);
    }
}
