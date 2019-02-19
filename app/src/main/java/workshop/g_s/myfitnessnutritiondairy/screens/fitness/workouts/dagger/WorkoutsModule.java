package workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.dagger;

import dagger.Module;
import dagger.Provides;
import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.core.WorkoutViewModelFactory;

@Module
public class WorkoutsModule {

    @WorkoutScope
    @Provides
    WorkoutViewModelFactory provideWorkoutViewModelFactory(ApplicationRepository applicationRepository) {
        return new WorkoutViewModelFactory(applicationRepository);
    }
}
