package com.szugyi.circlemenusample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by sathishsr on 26/11/15.
 */
public class MyView extends View {
    Context c;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mBitmapPaint;
    private Paint mpaint, paint2;
    private RectF rectF;
    private ImageView ivBackground;

    public MyView(Context context) {
        this(context, null);
        c = context;
        mpaint = new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setStyle(Paint.Style.FILL);
        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setStrokeWidth(10);
        mBitmapPaint = new Paint();
        mBitmapPaint.setColor(Color.RED);
    }

    /**
     * @param context
     * @param attrs
     */
    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        c = context;
        mpaint = new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setStyle(Paint.Style.FILL);
        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setStrokeWidth(10);
        mBitmapPaint = new Paint();
        mBitmapPaint.setColor(Color.RED);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        c = context;
        mpaint = new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setStyle(Paint.Style.FILL);
        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setStrokeWidth(10);
        mBitmapPaint = new Paint();
        mBitmapPaint.setColor(Color.RED);
    }

    public MyView(Activity context, ImageView ivBackgr) {
        super(context);
        c = context;
        ivBackground = ivBackgr;
        mpaint = new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setStyle(Paint.Style.FILL);
        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setStrokeWidth(10);
        mBitmapPaint = new Paint();
        mBitmapPaint.setColor(Color.RED);
        // TODO Auto-generated constructor stub
    }

    public MyView(Activity context) {
        super(context);
        c = context;
        mpaint = new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setStyle(Paint.Style.FILL);
        paint2 = new Paint();
        paint2.setColor(Color.GREEN);
        paint2.setStrokeWidth(10);
        mBitmapPaint = new Paint();
        mBitmapPaint.setColor(Color.RED);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }


    @Override
    public void onDraw(Canvas canvas) {
        float size = Math.min(getWidth(),getHeight());
        mpaint.setStrokeWidth(size/4);
        mpaint.setStyle(Paint.Style.STROKE);
        final RectF oval = new RectF(0, 0, getWidth(), getHeight());
        oval.inset(size/8,size/8);

        mpaint.setColor(Color.RED);
        Path redPath = new Path();
        redPath.arcTo(oval, 0, 120, true);
        canvas.drawPath(redPath, mpaint);

        mpaint.setColor(Color.GREEN);
        Path greenPath = new Path();
        greenPath.arcTo(oval, 120, 120, true);
        canvas.drawPath(greenPath, mpaint);

        mpaint.setColor(Color.BLUE);
        Path bluePath = new Path();
        bluePath.arcTo(oval, 240, 120, true);
        canvas.drawPath(bluePath, mpaint);

        mpaint.setStrokeWidth(2);
        mpaint.setColor(0xff000000);
        canvas.save();
        for(int i=0;i<360;i+=40){
            canvas.rotate(40,size/2,size/2);
            canvas.drawLine(size*3/4,size/2,size,size/2,mpaint);
        }
        canvas.restore();

        final RectF ovalOuter = new RectF(0, 0, getWidth(), getHeight());
        ovalOuter.inset(1,1);
        canvas.drawOval(ovalOuter,mpaint);

        final RectF ovalInner = new RectF(size/4, size/4, size*3/4,size*3/4);
        canvas.drawOval(ovalInner,mpaint);
    }
//    @Override
//    protected void onDraw(Canvas canvas) {
//
//
//        int startAngle = 0;
//        int numberOfSegments = 11;
//        int sweepAngle = 360 / numberOfSegments;
//        setUpDrawingArea();
//
//
//        for (int i = 0; i < numberOfSegments; i++) {
//
//            // pick a colour that is not the previous colour
//            mpaint.setColor(getColor());
//
//            // Draw arc
//            canvas.drawArc(rectF, startAngle, sweepAngle, true, mpaint);
//
//            // Set variable values
//            startAngle -= sweepAngle;
//        }
//    }

    public int getColor() {
        // generate the random integers for r, g and b value
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        int randomColor = Color.rgb(r, g, b);
        return randomColor;
    }

    private void setUpDrawingArea() {

        // First get the screen dimensions
        Point size = new Point();

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) c
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
//        display.getSize(size);
        int width = metrics.widthPixels/2;
        int height = metrics.heightPixels/2;


        // Set up the padding
        int paddingLeft = (int) c.getResources().getDimension(R.dimen.padding_large);
        int paddingTop = (int) c.getResources().getDimension(R.dimen.padding_large);
        int paddingRight = (int) c.getResources().getDimension(R.dimen.padding_large);
        int paddingBottom = (int) c.getResources().getDimension(R.dimen.padding_large);

        // Then get the left, top, right and bottom Xs and Ys for the rectangle we're going to draw in
        float left = 0 + paddingLeft;
        float top = 0 + paddingTop;
        float right = width - paddingRight;
        float bottom = width - paddingBottom;
//
//        getLeft()+(getRight()-getLeft())/3,
//                getTop()+(getBottom()-getTop())/3,
//                getRight()-(getRight()-getLeft())/3,
//                getBottom()-(getBottom()-getTop())/3
        rectF = new RectF(left, top, right, bottom);
    }
}