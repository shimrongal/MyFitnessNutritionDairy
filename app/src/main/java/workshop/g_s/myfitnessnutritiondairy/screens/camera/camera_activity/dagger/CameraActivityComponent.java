package workshop.g_s.myfitnessnutritiondairy.screens.camera.camera_activity.dagger;

import dagger.Component;
import workshop.g_s.myfitnessnutritiondairy.application.dagger.ApplicationComponent;
import workshop.g_s.myfitnessnutritiondairy.screens.camera.camera_activity.CameraActivity;

@CameraActovotyScope
@Component(dependencies = {ApplicationComponent.class})
public interface CameraActivityComponent {
    void inject(CameraActivity cameraActivity);
}
