package workshop.g_s.myfitnessnutritiondairy.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.concurrent.atomic.AtomicReference;

import workshop.g_s.myfitnessnutritiondairy.application.dagger.ApplicationComponent;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.ApplicationModule;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.DaggerApplicationComponent;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.NetModule;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.RepositoryModule;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.RxModule;

public class App extends Application implements Application.ActivityLifecycleCallbacks {

    private static ApplicationComponent applicationComponent;
    private static final AtomicReference<Activity> currentActivity = new AtomicReference<>();

    public App() {
        super();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static Activity getCurrentActivity() {
        return currentActivity.get();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(this);
        initApplicationComponent();
    }

    private void initApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .repositoryModule(new RepositoryModule(this))
                .netModule(new NetModule())
                .rxModule(new RxModule())
                .build();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        unregisterActivityLifecycleCallbacks(this);
    }

    /////////////////////////////////////
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        currentActivity.set(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
