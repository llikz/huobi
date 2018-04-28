package com.mining.app.zxing;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;
import com.mining.app.zxing.decoding.CaptureActivityHandler;
import com.mining.app.zxing.decoding.InactivityTimer;
import com.mining.app.zxing.view.ViewfinderView;
import com.weedys.weedlibrary.R;

import java.io.IOException;

/**
 * Created by weedys on 16/11/22.
 */
public abstract class BaseScanActivity extends AppCompatActivity {
    public ViewfinderView viewfinderView;
    public CaptureActivityHandler scanHandler;
    public InactivityTimer inactivityTimer;
    private MediaPlayer mediaPlayer;
    private boolean playBeep;
    private static final float BEEP_VOLUME = 0.10f;
    private static final long VIBRATE_DURATION = 200L;
    private boolean vibrate;
    public abstract void initScanView();
    public abstract void initHandlerDecode(Result result, Bitmap barcode);
    public void drawViewfinder() {
        if(viewfinderView==null){
            initScanView();
        }
        viewfinderView.drawViewfinder();

    }
    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }
    public Handler getHandler() {
        return scanHandler;
    }
    /**
     * 处理扫描结果
     * @param result
     * @param barcode
     */
    public void handleDecode(Result result, Bitmap barcode) {
        inactivityTimer.onActivity();
        playBeepSoundAndVibrate();
        String resultString = result.getText();
        if (resultString.equals("")) {
            Toast.makeText(this, "Scan failed!", Toast.LENGTH_SHORT).show();
        }else {
            initHandlerDecode(result,barcode);
//            if(resultString.startsWith("http") && resultString.contains("pileno=")){
//                int index=resultString.indexOf("pileno=");
//                String str=resultString.substring(index+7, resultString.length());
//                if(!TextUtils.isEmpty(str)){
////                    chargeId=str;
////                    httpGetPayInfo();
//                    //请求
//                }
//            }else{
////                chargeId=resultString;
////                httpGetPayInfo();
//            }
        }
        //ScanActvity.this.finish();
    }

    public void onDestroy() {
        if(inactivityTimer!=null)
            inactivityTimer.shutdown();
        super.onDestroy();
    }
    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (scanHandler != null) {
            scanHandler.quitSynchronously();
            scanHandler = null;
        }
        try{
        }catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initBeepPlay();
    }

    private void initBeepPlay(){
        playBeep = true;
        AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
            playBeep = false;
        }
        initBeepSound();
    }

    private void playBeepSoundAndVibrate() {
        if (playBeep && mediaPlayer != null) {
            mediaPlayer.start();
        }
        if (vibrate) {
            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrator.vibrate(VIBRATE_DURATION);
        }
    }
    private void initBeepSound() {
        if (playBeep && mediaPlayer == null) {
            // The volume on STREAM_SYSTEM is not adjustable, and users found it
            // too loud,
            // so we now play on the music stream.
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }






}
