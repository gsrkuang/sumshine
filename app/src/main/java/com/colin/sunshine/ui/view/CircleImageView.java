package com.colin.sunshine.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.Resource;
import com.colin.sunshine.R;

/**
 * Date:2022-01-26
 * Time:11:35
 * author:colin
 * 自定义圆形图片，用于判断用户头像是不是VIP
 */
@SuppressLint("AppCompatCustomView")
public class CircleImageView extends ImageView {

    private static Canvas mCanvas  ;

    //画笔
    private Paint mPaint;
    //圆形图片的半径
    private int mRadius;

    //圆形边框的半径
    private int mRadiusLine;

    //图片的宿放比例
    private float mScale;

    //用户VIP头像
    private boolean vip = false;

     //用java创建时候调用
    public CircleImageView(Context context) {
        super(context);

    }

    //用xml创建时候调用
    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        getAttrs(context, attrs);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        getAttrs(context, attrs);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mRadius = size/2;
        mRadiusLine = mRadius - 5;
        setMeasuredDimension(size,size);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        mPaint = new Paint();

        Drawable drawable = getDrawable();

        if (null != drawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();

            //初始化BitmapShader，传入bitmap对象
            BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            //计算缩放比例
            mScale = (mRadius * 2.0f) / Math.min(bitmap.getHeight(), bitmap.getWidth());

            Matrix matrix = new Matrix();
            matrix.setScale(mScale, mScale);
            bitmapShader.setLocalMatrix(matrix);

            mPaint.setShader(bitmapShader);


            //画圆形，指定好坐标，半径，画笔
            canvas.drawCircle(mRadius, mRadius, mRadiusLine, mPaint);

            if (vip){
                Paint p = new Paint();//这个是画矩形的画笔，方便大家理解这个圆弧
                p.setStyle(Paint.Style.STROKE);
                p.setAntiAlias(true);//取消锯齿
                p.setStrokeWidth(10);
//            p.setStrokeWidth(2.0f);
//                p.setColor(getResources().getColor(R.color.pink));
                p.setColor(getResources().getColor(R.color.green));
                canvas.drawCircle(mRadius, mRadius, mRadiusLine, p);

            }


        } else {
            super.onDraw(canvas);
        }


    }

    /**
     * 得到属性值
     *
     * @param context
     * @param attrs
     */
    private void getAttrs(Context context, AttributeSet attrs) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.UserIcon);
        vip = ta.getBoolean(R.styleable.UserIcon_vip, vip);
//        attr2 = ta.getInt(R.styleable.myViewDefinedAttr_attr2, 0);
        ta.recycle();

    }


    public void setVip(boolean mVip ){
        vip =mVip;
        invalidate();
        requestLayout();
    }


    public boolean isVip() {
        return vip;
    }
}
