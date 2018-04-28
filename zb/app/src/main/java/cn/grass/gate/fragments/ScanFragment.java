package cn.grass.gate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.zxing.BarcodeFormat;
import com.mining.app.zxing.camera.CameraManager;
import com.mining.app.zxing.decoding.CaptureActivityHandler;
import com.mining.app.zxing.decoding.InactivityTimer;
import com.mining.app.zxing.view.ViewfinderView;

import java.util.Vector;

import cn.grass.gate.GrassApp;
import cn.grass.gate.R;
import cn.grass.gate.base.BaseFragment;

/**
 * Created by min on 2017/8/1.
 */

public class ScanFragment extends BaseFragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vs = inflater.inflate(R.layout.fragment_scan, container, false);
        initView(vs);
        return vs;
    }
    private View vs;
    private int type;
    private FrameLayout scanFl;
    private FrameLayout photoFl;
    private ViewfinderView scanViewFinder;
    private boolean hasSurface;
    public InactivityTimer inactivityTimer;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    public CaptureActivityHandler scanHandler;

    private void initView(View vs) {
        Bundle bundle = getArguments();
        if (bundle != null) {
//            type = bundle.getInt(ScanBoxActivity.SCAN_TYPE, ScanBoxActivity.SCAN_CODE);
        }
        scanFl = (FrameLayout)vs.findViewById(R.id.scan_fl);
        photoFl = (FrameLayout)vs.findViewById(R.id.photo_fl);
//        if(ScanBoxActivity.SCAN_CODE == type){
//            scanFl.setVisibility(View.VISIBLE);
//            photoFl.setVisibility(View.GONE);
//        }else{
//            scanFl.setVisibility(View.GONE);
//            photoFl.setVisibility(View.VISIBLE);
//        }
        CameraManager.init(getActivity().getApplication());
        scanViewFinder = (ViewfinderView) vs.findViewById(R.id.viewfinder_view);
        hasSurface = false;
        inactivityTimer = new InactivityTimer(getActivity());

    }


//    @Override
//    public void onResume() {
//        super.onResume();
//        SurfaceView surfaceView = (SurfaceView) vs.findViewById(R.id.preview_view);
//        SurfaceHolder surfaceHolder = surfaceView.getHolder();
//        if (hasSurface) {
//            initCamera(surfaceHolder);
//        } else {
//            surfaceHolder.addCallback(getActivity());
//            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        }
//        decodeFormats = null;
//        characterSet = null;
//    }
//
//    private void initCamera(SurfaceHolder surfaceHolder) {
//        try {
//            CameraManager.get().openDriver(surfaceHolder);
//        } catch (IOException ioe) {
//            return;
//        } catch (RuntimeException e) {
//            return;
//        }
//        if (scanHandler == null) {
//            scanHandler = new CaptureActivityHandler(getActivity(), decodeFormats,
//                    characterSet);
//        }
//    }
    public void onPause() {
        super.onPause();
        try {
            if (!GrassApp.getInstance().isLogin()) {
                CameraManager.get().closeDriver();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

//    public static String KEY_BOX_CODE = "key_box_code";//key键
//    @Override
//    public void initHandlerDecode(Result result, Bitmap barcode) {
//        if (result != null) {
//            String[] temp = result.getText().toString().split(":");
//            if (temp != null && temp.length > 0) {
//                String resultStr = temp[0];
//                if ("contact".equals(resultStr)) {
//                    String tx = result.getText().toString();
//                    LogUtil.show("log:" + tx);
//                    if (tx.length() > 0 && tx.startsWith("contact:")) {
//                        String uname = tx.substring("contact:".length(), tx.length());
////                        httpGetFriend(uname);
//                    }
//                } else if ("login".equals(resultStr)) {
////                    ScanResultActivity.startScanResultActivityForLogin(this, temp[1], 11);
//                }  else {
//                    Intent it = new Intent();
//                    it.putExtra(KEY_BOX_CODE, resultStr);
//                    setResult(RESULT_OK, it);
//                    finish();
////                    ToastUtil.show(result.getText().toString());
////                    ToastUtil.show("请使用正确的二维码进行扫描~"+result.getText().toString());//TODO
//                }
//            } else {
//                ToastUtil.show("请使用正确的二维码进行扫描~"+result.getText().toString());//TODO
////                ToastUtil.show(result.getText().toString());
//            }
//            LogUtil.show("txt:" + result.getText());
//        }
//    }

}
