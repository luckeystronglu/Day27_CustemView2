package com.qf.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by Ken on 2016/9/22.9:22
 */
public class NumberImageView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener {

    private int radiu;
    private float fl = 0.18f;
    private String number = "20";

    private Paint paint, txtPaint;

    public NumberImageView(Context context) {
        this(context, null);
    }

    public NumberImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        txtPaint = new Paint();
        txtPaint.setAntiAlias(true);
        txtPaint.setColor(Color.WHITE);
        txtPaint.setTextSize(30);
        txtPaint.setStyle(Paint.Style.STROKE);
        txtPaint.setStrokeWidth(3);

        /**
         * 在控件加载完成以后调用run方法
         */
        this.post(new Runnable() {
            @Override
            public void run() {
                Log.d("print", "------->post:" + getWidth() + "   " + getHeight());
            }
        });
    }

    /**
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d("print", "------->onSizeChanged:" + getWidth() + "   " + getHeight());
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //当视图树发生改变的时候，就会回调监听
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        Log.d("print", "------->onGlobalLayout:" + getWidth() + "   " + getHeight());
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int vWidth = getWidth();
        int vHeight = getHeight();
        radiu = (int) (vWidth * fl);
        txtPaint.setTextSize(vWidth * (1f / 6f));

        Drawable drawable = getDrawable();
        if(drawable != null && drawable instanceof BitmapDrawable){
            //获得ImageView中的位图资源
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            int bWidth = bitmap.getWidth();
            int bHeight = bitmap.getHeight();

            Rect rect1 = new Rect(0, 0, bWidth, bHeight);
            Rect rect2 = new Rect(0, radiu, vWidth - radiu, vHeight);
            canvas.drawBitmap(bitmap, rect1, rect2, null);

            canvas.drawCircle(vWidth - radiu, radiu, radiu, paint);

            canvas.drawText(number,
                    vWidth - radiu - txtPaint.measureText(number)/2,
                    radiu + (txtPaint.descent() - txtPaint.ascent())/2 - txtPaint.descent(),
                    txtPaint);
        } else {
            super.onDraw(canvas);
        }
    }

    public void setNumber(int number){
        if(number > 99){
            this.number = "99+";
        } else {
            this.number = number + "";
        }
        invalidate();
    }


}
