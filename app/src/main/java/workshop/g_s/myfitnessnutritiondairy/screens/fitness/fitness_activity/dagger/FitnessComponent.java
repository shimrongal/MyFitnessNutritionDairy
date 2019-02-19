package workshop.g_s.myfitnessnutritiondairy.screens.fitness.fitness_activity.dagger;

import dagger.Component;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.ApplicationComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.fitness_activity.core.FitnessActivity;

@FitnessActivityScope
@Component(dependencies = {ApplicationComponent.class})
public interface FitnessComponent extends ApplicationComponent {

    void inject(FitnessActivity fitnessActivity);
}
