package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        int top = 50;
        int left = 100;
        int maxHeight = 600;
        int maxWidth = 0;
        int gap = 30;

        int rectWidth = 100;
        int rectLeft = 0;
        int rectTop = 0;
        int rectRight = 0;
        int rectBottom = 0;

        //画线
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawLine(left, top, left, top + maxHeight, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xff72B916);

        String[] strArray = new String[]{"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.WHITE);   // 绘画字体的颜色
        paint2.setTextSize(24);
        int[] percentArray = new int[]{0, 6, 6, 63, 95, 127, 63};
        int[] array = new int[]{0, 20, 20, 200, 300, 400, 200};
        rectRight = left;
        for (int i = 0; i < array.length; ++i) {
            rectLeft = rectRight + gap;
            rectTop = (top + maxHeight - array[i]);
            rectRight = rectLeft + rectWidth;
            rectBottom = (top + maxHeight);
            canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, paint);
            Rect bounds = new Rect();
            String text = strArray[i];
            paint2.getTextBounds(text, 0, text.length(), bounds);
            canvas.drawText(text, rectLeft + (rectWidth - bounds.width()) / 2
                    , (rectBottom + bounds.height() / 2 + 10), paint2);

            String text2 = array[i] * 100 / 1140 + "%";
            paint2.getTextBounds(text2, 0, text2.length(), bounds);
            canvas.drawText(text2, rectLeft + (rectWidth - bounds.width()) / 2
                    , (rectTop - bounds.height() / 2 + 10), paint2);
        }
        paint.setColor(Color.WHITE);
        canvas.drawLine(left, top + maxHeight, rectRight + 100, top + maxHeight, paint);
    }
}
