package workshop.g_s.myfitnessnutritiondairy.screens.camera.camera_activity;

import android.os.Bundle;
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
import workshop.g_s.myfitnessnutritiondairy.screens.camera.camera_activity.dagger.DaggerCameraActivityComponent;
import workshop.g_s.myfitnessnutritiondairy.utils.ApplicationFunctions;
import workshop.g_s.myfitnessnutritiondairy.utils.Constants;
import workshop.g_s.myfitnessnutritiondairy.utils.Coordinator;

public class CameraActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout_camera)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view_camera)
    NavigationView navigationView;
    @BindView(R.id.toolbarCameraActivity)
    Toolbar toolbar;

    @Inject
    Coordinator coordinator;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerCameraActivityComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .build()
                .inject(this);

        setContentView(R.layout.activity_camera);
        initUI();
    }

    protected void onStart() {
        super.onStart();

        String strAction = getIntent().getAction() == null ? "" : getIntent().getAction();
        switch (strAction) {
            case Constants.ACTION_GO_TO_FOODS_FRAGMENT:
            default:
                coordinator.goToScannerFragment(this);
                break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        ApplicationFunctions.getInstance().onBackPressed();
    }

    private void initUI() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ApplicationFunctions.getInstance().initToolBar(getSupportActionBar(), getString(R.string.nutrition));
        ApplicationFunctions.getInstance().initNavigationView(navigationView);
    }


}
