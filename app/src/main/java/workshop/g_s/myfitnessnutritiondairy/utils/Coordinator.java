package workshop.g_s.myfitnessnutritiondairy.utils;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.screens.camera.barcode.BarcodeScannerFragment;
import workshop.g_s.myfitnessnutritiondairy.screens.camera.camera_activity.CameraActivity;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.exercise_list.core.ExerciseFragment;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.fitness_activity.core.FitnessActivity;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.workouts.core.WorkoutFragment;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.fooddetails.core.FoodDetailsFragment;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.foods_list.core.FoodsFragment;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.NutritionActivity;

public class Coordinator {

    public <T extends Activity> void goToFoodsFragmentFromCurrentActivity(T activity) {
        AppCompatActivity currentActivity = (AppCompatActivity) activity;
        if (currentActivity instanceof NutritionActivity) {
            currentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.llNutritionActivityContainer, FoodsFragment.getInstance()).addToBackStack(Constants.FOODS_FRAGMENT).commit();
        } else {
            currentActivity.startActivity(new Intent(currentActivity, NutritionActivity.class).setAction(Constants.ACTION_GO_TO_FOODS_FRAGMENT));
        }
    }

    public <T extends Activity> void goToWorkoutsFragmentFromCurrentActivity(T activity) {
        AppCompatActivity currentActivity = (AppCompatActivity) activity;
        if (currentActivity instanceof FitnessActivity) {
            currentActivity.getSupportFragmentManager().beginTransaction().replace(R.id.llFitnessActivityContainer, WorkoutFragment.getInstance()).addToBackStack(Constants.WORKOUT_FRAGMENT).commit();
        } else {
            currentActivity.startActivity(new Intent(currentActivity, FitnessActivity.class).setAction(Constants.GO_TO_WORKOUT_FRAGMENT));
        }
    }

    public void goToFoodDetailFragment(FragmentActivity fragmentActivity, long foodId) {
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.llNutritionActivityContainer, FoodDetailsFragment.getInstance(foodId))
                .addToBackStack(Constants.FOOD_DETAILS_FRAGMENT)
                .commit();
    }

    public void goToExerciseListFragment(FragmentActivity currentActivity, int id) {
        currentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.llFitnessActivityContainer, ExerciseFragment.getInstance(id))
                .addToBackStack(Constants.EXERCISE_FRAGMENT)
                .commit();

    }

    public void searchStringInGoogleEngine(Context context, String strToSearch) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(context.getString((R.string.search_url), strToSearch))));
    }

    public void goToWebBrowser(Context context, String strForSearch) {
        context.startActivity(new Intent(Intent.ACTION_WEB_SEARCH).putExtra(SearchManager.QUERY, strForSearch));
    }

    public <T extends Activity> void goToScannerFragment(T activity) {
        AppCompatActivity currentActivity = (AppCompatActivity) activity;
        if (currentActivity instanceof CameraActivity) {
            currentActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.clCameraContainer, BarcodeScannerFragment.getFragmentInstance())
                    .addToBackStack(Constants.BARCODE_SCANNER_FRAGMENT)
                    .commit();
        } else {
            currentActivity.startActivity(new Intent(currentActivity, CameraActivity.class).setAction(Constants.ACTION_GO_TO_SCANNER_FRAGMENT));
        }
    }

    public void exitApp(Activity currentActivity) {
        if (Build.VERSION.SDK_INT > 20) {
            currentActivity.finishAndRemoveTask();
        } else {
            currentActivity.finishAffinity();
        }
        //System.exit is used because the use of dagger
        System.exit(0);
    }
}

