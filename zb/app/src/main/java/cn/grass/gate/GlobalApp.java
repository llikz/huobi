package cn.grass.gate;

import java.io.File;

/**
 * Created by weedys on 16/7/22.
 */
public class GlobalApp {
    public static String APP_DIR = "/sdcard/grassCloud";
    public static String LOG_DIR = APP_DIR + "/log";
    public static String DATA_CACHE_DIR = APP_DIR + "/cache";
    public static String PHOTO_PATH = APP_DIR + "/images";
    public static String PIC_URL = APP_DIR+"/cache/";
    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;

    static {
        try {
            new File(APP_DIR).mkdirs();
            new File(LOG_DIR).mkdirs();
            new File(DATA_CACHE_DIR).mkdirs();
            new File(PHOTO_PATH).mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final static String PRE_FIRST = "pre.app.first";

    public final static String PRE_PUSH_ENABLE = "pre.push.enable";
    public final static String PRE_AUDIO_ENABLE = "pre.audio.enable";
    public final static String PRE_VIBI_ENABLE = "pre.vibi.enable";//

    public final static String PRE_UPDATE_VERSION_NEW = "pre.update.new_version";//
    public final static String PRE_UPDATE_VERSION_NEW_MSG = "pre.update.new_msg";//
    public final static String PRE_UPDATE_VERSION_NEW_APK = "pre.update.new_apk";//

    public final static String PRE_USER_LOGIN_UNAME = "pre.user.login.uname";

}
