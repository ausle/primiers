package asule.primiers.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

import asule.primiers.R;

/**
 * Created by lenovo on 2016/11/7.
 */
public class IClockView extends View{

    private Context mContext;
    private int mWidth;



    //钟表最外层圆的颜色，厚度，半径
    private int circleColor;
    private int circleStrokeWidth;
    private int circleWidth;

    //刻度的颜色，宽高，半径
    private int degreeAngle=30;
    private int degreeStrokeWidth;
    private int degreeStrokeHeight;
    private int degreeStrokeColor;

    //圆心的宽度
    private int fillPointWidth;
    //文字大小
    private int textSize;
    //时分秒的厚度
    private int clockThickness;

    public IClockView(Context context) {
        this(context,null);
    }

    public IClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        init();
    }

    private void init() {
        circleColor=mContext.getResources().getColor(R.color.group1);
        circleStrokeWidth=5;
        degreeStrokeWidth=5;
        degreeStrokeHeight=10;
        degreeStrokeColor=mContext.getResources().getColor(R.color.group2);
        fillPointWidth=10;
        textSize=mContext.getResources().getDimensionPixelSize(R.dimen.text_size_nor);
        clockThickness=5;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        circleWidth = (mWidth)/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawStorke(canvas);
        drawClock(canvas);
        drawClockText(canvas);
        handler.sendEmptyMessageDelayed(0,1000);
    }

    private void drawClockText(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);


        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.setColor(getResources().getColor(R.color.text_color_hint));

        String clockText=pad(hour)+":"+pad(minute)+":"+pad(second);
        //要和屏幕的实心点有间距
        canvas.drawText(clockText,circleWidth-getTextWidth(clockText)/2,10+circleWidth+getTextHeight(clockText)+fillPointWidth,paint);
    }


    private int getTextWidth(String bigText) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        Rect rect = new Rect();
        paint.getTextBounds(bigText,0,bigText.length(),rect);
        int bigTextWidth = rect.width();
        return bigTextWidth;
    }

    private int getTextHeight(String bigText) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        Rect rect = new Rect();
        paint.getTextBounds(bigText,0,bigText.length(),rect);
        int bigTextHeight = rect.height();
        return bigTextHeight;
    }


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };

    private void drawClock(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(circleWidth,circleWidth,fillPointWidth,paint);//画实心点

        //各种指针的厚度
        paint.setStrokeWidth(clockThickness);

        //根据当前时间画时分秒
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        //画时针
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        int perHour=hour % 12;
        canvas.save();//保留画布的坐标状态
        //时针的旋转角度和分钟也有关联
        canvas.rotate(perHour*degreeAngle+minute*degreeAngle/60,circleWidth,circleWidth);
        canvas.drawLine(circleWidth,
                    degreeStrokeHeight+20+5+10,
                    circleWidth,
                    circleWidth,
                    paint
        );
        canvas.restore();//会重置，rotate会恢复到save之前的状态。所以要在每次rotate之前进行save。要重新进行旋转


        paint.setColor(getResources().getColor(R.color.colorAccent));
        int perMinute=minute*360/60;
        canvas.save();
        canvas.rotate(perMinute,circleWidth,circleWidth);
        canvas.drawLine(circleWidth,
                degreeStrokeHeight+10+5+10,
                circleWidth,
                circleWidth,
                paint
        );
        canvas.restore();

        paint.setColor(getResources().getColor(R.color.dsc_color));
        int perSecond=second*360/60;
        canvas.save();
        canvas.rotate(perSecond,circleWidth,circleWidth);
        canvas.drawLine(circleWidth,
                degreeStrokeHeight+5+10,
                circleWidth,
                circleWidth,
                paint
        );
        canvas.restore();
    }

    private void drawStorke(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(degreeStrokeWidth);
        paint.setColor(degreeStrokeColor);
        paint.setStyle(Paint.Style.STROKE);
        int drawCount=360/degreeAngle;
        for (int i = 0; i <drawCount; i++) {
            if (i%3==0){
                canvas.drawLine(circleWidth,0,circleWidth,degreeStrokeHeight+10,paint);
            }else{
                canvas.drawLine(circleWidth,0,circleWidth,degreeStrokeHeight,paint);
            }
            canvas.rotate(degreeAngle,circleWidth,circleWidth);
        }
    }

    private void drawCircle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(circleStrokeWidth);
        paint.setColor(circleColor);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(circleWidth,circleWidth,circleWidth,paint);
    }

    private String pad(int c) {
        if (c>=10)
            return String.valueOf(c);
        else
            return "0"+String.valueOf(c);
    }
}
