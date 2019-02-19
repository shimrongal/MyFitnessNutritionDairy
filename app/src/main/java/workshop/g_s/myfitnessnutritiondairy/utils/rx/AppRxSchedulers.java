package workshop.g_s.myfitnessnutritiondairy.utils.rx;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AppRxSchedulers implements RxSchedulers {

    private static Executor internetExecutor = Executors.newCachedThreadPool();
    private static Scheduler INTERNET_SCHEDULERS = Schedulers.from(internetExecutor);

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler androidMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler internet() {
        return INTERNET_SCHEDULERS;
    }


}
