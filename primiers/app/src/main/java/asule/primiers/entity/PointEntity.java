package asule.primiers.entity;

/**
 * Created by lenovo on 2016/11/3.
 */

public class PointEntity {

    private int pointColor;
    private int pointValue;
    private int pointStroke;

    public int getPointStroke() {
        return pointStroke;
    }

    public void setPointStroke(int pointStroke) {
        this.pointStroke = pointStroke;
    }

    public int getPointColor() {
        return pointColor;
    }

    public void setPointColor(int pointColor) {
        this.pointColor = pointColor;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    public PointEntity(int pointColor, int pointValue, int pointStroke) {
        this.pointColor = pointColor;
        this.pointValue = pointValue;
        this.pointStroke = pointStroke;
    }

    public PointEntity(int pointValue, int pointStroke) {
        this.pointValue = pointValue;
        this.pointStroke = pointStroke;
    }
}
