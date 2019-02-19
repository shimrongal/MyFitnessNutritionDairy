/* package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.dagger;

import dagger.Module;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.core.FoodsFragment;

@Module
public class FoodsModule {

    private final FoodsFragment foodsFragment;

    public FoodsModule(FoodsFragment foodsFragment) {
        this.foodsFragment = foodsFragment;
    }

   @FoodsScope
    @Provides
    public FoodsModel provideFoodsModel( ApplicationRestApi foodApi){
        return new FoodsModel(foodsFragment.getActivity() , foodApi );
    }
}
*/