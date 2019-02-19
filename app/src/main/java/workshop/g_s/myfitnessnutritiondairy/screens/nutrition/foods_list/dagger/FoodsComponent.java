package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.dagger;

import dagger.Component;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.ApplicationComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.core.FoodsFragment;

@FoodsScope
@Component(dependencies = {ApplicationComponent.class})
public interface FoodsComponent {

    void inject(FoodsFragment foodsFragment);
}
