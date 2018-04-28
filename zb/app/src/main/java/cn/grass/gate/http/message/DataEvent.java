package cn.grass.gate.http.message;

/**
 * Created by weedys on 16/8/10.
 */
public class DataEvent {
    public final static int TYPE_DATA_RELOAD=0X22;
    public final static int TYPE_GET_UNREAD_MSG=0X23;
    public final static int TYPE_GET_UPDATE_MSG=0X24;
    public int mType =TYPE_DATA_RELOAD;
    public final static int TYPE_UPDATE_VERSION=0X20;//sign is change
    public final static int TYPE_UPDATE_VERSION_FORCE=0X120;//sign is change
    public final static int TYPE_UPDATE_CHAT_LIST=0X28;//更新通知列表
    public final static int TYPE_UPDATE_NEW_FRIEND_NUM=0X31;//新朋友数量
    public final static int TYPE_SHOW_WRITE=0X81;//显示写作业
    public final static int TYPE_SHOW_BOX=0X40;//显示表箱图片
    public final static int TYPE_SHOW_METER=0X41;//显示表图片
    public final static int TYPE_SHOW_RESULT=0X42;//显示图片识别结果
    public final static int TYPE_CLOSE_DIALOG=0X43;//关闭掉进度框
    public DataEvent(int type){
        mType =type;
    }


    public final static int TYPE_SHUAXIN_HANGQING=0X100;//刷新行情
}
