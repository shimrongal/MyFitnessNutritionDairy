package workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.dagger;

import dagger.Component;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.ApplicationComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.core.ExerciseFragment;

@ExerciseScope
@Component(dependencies = {ApplicationComponent.class}, modules = {ExerciseModule.class})
public interface ExerciseComponent {

    void inject(ExerciseFragment exerciseFragment);
}
