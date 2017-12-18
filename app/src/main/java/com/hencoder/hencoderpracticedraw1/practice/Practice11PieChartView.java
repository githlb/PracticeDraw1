package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice11PieChartView extends View {

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        int[] percentArray = new int[]{0, 6, 6, 63, 95, 127, 63};
        int[] colorArray = new int[]{0x00ffffff, 0xff9C27B0, 0xff9E9E9E, 0xff009688, 0xff2196F3, 0xffF44336, 0xffFFC107};
        String[] strArray = new String[]{"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};

        int left = 200;
        int top = 200;
        int right = 700;
        int bottom = 700;

        int radius = 250;
        int x0 = left + radius;
        int y0 = top + radius;

        int startAngle = 0;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < percentArray.length; ++i) {
            paint.setColor(colorArray[i]);
            int margin = 0;
            if (5 == i) {
                margin = 15;
                canvas.drawArc(left - margin, top - margin, right - margin, bottom - margin, startAngle, percentArray[i], true, paint);
            } else {
                canvas.drawArc(left, top, right, bottom, startAngle, percentArray[i] - 2, true, paint);
            }
            int angle = ((startAngle + percentArray[i] / 2)) % 360;
            double x = x0 - margin + radius * Math.cos(angle * 3.14 / 180);
            double y = y0 - margin + radius * Math.sin(angle * 3.14 / 180);

            int targetX = 0;
            boolean isLeft = false;
            if (angle > 90 && angle < 270) {//在左边
                targetX = (int) x - 100;
                isLeft = true;
            } else {//在右边
                targetX = (int) x + 100;
            }
            paint.setColor(Color.WHITE);
            canvas.drawLine((float) x, (float) y, targetX, (float) y, paint);

            Paint txtPaint = new Paint();
            txtPaint.setColor(Color.WHITE);
            txtPaint.setTextSize(22);

            String txt = strArray[i];
            Rect bounds = new Rect();
            txtPaint.getTextBounds(txt, 0, txt.length(), bounds);

            canvas.drawText(strArray[i], (isLeft ? targetX - bounds.width() - 10 : targetX + 10), (float) y + bounds.height() / 2, txtPaint);
            startAngle += percentArray[i];
        }
    }
}
