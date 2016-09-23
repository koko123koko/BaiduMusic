package com.example.dllo.baidumusic.div;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.dllo.baidumusic.R;


/**
 * Created by dllo on 16/8/26.
 */
public class CircleImage extends ImageView {

    private boolean isCircle = false;

    public CircleImage(Context context) {
        super(context);
    }

    public CircleImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleImage);
        isCircle = array.getBoolean(R.styleable.CircleImage_is_check, false);


    }

    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isCircle) {

            //在这段代码里面画一个圆
            //获取到src设置的图片
            BitmapDrawable drawable = (BitmapDrawable) getDrawable();
            if (drawable != null) {
                Bitmap bitmap = drawable.getBitmap();
                //自定义的方法 用来获取圆形的bitmap 图片
                Bitmap circleBitmap = getCircleBitmap(bitmap);

                Paint paint = new Paint();
                paint.setAntiAlias(true);

                Rect rect = new Rect(0, 0, circleBitmap.getWidth(), circleBitmap.getHeight());
                canvas.drawBitmap(circleBitmap, rect, rect, paint);


            }


        } else {

            super.onDraw(canvas);
        }
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig().ARGB_8888);
        Canvas canves = new Canvas(outBitmap);

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        //获取到宽高
        canves.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getHeight() / 2, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        canves.drawBitmap(bitmap, rect, rect, paint);

        //这里就可以返回一个圆形的bitmap
        return outBitmap;
    }
}
