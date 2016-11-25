package asule.primiers.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import asule.primiers.R;

/**
 * Created by lenovo on 2016/11/9.
 */

public class IContactView extends View{

    public static String[] cityChars= { "热门","A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#" };
    private int mWidth;
    private int textSize;
    private int mHeight;
    private int perCharHeight;

    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private int choose = -1;// 选中

    private TextView mTextDialog;
    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }




    public IContactView(Context context) {
        this(context,null);
    }

    public IContactView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        textSize=getResources().getDimensionPixelSize(R.dimen.text_size_nor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        perCharHeight = mHeight / cityChars.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        for (int i = 0; i <cityChars.length; i++) {
            paint.setTextSize(textSize);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLUE);
            float xPs=mWidth/2-getTextWidth(cityChars[i])/2;
            float yPs=perCharHeight;
            //drawtext的基线是底部,所有i为0的时候，yPs为每个文字的高度
            canvas.drawText(cityChars[i],xPs,yPs*(i+1),paint);
            paint.reset();
        }
    }

    private int getTextWidth(String bigText) {
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        Rect rect = new Rect();
        paint.getTextBounds(bigText,0,bigText.length(),rect);
        int bigTextWidth = rect.width();
        return bigTextWidth;
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();// 点击y坐标
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() *cityChars.length);
        // 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;//
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                setBackgroundResource(R.drawable.contact_bg);
                if (oldChoose != c) {
                    if (c >= 0 && c <cityChars.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(cityChars[c]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(cityChars[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    /**
     * 向外公开的方法
     */
    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    /**
     * 接口
     */
    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }
}
