package workshop.g_s.myfitnessnutritiondairy.utils.rx;


import io.reactivex.Scheduler;

public interface RxSchedulers {

    Scheduler io();

    Scheduler androidMainThread();

    Scheduler internet();
}
