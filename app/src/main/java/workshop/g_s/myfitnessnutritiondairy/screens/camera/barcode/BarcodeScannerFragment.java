package workshop.g_s.myfitnessnutritiondairy.screens.camera.barcode;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import workshop.g_s.myfitnessnutritiondairy.R;

public class BarcodeScannerFragment extends Fragment implements ZBarScannerView.ResultHandler {
    @BindView(R.id.flBarcodeScanner)
    ViewGroup flBarcodeScanner;
    private Unbinder butterKnife;
    private ZBarScannerView zBarScannerView;

    public static BarcodeScannerFragment getFragmentInstance() {
        return new BarcodeScannerFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateBarcodeScannerFragment = inflater.inflate(R.layout.layout_barcode_scanner_fragment, container, false);
        butterKnife = ButterKnife.bind(this, inflateBarcodeScannerFragment);

        zBarScannerView = new ZBarScannerView(getActivity());
        flBarcodeScanner.addView(zBarScannerView);

        return inflateBarcodeScannerFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        zBarScannerView.setResultHandler(this);
        zBarScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        zBarScannerView.stopCamera();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        butterKnife.unbind();
    }

    public void handleResult(Result result) {
        Toast.makeText(getActivity(), "Contents = " + result.getContents() + ", Format = " + result.getBarcodeFormat().getName(), Toast.LENGTH_SHORT).show();
        // * Wait 3 seconds to resume the preview.
        Handler handler = new Handler();
        handler.postDelayed(() -> zBarScannerView.resumeCameraPreview(BarcodeScannerFragment.this), 3000);
    }
}
