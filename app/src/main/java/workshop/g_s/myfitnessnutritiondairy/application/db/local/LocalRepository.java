package workshop.g_s.myfitnessnutritiondairy.application.db.local;


import android.arch.lifecycle.LiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.entities.ExercisePerWorkout;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;
import workshop.g_s.myfitnessnutritiondairy.entities.Mineral;
import workshop.g_s.myfitnessnutritiondairy.entities.Vitamin;
import workshop.g_s.myfitnessnutritiondairy.entities.Workout;

public class LocalRepository implements ApplicationRepository.Local {

    private ApplicationRoomDao applicationRoomDao;
    private Executor executor;

    @Inject
    public LocalRepository(Executor executor, ApplicationRoomDao applicationRoomDao) {
        this.applicationRoomDao = applicationRoomDao;
        this.executor = executor;
    }

    @Override
    public void insertFoodList(List<Food> foodList) {
        Gson gson = new Gson();
        ArrayList<Vitamin> arrayListVitamins = new ArrayList<>();
        ArrayList<Mineral> arrayListMinerales = new ArrayList<>();

        for (int index = 0; index < foodList.size(); index++) {
            Food food = foodList.get(index);

            String strListVitamins = "[" + food.getStrListVitamins() + "]";
            arrayListVitamins = gson.fromJson(strListVitamins, new TypeToken<List<Vitamin>>() {
            }.getType());

            String strListMinerals = "[" + food.getStrListMinerals() + "]";
            arrayListMinerales = gson.fromJson(strListMinerals, new TypeToken<List<Mineral>>() {
            }.getType());
        }
        executor.execute(() -> applicationRoomDao.insertFoodList(foodList));

        if (null != arrayListMinerales && !arrayListMinerales.isEmpty()) {
            if (arrayListMinerales.size() == 1) {
                if (arrayListMinerales.get(0) != null) {
                    insertMineralesList(arrayListMinerales);

                }
            } else {
                insertMineralesList(arrayListMinerales);
            }
        }
        if (null != arrayListVitamins && !arrayListVitamins.isEmpty()) {
            if (arrayListVitamins.size() == 1) {
                if (arrayListVitamins.get(0) != null)
                    insertVitaminList(arrayListVitamins);
            } else {
                insertVitaminList(arrayListVitamins);
            }

        }
    }

    @Override
    public void insertVitaminList(List<Vitamin> vitamins) {
        executor.execute(() -> applicationRoomDao.insertVitaminsList(vitamins));
    }


    @Override
    public void insertMineralesList(List<Mineral> minerales) {
        executor.execute(() -> applicationRoomDao.insertMineralesList(minerales));
    }

    @Override
    public void insertWorkouts(List<Workout> workouts) {
        executor.execute(() -> applicationRoomDao.insertWorkouts(workouts));
    }

    @Override
    public LiveData<List<ExercisePerWorkout>> loadExercisesPerWorkout(int currentWorkoutId) {
        return applicationRoomDao.loadExercisesPerWorkout(currentWorkoutId);
    }

    @Override
    public void insertExerciseListPerWorkout(List<ExercisePerWorkout> exercises) {
        executor.execute(() -> applicationRoomDao.insertExerciseListPerWorkout(exercises));
    }

    @Override
    public LiveData<List<Food>> loodFoodList() {
        return applicationRoomDao.getFoodList();
    }

    public LiveData<List<Workout>> loadWorkoutList() {
        return applicationRoomDao.getWorkoutList();
    }

}
