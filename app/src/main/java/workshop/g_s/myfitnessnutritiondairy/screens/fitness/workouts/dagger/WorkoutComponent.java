package workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.dagger;

import dagger.Component;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.ApplicationComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.core.WorkoutFragment;

@WorkoutScope
@Component(dependencies = {ApplicationComponent.class}, modules = {WorkoutsModule.class})
public interface WorkoutComponent {

    void inject(WorkoutFragment workoutFragment);
}
