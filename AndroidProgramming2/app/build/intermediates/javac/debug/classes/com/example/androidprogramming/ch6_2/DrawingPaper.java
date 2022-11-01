package com.example.android;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawingPaper extends View {

    private ArrayList<Path> pathList = new ArrayList<Path>();
    private ArrayList<Paint> paintList = new ArrayList<Paint>(); // 그림을 그리는 히스토리를 저장하기 위한 리스트 정의
    private int currentPath = 0;
    private int currentPaint = 0;
    private int currentColor = Color.RED;
    private float currentStrokeWidth = 10f;

    public DrawingPaper(Context context) { // onTouchEvent 이벤트 발생시 새로운 선과 페인트를 리스트에 추가
        super(context);
        initPaper();
    }

    public void initPaper() {
        pathList.add(new Path());
        paintList.add(new Paint());
        paintList.get(currentPaint).setAntiAlias(true);
        paintList.get(currentPaint).setColor(currentColor);
        paintList.get(currentPaint).setStrokeWidth(currentStrokeWidth);
        paintList.get(currentPaint).setStyle(Paint.Style.STROKE);
        paintList.get(currentPaint).setStrokeJoin(Paint.Join.ROUND);
    }

    public void resetPaper() {
        pathList.clear();
        paintList.clear();
        currentPath = 0;
        currentPaint = 0;

        initPaper();
        invalidate(); // 새로운 선이 추가될 경우 화면을 다시 그리는 invalidate(); 메소드를 호출
    }

    public void setEraser() {
        pathList.add(new Path());
        paintList.add(new Paint());
        currentPath++;
        currentPaint++;
        paintList.get(currentPaint).setAntiAlias(true);
        paintList.get(currentPaint).setColor(Color.WHITE);
        paintList.get(currentPaint).setStyle(Paint.Style.STROKE);
        paintList.get(currentPaint).setStrokeJoin(Paint.Join.ROUND);
        paintList.get(currentPaint).setStrokeWidth(30f);
    }

    public void setPaintColor(int color) {
        paintList.add(new Paint());
        pathList.add(new Path());
        currentPath++;
        currentPaint++;
        currentColor = color;
        paintList.get(currentPaint).setColor(color);
        paintList.get(currentPaint).setAntiAlias(true);
        paintList.get(currentPaint).setStyle(Paint.Style.STROKE);
        paintList.get(currentPaint).setStrokeJoin(Paint.Join.ROUND);
        paintList.get(currentPaint).setStrokeWidth(currentStrokeWidth);
    }

    public void setPaintStrokeWidth(float size) {
        // 선의 스타일, 지우개 설정, 선의 굵기와 같은 페인트 스타일을 지정하기 위한 메소드를 정의
        paintList.add(new Paint());
        pathList.add(new Path());
        currentPath++;
        currentPaint++;

        paintList.get(currentPaint).setColor(currentColor);
        paintList.get(currentPaint).setAntiAlias(true);
        paintList.get(currentPaint).setStyle(Paint.Style.STROKE);
        paintList.get(currentPaint).setStrokeJoin(Paint.Join.ROUND);
        paintList.get(currentPaint).setStrokeWidth(size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //pathList 데이터를 기반으로 도화지 화면을 그리는 코드를 추가
        int i;
        for (i=0; i<pathList.size(); i++){
            canvas.drawPath(pathList.get(i), paintList.get(i));
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pathList.get(currentPath).moveTo(x, y);
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                pathList.get(currentPath).lineTo(x, y);
                invalidate();
            case MotionEvent.ACTION_UP:
                break;
            default:
                return true;
        }

        invalidate();
        return true;
    }
}
