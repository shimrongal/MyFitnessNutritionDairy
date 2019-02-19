package workshop.g_s.myfitnessnutritiondairy.application.dagger;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import workshop.g_s.myfitnessnutritiondairy.utils.rx.RxSchedulers;

/**
 * Created by g-s on 05/04/2018.
 */
@Singleton
@Module
public class NetModule {
    @AppScope
    @Provides
    OkHttpClient provideHttpClient(HttpLoggingInterceptor logger, Cache cache) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.addInterceptor(logger);
        builder.cache(cache);
        return builder.build();
    }

    @AppScope
    @Provides
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @AppScope
    @Provides
    Cache provideCache(File file) {
        return new Cache(file, 10 * 10 * 1000);
    }

    @AppScope
    @Provides
    File provideCacheFile(Context context) {
        return context.getFilesDir();
    }

    @AppScope
    @Provides
    RxJava2CallAdapterFactory provideRxAdapter(RxSchedulers rxSchedulers) {
        return RxJava2CallAdapterFactory.createWithScheduler(rxSchedulers.internet());
    }

    @AppScope
    @Provides
    GsonConverterFactory provideGsonClient() {
        return GsonConverterFactory.create(new GsonBuilder().setLenient().create());
    }
}
