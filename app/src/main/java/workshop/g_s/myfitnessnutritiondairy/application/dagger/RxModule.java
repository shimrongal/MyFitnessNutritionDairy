package workshop.g_s.myfitnessnutritiondairy.application.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import workshop.g_s.myfitnessnutritiondairy.utils.rx.AppRxSchedulers;
import workshop.g_s.myfitnessnutritiondairy.utils.rx.RxSchedulers;

@Module
@Singleton
public class RxModule {
    @AppScope
    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}
