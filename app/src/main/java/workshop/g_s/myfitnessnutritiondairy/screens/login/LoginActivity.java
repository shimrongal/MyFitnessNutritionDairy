package workshop.g_s.myfitnessnutritiondairy.screens.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import workshop.g_s.myfitnessnutritiondairy.R;
import workshop.g_s.myfitnessnutritiondairy.application.App;
import workshop.g_s.myfitnessnutritiondairy.screens.login.dagger.DaggerLoginComponent;
import workshop.g_s.myfitnessnutritiondairy.utils.Constants;
import workshop.g_s.myfitnessnutritiondairy.utils.Coordinator;

public class LoginActivity extends AppCompatActivity {

    @Inject
    Coordinator coordinator;
    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerLoginComponent.builder()
                .applicationComponent(App.getApplicationComponent())
                .build()
                .inject(this);
        if (sharedPreferences.getBoolean(Constants.IS_USER_LOGED, false)) {
            goToFoodsFragment();
        } else {
            setContentView(R.layout.layout_activity_login);
            initFirebaseLogin();
        }
    }

    private void initFirebaseLogin() {
        List<AuthUI.IdpConfig> authProviders = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(authProviders).build(), Constants.FIREBASE_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.FIREBASE_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                sharedPreferences.edit().putBoolean(Constants.IS_USER_LOGED, true).apply();
                goToFoodsFragment();
            }
        }
        AuthUI.getInstance().signOut(this);
    }

    private void goToFoodsFragment() {
        coordinator.goToFoodsFragmentFromCurrentActivity(this);
    }
}
