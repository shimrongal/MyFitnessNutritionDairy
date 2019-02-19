package workshop.g_s.myfitnessnutritiondairy.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;

import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.App;

/**
 * Class for functions reuse.
 */
public class ApplicationFunctions {

    private volatile static ApplicationFunctions instance;
    private final Coordinator coordinator;
    private long backPressed = 0;

    private ApplicationFunctions() {
        coordinator = new Coordinator();
    }

    public static ApplicationFunctions getInstance() {
        if (instance == null) {  // first check
            synchronized (ApplicationFunctions.class) {
                if (instance == null) {  // second check
                    instance = new ApplicationFunctions();
                }
            }
        }
        return instance;
    }

    public RequestOptions getRequestOptions(@NonNull View inflateMovieViewHolder) {
        return new RequestOptions().placeholder(getCircularProgressDrawable(inflateMovieViewHolder)).error(R.drawable.baseline_error_outline_black);
    }


    private CircularProgressDrawable getCircularProgressDrawable(@NonNull View itemView) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(itemView.getContext());
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }

    public void initNavigationView(NavigationView navigationView) {
        navigationView.inflateMenu(R.menu.menu_navigation_view);
        navigationView.setNavigationItemSelectedListener((item) -> {
            int itemId = item.getItemId();
            mainMenuSwitch(itemId);
            return true;
        });
    }

    public void initToolBar(ActionBar supportActionBar, String title) {
        supportActionBar.setTitle(title);
        supportActionBar.setDisplayShowHomeEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.menu_black);
    }

    private void mainMenuSwitch(int itemId) {

        Activity currentActivity = App.getCurrentActivity();
        switch (itemId) {
            case R.id.nav_foods:
                coordinator.goToFoodsFragmentFromCurrentActivity(currentActivity);
                break;
            case R.id.nav_workouts:
                coordinator.goToWorkoutsFragmentFromCurrentActivity(currentActivity);
                break;
            case R.id.nav_scanner:
                if (Camera.getNumberOfCameras() == 0) {
                    Toast.makeText(App.getCurrentActivity(), "Unfortunately your phone do not have a camera device", Toast.LENGTH_SHORT).show();
                } else if (Build.VERSION.SDK_INT >= 23) {
                    askForCameraPermission(currentActivity);
                } else {
                    coordinator.goToScannerFragment(currentActivity);
                }
                break;
            case R.id.nav_exit:
                // coordinator.exitApp(currentActivity);
                currentActivity.finishAffinity();
                break;
            default:
                break;
        }
    }

    @TargetApi(23)
    private void askForCameraPermission(Activity currentActivity) {
        String permissionCamera = Manifest.permission.CAMERA;
        if (ContextCompat.checkSelfPermission(currentActivity, permissionCamera) != PackageManager.PERMISSION_GRANTED) {
            currentActivity.requestPermissions(new String[]{permissionCamera}, Constants.CAMERA_REQUEST_CODE);
        } else {
            coordinator.goToScannerFragment(currentActivity);
        }
    }

    public void onRequestPermissionResult(String permission) {
        Activity currentActivity = App.getCurrentActivity();
        if (ActivityCompat.checkSelfPermission(currentActivity, permission) == PackageManager.PERMISSION_GRANTED) {
            coordinator.goToScannerFragment(currentActivity);
        } else {
            Toast.makeText(currentActivity, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        AppCompatActivity currentActivity = (AppCompatActivity) App.getCurrentActivity();
        int backStackEntryCount = currentActivity.getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntryCount > 1) {
            currentActivity.getSupportFragmentManager().popBackStack();
        } else if (backPressed + Constants.TIME_INTERVAL_FOR_DOUBLE_CLICK_TO_EXIT > System.currentTimeMillis()) {
            currentActivity.finishAffinity();
        } else {
            Toast.makeText(currentActivity, "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
            backPressed = System.currentTimeMillis();
        }
    }
}
