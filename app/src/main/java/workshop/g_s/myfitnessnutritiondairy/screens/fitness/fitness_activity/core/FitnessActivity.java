package workshop.g_s.myfitnessnutritiondairy.screens.fitness.fitness_activity.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.App;
import workshop.g_s.myfitnessnutritiondairy.screens.fitness.fitness_activity.dagger.DaggerFitnessComponent;
import workshop.g_s.myfitnessnutritiondairy.utils.ApplicationFunctions;
import workshop.g_s.myfitnessnutritiondairy.utils.Constants;
import workshop.g_s.myfitnessnutritiondairy.utils.Coordinator;

public class FitnessActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout_fitness)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view_fitness)
    NavigationView navigationView;
    @BindView(R.id.toolbarFitnessActivity)
    Toolbar toolbar;
    @Inject
    Coordinator coordinator;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_fitnees);
        initFitnessComponent();
        initUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String whereToGo = getIntent().getAction() != null ? getIntent().getAction() : "";
        switch (whereToGo) {
            case Constants.ACTION_GO_TO_FOODS_FRAGMENT:
                coordinator.goToFoodsFragmentFromCurrentActivity(this);
                break;
            default:
                coordinator.goToWorkoutsFragmentFromCurrentActivity(this);
                break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFitnessComponent() {
        DaggerFitnessComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .build()
                .inject(this);
    }

    private void initUI() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ApplicationFunctions applicationFunctions = ApplicationFunctions.getInstance();
        applicationFunctions.initToolBar(getSupportActionBar(), getString(R.string.fitness));
        applicationFunctions.initNavigationView(navigationView);
    }

    @Override
    public void onBackPressed() {
        ApplicationFunctions.getInstance().onBackPressed();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.CAMERA_REQUEST_CODE) {
            ApplicationFunctions.getInstance().onRequestPermissionResult(permissions[0]);
        }
    }
}
