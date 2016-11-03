package asule.primiers.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import asule.primiers.R;
import asule.primiers.bean.PointEntity;


/**
 * Created by lenovo on 2016/11/2.
 */

public class IChartView extends View{

    private TypedArray typedArray;

    //X和Y轴的文字的宽高，颜色，大小
    private int xTextWidth;
    private int xTextHeight;
    private int xTextColor;
    private int xTextSize=30;

    private int yTextWidth;
    private int yTextHeight;
    private int yTextColor;
    private int yTextSize;


    //X和Y轴的宽高，颜色
    private int xWidth;
    private int xHeight;
    private int xColor;

    private int yWidth;
    private int yHeight;
    private int yColor;

    //X轴文字和X轴的纵向距离，Y轴文字和Y轴的横向距离
    private int xTextSpace;
    private int yTextSpace;

    //刻度的宽高和颜色
    private int xDegreeHeight;
    private int xDegreeWidth;
    private int yDegreeHeight;
    private int yDegreeWidth;
    private int xDegreeColor;
    private int yDegreeColor;


    //控件的宽高
    private int mWidth;
    private int mHeight;


    //曲线的颜色
    private int pointColor;
    private int pointStroke;

    //x和y轴的最长文字的宽高
    private int xBigTextWidth;
    private int xBigTextHeight;
    private int yBigTextWidth;
    private int yBigTextHeight;


    private Context mContext;

    private String[] xValues;
    private String[] yValues;
    private List<List<PointEntity>> points;//点的数据

    private int maxYValue;

    public IChartView(Context context) {
        this(context,null);
    }

    public IChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        //TypedValue可以理解是检索属性值和属性数组的容器。检索后必须调用recycle进行回收.
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.IChartView);
        /**
         * getInteger和getInt都可以从属性集中检索integer的值
         * 不同的是getInteger如果发现检索的不是int值，会抛出异常，而getInt会先转化为Integer再进行retrieve。
         */
        try{
            xTextWidth = typedArray.getInt(R.styleable.IChartView_xTextWidth, -1);
            xTextHeight = typedArray.getInt(R.styleable.IChartView_xTextHeight, -1);
            xTextColor = typedArray.getColor(R.styleable.IChartView_xTextColor, context.getResources().getColor(R.color.bg_color));
            xTextSize = typedArray.getDimensionPixelSize(R.styleable.IChartView_xTextSize, 35);
            xWidth = typedArray.getInt(R.styleable.IChartView_xWidth, -1);
            xHeight = typedArray.getInt(R.styleable.IChartView_xHeight, -1);
            xColor = typedArray.getColor(R.styleable.IChartView_xColor, context.getResources().getColor(R.color.bg_color));
            xTextSpace = typedArray.getInt(R.styleable.IChartView_xTextSpace, -1);
            xDegreeWidth = typedArray.getInt(R.styleable.IChartView_xDegreeWidth, -1);
            xDegreeHeight = typedArray.getInt(R.styleable.IChartView_xDegreeHeight, -1);
            xDegreeColor = typedArray.getColor(R.styleable.IChartView_xDegreeColor, context.getResources().getColor(R.color.bg_color));

            yTextWidth = typedArray.getInt(R.styleable.IChartView_yTextWidth, -1);
            yTextHeight = typedArray.getInt(R.styleable.IChartView_yTextHeight, -1);
            yTextColor = typedArray.getColor(R.styleable.IChartView_yTextColor, context.getResources().getColor(R.color.bg_color));
            yTextSize = typedArray.getDimensionPixelSize(R.styleable.IChartView_yTextSize, 35);
            yWidth = typedArray.getInt(R.styleable.IChartView_yWidth, -1);
            yHeight = typedArray.getInt(R.styleable.IChartView_yHeight, -1);
            yColor = typedArray.getColor(R.styleable.IChartView_yColor, context.getResources().getColor(R.color.bg_color));
            yTextSpace = typedArray.getInt(R.styleable.IChartView_yTextSpace, -1);
            yDegreeWidth = typedArray.getInt(R.styleable.IChartView_yDegreeWidth, -1);
            yDegreeHeight = typedArray.getInt(R.styleable.IChartView_yDegreeHeight, -1);
            yDegreeColor = typedArray.getColor(R.styleable.IChartView_yDegreeColor, context.getResources().getColor(R.color.bg_color));

            pointColor = typedArray.getColor(R.styleable.IChartView_pointColor, context.getResources().getColor(R.color.bg_color));
            pointStroke = typedArray.getInt(R.styleable.IChartView_pointStroke, -1);
        }finally {
            typedArray.recycle();
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算出最长的文字
        String xBigText = getMaxText(xValues);
        xBigTextWidth = getTextWidth(xBigText);
        xBigTextHeight = getTextHeight(xBigText);

        String yBigText = getMaxText(yValues);
        yBigTextWidth = getTextWidth(yBigText);
        yBigTextHeight = getTextHeight(yBigText);

        drawX(canvas);
        drawY(canvas);
        drawPoint(canvas);
    }


    private int[] colors= new int[]{R.color.city1,R.color.city2,R.color.city3,R.color.city0};

    //画曲线
    private void drawPoint(Canvas canvas) {
        if (points==null||points.size()==0){
            return;
        }
        //求出x和y轴的实际长度，所以这里减去最大文字宽度的2/1
        int width = mWidth - yTextSpace - yBigTextWidth-xBigTextWidth/2;
        int height=mHeight-xTextSpace-xBigTextHeight-yBigTextHeight/2;
        //x轴上每段的距离
        int xSpace = width / (xValues.length - 1);

        //y轴的最大值，pixel为每单位值对应的长度
        float maxY=maxYValue;
        float pixel=height/maxY;

        for (int i = 0; i <points.size(); i++) {
            List<PointEntity> pointEntities = points.get(i);
            for (int j = 0; j <pointEntities.size()-1; j++) {
                PointEntity entityq = pointEntities.get(j);
                PointEntity entityh = pointEntities.get(j + 1);

                Paint paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setStrokeWidth(entityq.getPointStroke());
                paint.setColor(getResources().getColor(colors[i]));

                //之所以加上yBigTextHeight/2，是因为从上往下在draw
                canvas.drawLine(
                        yBigTextWidth+yTextSpace+xSpace*j,
                        height-(entityq.getPointValue()*pixel)+yBigTextHeight/2,
                        yBigTextWidth+yTextSpace+xSpace*(j+1),
                        height-(entityh.getPointValue()*pixel)+yBigTextHeight/2,
                        paint);
            }
        }
    }


    private void drawY(Canvas canvas) {
        if (yValues==null||yValues.length==0){
            return;
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        //得出y轴线的高，控件的高减去x轴上文字的高和x轴的文字和轴线的间距
        int height=mHeight-xBigTextHeight-xTextSpace;

        //Y轴上的原点不需要有数值，一个刻度对应一个数值
        int space=height/(yValues.length);

        //开始画刻度，startX，stopX不变，为Y轴最大文字的宽度加文字距离Y轴的间距
        //从文字的一半开始进行绘制刻度，是从上往下绘制
        paint.setStrokeWidth(yDegreeHeight);
        paint.setColor(yDegreeColor);
        for (int i=0; i <yValues.length; i++) {
                canvas.drawLine(
                        yBigTextWidth+yTextSpace,
                        yBigTextHeight/2+space*i,
                        yBigTextWidth+yTextSpace+yDegreeWidth,
                        yBigTextHeight/2+space*i,
                        paint);
        }

        //画Y轴的线,从最大文字的高度1/2到最底部
        paint.setStrokeWidth(yHeight);
        paint.setColor(yColor);
        canvas.drawLine(yBigTextWidth+yTextSpace,
                0+yBigTextHeight/2,
                yBigTextWidth+yTextSpace,
                mHeight-xBigTextHeight-xTextSpace,
                paint);


        //drawText画文字的x,y的坐标是文字底部的坐标，而不是左上角，y的坐标其实是文字的基线，可以理解为左下角
        //y轴的点是文字的底部，所以要让文字居中，基线的位置应该是文字最大的高度
        //所以这里加的是最大的文字高度
        //画Y轴上的文字
        paint.setTextSize(yTextSize);
        paint.setColor(yTextColor);
        for (int i = 0; i <yValues.length; i++) {
            String yValue = yValues[i];
            canvas.drawText(yValue,0,space*i+yBigTextHeight,paint);
        }
    }

    private void drawX(Canvas canvas) {
        if (xValues==null||xValues.length==0){
            return;
        }
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        //得出x轴线的宽。控件的宽-y轴最大文字的宽-y轴文字距离y轴的横向距离
        int width=mWidth-yBigTextWidth-yTextSpace-(xBigTextWidth/2);
        //每个刻度之间的间距
        int space=width/(xValues.length-1);


       //开始画刻度
        paint.setStrokeWidth(xDegreeWidth);
        paint.setColor(xDegreeColor);
        //startX需要加上y轴最大文字的宽和y轴文字距离y轴的横向距离，而startY-stopY相差的值就是刻度的高
        for (int i = 0; i <xValues.length; i++) {
            if (i!=0){
                canvas.drawLine(space*i+(yBigTextWidth+yTextSpace),
                        mHeight-xBigTextHeight-xTextSpace,
                        space*i+(yBigTextWidth+yTextSpace),
                        mHeight-xBigTextHeight-xTextSpace-xDegreeHeight,
                        paint);
            }
        }

        //画X轴的线
        paint.setStrokeWidth(xHeight);
        paint.setColor(xColor);
        //y轴相关的坐标不变
        canvas.drawLine(yBigTextWidth+yTextSpace,
                mHeight-xBigTextHeight-xTextSpace,
                mWidth-(xBigTextWidth/2),
                mHeight-xBigTextHeight-xTextSpace,
                paint);


        //画X轴上的文字
        paint.setTextSize(xTextSize);
        paint.setColor(xTextColor);
        for (int i = 0; i <xValues.length; i++) {
            //画文字的中心点
            int textPoint = i*space + (yTextSpace + yBigTextWidth);
//          //得到每个文字的宽度
            int textWidth = getTextWidth(xValues[i]);
            int realPoint=textPoint-textWidth/2;
            canvas.drawText(xValues[i],realPoint,mHeight,paint);
        }
    }


    //设置X轴的数组
    public void setxValues(String[] xValues) {
        this.xValues = xValues;
        invalidate();
    }

    //设置Y轴的最大值
    public void setyValues(int maxYValue) {
        this.maxYValue=maxYValue;
        int middleValue=0;
        if (maxYValue%2==0){
            middleValue=maxYValue/2;
        }else{
            middleValue=(maxYValue+1)/2;
        }
        yValues=new String[2];
        yValues[0]=maxYValue+"";
        yValues[1]=middleValue+"";
        invalidate();
    }

    public void setPoints(List<List<PointEntity>> points) {
        this.points = points;
        invalidate();
    }

    private String getMaxText(String[] texts) {
        int maxLength=0;
        for (int i = 0; i <texts.length; i++) {
            if (texts[maxLength].length()<texts[i].length()){
                maxLength=i;
            }
        }
        return texts[maxLength];
    }

    //Paint将文字区域进行rect矩形绘制，通过计算矩形区域的宽来知道文字的宽度
    private int getTextWidth(String bigText) {
        Paint paint = new Paint();
        paint.setTextSize(xTextSize);
        Rect rect = new Rect();
        paint.getTextBounds(bigText,0,bigText.length(),rect);
        int bigTextWidth = rect.width();
        return bigTextWidth;
    }

    private int getTextHeight(String bigText) {
        Paint paint = new Paint();
        paint.setTextSize(xTextSize);
        Rect rect = new Rect();
        paint.getTextBounds(bigText,0,bigText.length(),rect);
        int bigTextHeight = rect.height();
        return bigTextHeight;
    }
}
