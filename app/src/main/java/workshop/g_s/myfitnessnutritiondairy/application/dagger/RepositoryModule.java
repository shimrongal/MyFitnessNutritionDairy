package workshop.g_s.myfitnessnutritiondairy.application.dagger;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.App;
import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepositoryImpl;
import workshop.g_s.myfitnessnutritiondairy.application.db.local.LocalRepository;
import workshop.g_s.myfitnessnutritiondairy.application.db.local.RoomDB;
import workshop.g_s.myfitnessnutritiondairy.application.db.remote.ApplicationRestApi;
import workshop.g_s.myfitnessnutritiondairy.application.db.remote.RemoteRepository;
import workshop.g_s.myfitnessnutritiondairy.utils.Constants;
import workshop.g_s.myfitnessnutritiondairy.utils.rx.RxSchedulers;

@Module
@Singleton
public class RepositoryModule {

    private final App application;

    public RepositoryModule(App application) {
        this.application = application;
    }

    @AppScope
    @Provides
    RoomDB provideRoomDB() {
        return Room.databaseBuilder(application, RoomDB.class, application.getString(R.string.db_name)).build();
    }

    @AppScope
    @Provides
    ApplicationRepository provideApplicationRepository(RemoteRepository remoteRepository, LocalRepository localRepository, RxSchedulers rxSchedulers) {
        return new ApplicationRepositoryImpl(remoteRepository, localRepository, rxSchedulers);
    }

    @AppScope
    @Provides
    SharedPreferences getDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @AppScope
    @Provides
    ApplicationRestApi provideApiService(OkHttpClient client, GsonConverterFactory gson, RxJava2CallAdapterFactory rxAdapter) {
        Retrofit retrofit = new Retrofit.Builder().client(client)
                .baseUrl(Constants.BASE_URL).addConverterFactory(gson).
                        addCallAdapterFactory(rxAdapter).build();

        return retrofit.create(ApplicationRestApi.class);
    }

    @AppScope
    @Provides
    RemoteRepository provideRemoteRepositoy(ApplicationRestApi applicationRestApi) {
        return new RemoteRepository(applicationRestApi);
    }

    @AppScope
    @Provides
    LocalRepository provideLocalRepository(RoomDB roomDB) {
        return new LocalRepository(Executors.newSingleThreadExecutor(), roomDB.applicationDao());
    }

}
