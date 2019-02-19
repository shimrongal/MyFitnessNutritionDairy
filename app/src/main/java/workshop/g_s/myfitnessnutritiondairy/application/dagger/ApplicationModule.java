package workshop.g_s.myfitnessnutritiondairy.application.dagger;

import android.content.Context;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import workshop.g_s.myfitnessnutritiondairy.application.App;
import workshop.g_s.myfitnessnutritiondairy.utils.Coordinator;

@Module
@Singleton
public class ApplicationModule {

    private final App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @AppScope
    @Provides
    Context provideApplicationContext() {
        return app.getApplicationContext();
    }

    @AppScope
    @Provides
    Coordinator provideCoordinator() {
        return new Coordinator();
    }

    @AppScope
    @Provides
    Gson provideGson() {
        return new Gson();
    }
}
