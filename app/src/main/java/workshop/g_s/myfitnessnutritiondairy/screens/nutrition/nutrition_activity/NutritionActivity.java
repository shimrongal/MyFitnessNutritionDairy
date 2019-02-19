package workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.App;
import workshop.g_s.myfitnessnutritiondairy.application.db.ApplicationRepository;
import workshop.g_s.myfitnessnutritiondairy.application.db.local.LocalRepository;
import workshop.g_s.myfitnessnutritiondairy.entities.Food;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.dagger.DaggerNutritionActivityComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.dagger.NutritionActivityModule;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.view_model.NutritionViewModel;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.view_model.NutritionViewModelFactory;
import workshop.g_s.myfitnessnutritiondairy.screens.nutrition.nutrition_activity.view_model.NutritionViewModelImpl;
import workshop.g_s.myfitnessnutritiondairy.utils.ApplicationFunctions;
import workshop.g_s.myfitnessnutritiondairy.utils.Constants;
import workshop.g_s.myfitnessnutritiondairy.utils.Coordinator;

public class NutritionActivity extends AppCompatActivity {

    public static final String TAG = "NutritionActivity";
    @BindView(R.id.drawer_layout_nutrition)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view_nutrition)
    NavigationView navigationView;
    @BindView(R.id.toolbarNutritionActivity)
    Toolbar toolbar;
    @Inject
    ApplicationRepository applicationRepository;
    @Inject
    Coordinator coordinator;
    @Inject
    LocalRepository localRepository;
    @Inject
    SharedPreferences sharedPref;
    @Inject
    Gson gson;

    @Inject
    NutritionViewModelFactory nutritionViewModelFactory;

    private Unbinder bindButterKnife;
    private NutritionViewModel nutritionViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_nutrition);
        Log.e(TAG, "onCreate: ");
        initDagger();
        initUI();
        initNutritionViewModel();
        initFoodsList();
    }

    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
        String action = getIntent().getAction() != null ? getIntent().getAction() : "";
        switch (action) {
            case Constants.ACTION_GO_TO_FOODS_FRAGMENT:
                coordinator.goToFoodsFragmentFromCurrentActivity(this);
                break;
            default:
                coordinator.goToFoodsFragmentFromCurrentActivity(this);
                break;
        }

    }

    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
        bindButterKnife.unbind();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void initDagger() {
        DaggerNutritionActivityComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .nutritionActivityModule(new NutritionActivityModule(this))
                .build().inject(this);
    }

    private void initNutritionViewModel() {

        nutritionViewModel = ViewModelProviders.of(this, nutritionViewModelFactory).get(NutritionViewModelImpl.class);
    }

    private void initUI() {
        bindButterKnife = ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ApplicationFunctions.getInstance().initToolBar(getSupportActionBar(), getString(R.string.nutrition));
        ApplicationFunctions.getInstance().initNavigationView(navigationView);
    }

    private void initFoodsList() {
        LiveData<List<Food>> listLiveDataFoods = nutritionViewModel.initiateFoodsLocalRepositoryTable();
        listLiveDataFoods.observe(this, foodList -> {
            String strJsonFoodList = gson.toJson(foodList);
            sharedPref.edit().putString(Constants.FOOD_LIST_OBJECT, strJsonFoodList).apply();
        });
    }

    public ApplicationRepository getApplicationRepository() {
        return applicationRepository;
    }

    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    public void onBackPressed() {
        ApplicationFunctions.getInstance().onBackPressed();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.CAMERA_REQUEST_CODE) {
            ApplicationFunctions.getInstance().onRequestPermissionResult(permissions[0]);
        }
    }
}
