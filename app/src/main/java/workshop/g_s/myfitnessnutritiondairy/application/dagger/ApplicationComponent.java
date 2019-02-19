package workshop.g_s.myfitnessnutritiondairy.application.dagger;


import android.content.SharedPreferences;

import com.google.gson.Gson;

import dagger.Component;
import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.application.db.local.LocalRepository;
import workshop.g_s.myfitnessnutritiondairy.application.db.remote.RemoteRepository;
import workshop.g_s.myfitnessnutritiondairy.utils.Coordinator;
import workshop.g_s.myfitnessnutritiondairy.utils.rx.RxSchedulers;

@AppScope
@Component(modules = {ApplicationModule.class, RepositoryModule.class, RxModule.class, NetModule.class})
public interface ApplicationComponent {

    SharedPreferences sharedPreference();

    ApplicationRepository applicationRepository();

    LocalRepository localRepo();

    RemoteRepository remoteRepository();

    RxSchedulers rxSchedulers();

    Coordinator coordinator();

    Gson gson();

}
