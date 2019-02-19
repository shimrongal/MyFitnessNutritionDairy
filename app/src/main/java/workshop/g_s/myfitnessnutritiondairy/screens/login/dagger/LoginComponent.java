package workshop.g_s.myfitnessnutritiondairy.screens.login.dagger;

import dagger.Component;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.ApplicationComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.login.LoginActivity;

@LoginScope
@Component(dependencies = {ApplicationComponent.class})
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
