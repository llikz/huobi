package cn.grass.gate.widgets;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by weedys on 16/10/5.
 */
public class MultEditView extends EditText{
    public MultEditView(Context context) {
        super(context);
    }

    public MultEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 插入图片
     * @param path
     */
    public void insertImage(String path){
        String text="<img>"+path+"</img>";
        SpannableString ss = new SpannableString(text);
        Bitmap pic= BitmapFactory.decodeFile(path);
        pic=changeBitmap(pic);
        ImageSpan span = new ImageSpan(getContext(), pic);
        ss.setSpan(span, 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        Editable et = this.getText();
        int start = this.getSelectionStart();
        // 设置ss要添加的位置
        et.insert(start, ss);
        // 把et添加到Edittext中
        this.setText(et);
        // 设置Edittext光标在最后显示
        this.setSelection(start + ss.length());
    }

    /**
     * 修改图片的宽度
     * @param pic
     * @return
     */
    private Bitmap changeBitmap(Bitmap pic){
        int imgWidth = pic.getWidth();
        int imgHeight = pic.getHeight();
        // 只对大尺寸图片进行下面的压缩，小尺寸图片使用原图
        int mInsertedImgWidth=getWidth();
        if (imgWidth >= mInsertedImgWidth) {
            float scale = (float) mInsertedImgWidth / imgWidth;
            Matrix mx = new Matrix();
            mx.setScale(scale, scale);
            pic = Bitmap.createBitmap(pic, 0, 0, imgWidth, imgHeight, mx, true);
        }
        return pic;
    }
}
