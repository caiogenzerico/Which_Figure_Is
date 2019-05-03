package com.example.caiogenzerico.com.br.which_figure_is;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.maps.android.PolyUtil;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DoodleView extends View {

    private static final int TOUCH_TOLERANCE = 10;

    private Bitmap bitmap;
    private Canvas canvasBitmap;
    private Paint paintScreen;
    private Paint paintLine;
    private Map<Integer, Path> pathMap = new HashMap<>();
    private Map<Integer, Point> previousPointMap = new HashMap<>();
    private TextView pointTextView;
    private TextView choiceTextView;
    private int square;
    private int circle;
    private int rectangle;
    private int triangle;
    private int point;
    private int round;
    private int alternative;

    private Rect squareObject;
    private Rect rectangleObject;
    private int circleX;
    private int circleY;
    private static final int RADIUS = 300;
    List<LatLng> trian;


    public DoodleView(Context context, AttributeSet set) {
        super(context, set);
        paintScreen = new Paint();
        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setColor(Color.argb(255, 144, 238, 2));
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(10);
        paintLine.setStrokeCap(Paint.Cap.ROUND);
        point = 0;
        round = 0;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (round < 5) {
            pointTextView = (((Activity) getContext()).findViewById(R.id.pointTextView));
            choiceTextView =(((Activity) getContext()).findViewById(R.id.choiceTextView));
            bitmap.eraseColor(Color.WHITE);
            Random random = new Random();
            square = random.nextInt(4);
            circle = proximo(square);
            rectangle = proximo(circle);
            triangle = proximo(rectangle);
            alternative = random.nextInt(4);
            canvas.drawBitmap(bitmap, 0, 0, paintScreen);
            circleImage(canvasBitmap, paintLine, circle);
            squareImage(canvasBitmap, paintLine, square);
            rectangleImage(canvasBitmap, paintLine, rectangle);
            triangleImage(canvasBitmap, paintLine, triangle,600);
            if (alternative == square) {
                choiceTextView.setText(R.string.SquareChoice);
            } else if (alternative == circle) {
                choiceTextView.setText(R.string.CircleChoice);
            } else if (alternative == rectangle) {
                choiceTextView.setText((R.string.RectangleChoice));
            } else if (alternative == triangle) {
                choiceTextView.setText(R.string.triangleChoice);
            }
            round++;
        } else {
            Toast.makeText(getContext(), "Final punctuation: " + pointTextView.getText(), Toast.LENGTH_SHORT).show();
            point = 0;
            pointTextView.setText("Score: " + point);
            round = 0;
            invalidate();
        }

    }

    private int proximo(int x) {
        if (x >= 3)
            return 0;
        return x + 1;
    }
    private void squareImage(Canvas canvasBitmap, Paint paintLine, int pos){
        if(pos==0){
            squareObject = new Rect(getWidth()/4-300, getHeight()/4-300, getWidth()/2-60, getHeight()/2-300);
            canvasBitmap.drawRect(squareObject, paintLine);
        }else if(pos == 1){
            squareObject = new Rect(getWidth()/4*3-300, getHeight()/4-300, getWidth()/4*3+300, getHeight()/2-300);
            canvasBitmap.drawRect(squareObject, paintLine);
        }else if(pos == 2){
            squareObject = new Rect(getWidth()/4-300, getHeight()/2+300, getWidth()/2-60, getHeight()/4*3+300);
            canvasBitmap.drawRect(squareObject, paintLine);
        }else if(pos == 3){
            squareObject = new Rect(getWidth()/4*3-300, getHeight()/2+300, getWidth()/4*3+300, getHeight()/4*3+300);
            canvasBitmap.drawRect(squareObject, paintLine);
        }
    }
    private void circleImage(Canvas canvasBitmap, Paint paintLine, int pos) {
        if (pos == 0) {
            circleX = getWidth() / 4;
            circleY = getHeight() / 4;
            canvasBitmap.drawCircle(circleX, circleY, RADIUS, paintLine);
        } else if (pos == 1) {
            circleX = getWidth() / 4 * 3;
            circleY = getHeight() / 4;
            canvasBitmap.drawCircle(circleX, circleY, RADIUS, paintLine);
        } else if (pos == 2){
            circleX = getWidth() / 4;
            circleY = getHeight() / 4 * 3;
            canvasBitmap.drawCircle(circleX, circleY, RADIUS, paintLine);
        } else if (pos == 3) {
            circleX = getWidth() / 4 * 3;
            circleY = getHeight() / 4 * 3;
            canvasBitmap.drawCircle(circleX, circleY, RADIUS, paintLine);
        }
    }
    private void rectangleImage(Canvas canvasBitmap, Paint paintLine, int pos) {
        if (pos == 0) {
            rectangleObject = new Rect(getWidth() / 4 - 150, getHeight() / 4 - 300, getWidth() / 2 - 210, getHeight() / 2 - 300);
            canvasBitmap.drawRect(rectangleObject, paintLine);
        } else if (pos == 1) {
            rectangleObject = new Rect(getWidth() / 4 * 3 - 150, getHeight() / 4 - 300, getWidth() / 4 * 3 + 150, getHeight() / 2 - 300);
            canvasBitmap.drawRect(rectangleObject, paintLine);
        } else if (pos == 2){
            rectangleObject = new Rect(getWidth() / 4 - 150, getHeight() / 2 + 300, getWidth() / 2 - 210, getHeight() / 4 * 3 + 300);
        canvasBitmap.drawRect(rectangleObject, paintLine);
        }
        else if(pos==3) {
            rectangleObject = new Rect(getWidth() / 4 * 3 - 150, getHeight() / 2 + 300, getWidth() / 4 * 3 + 150, getHeight() / 4 * 3 + 300);
            canvasBitmap.drawRect(rectangleObject, paintLine);
        }
    }
    private void triangleImage(Canvas canvas, Paint paint, int pos, int width){
        int x = 0;
        int y = 0;
        if(pos==0) {
            x = getWidth() / 4;
            y = getHeight() / 4;
        }else if(pos==1) {
            x = getWidth() / 4 * 3;
            y = getHeight() / 4;
        }else if(pos ==2) {
            x = getWidth() / 4;
            y = getHeight() / 4 * 3;
        }else if(pos == 3) {
            x = getWidth() / 4 * 3;
            y = getHeight() / 4 * 3;
        }


        int halfWidth = width / 2;

        trian = new ArrayList<>();
        trian.add(new LatLng(x/100, (y-halfWidth)/100));
        trian.add(new LatLng((x -halfWidth)/100, (y + halfWidth)/100));
        trian.add(new LatLng((x + halfWidth)/100, (y + halfWidth)/100));

        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();

        canvas.drawPath(path, paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvasBitmap = new Canvas(bitmap);
        bitmap.eraseColor(Color.WHITE);
    }

    public void clear() {
        pathMap.clear();
        previousPointMap.clear();
        bitmap.eraseColor(Color.WHITE);
        invalidate();
    }

    public void setDrawingColor(int color) {
        this.paintLine.setColor(color);
    }

    public int getDrawingColor() {
        return this.paintLine.getColor();
    }

    public void setLineWidth(int width) {
        this.paintLine.setStrokeWidth(width);
    }

    public int getLineWidth() {
        return (int) this.paintLine.getStrokeWidth();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();

        int actionIndex = event.getActionIndex();

        if (checkPoint(event)) {
            addPoint();
        } else {
            decreasePoint();
        }
        invalidate();
        return false;
    }

    private void addPoint() {
        point++;
        pointTextView.setText("Score: " + point);
    }

    private void decreasePoint() {
        if (point != 0) {
            point--;
        }
        pointTextView.setText("Score: " + point);
    }

    private boolean checkPoint(MotionEvent event) {
        if (alternative == square) {
            if (itsSquare(event)) {
                return true;
            }
            return false;
        } else if (alternative == circle) {
            if (itsCircle(event)) {
                return true;
            }
            return false;
        } else if (alternative == triangle) {
            if (itsTriangle(event)) {
                return true;
            }
            return false;
        } else if (alternative == rectangle) {
            if (itsRetangle(event)) {
                return true;
            }
        }
        return false;
    }

    private boolean itsSquare(MotionEvent event){
        if(squareObject.contains((int)event.getX(), (int)event.getY())){
            return true;
        }
        return false;
    }
    private boolean itsCircle(MotionEvent event){
        if(Math.sqrt(Math.pow(event.getX() - circleX, 2) + Math.pow(event.getY() - circleY, 2)) <= RADIUS)
            return true;
        return false;
    }
    private boolean itsTriangle(MotionEvent event){
        float x = event.getX()/100;
        float y = event.getY()/100;
        LatLng point = new LatLng(x, y);
        boolean contains = PolyUtil.containsLocation(point.latitude, point.longitude, trian, true);
        if (contains) {
            return true;
        }
        return false;

    }
    private boolean itsRetangle(MotionEvent event){
        if(rectangleObject.contains((int)event.getX(), (int)event.getY())){
            return true;
        }
        return false;

    }

}