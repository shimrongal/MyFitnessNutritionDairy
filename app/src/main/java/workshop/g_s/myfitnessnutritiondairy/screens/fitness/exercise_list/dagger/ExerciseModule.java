package workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.dagger;

import dagger.Module;
import dagger.Provides;
import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.core.ExerciseViewModelFactory;

@Module
public class ExerciseModule {

    @ExerciseScope
    @Provides
    ExerciseViewModelFactory provideExerciseViewModelFactory(ApplicationRepository applicationRepository) {
        return new ExerciseViewModelFactory(applicationRepository);
    }
}
