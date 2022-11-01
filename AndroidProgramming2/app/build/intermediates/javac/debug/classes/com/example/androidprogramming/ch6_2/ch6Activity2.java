package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class ch6Activity2 extends AppCompatActivity {

    FrameLayout frame;
    ImageButton poubelleButton, eraserButton;
    ImageButton redButton, greenButton, blueButton;

    DrawingPaper drawingPaper;
    int currentTool = 1; // 1 color, 2 brush
    LinearLayout colorLayout, brushLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawingPaper = new DrawingPaper(this);

        colorLayout = findViewById(R.id.color_list);
        brushLayout = findViewById(R.id.brush_list);

        redButton = findViewById(R.id.red_button);
        greenButton = findViewById(R.id.green_button);
        blueButton = findViewById(R.id.blue_button);

        poubelleButton = findViewById(R.id.poubelle_button);
        eraserButton = findViewById(R.id.eraser_button);
        frame = findViewById(R.id.frame_container);
        frame.addView(drawingPaper);
        setBottomList(currentTool);
    }

    public void setBottomList (int toolNumber) {
        currentTool = toolNumber;

        switch (currentTool) {
            case 1:
                colorLayout.setVisibility(View.VISIBLE);
                brushLayout.setVisibility(View.GONE);
                break;
            case 2:
                colorLayout.setVisibility(View.GONE);
                brushLayout.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void onPoubelleButton (View view) {
        drawingPaper.resetPaper();
    }

    public void onEraserButton (View view) {
        drawingPaper.setEraser();
    }

    public void onColorToolButton(View view) {
        setBottomList(1);
    }

    public void onBrushButton(View view) {
        setBottomList(2);
    }

    public void onBrushSizeButton(View view) {
        switch (view.getId()) {
            case R.id.small_button:
                drawingPaper.setPaintStrokeWidth(10f);
                break;
            case R.id.middle_button:
                drawingPaper.setPaintStrokeWidth(20f);
                break;
            case R.id.big_button:
                drawingPaper.setPaintStrokeWidth(30f);
                break;
        }
    }

    public void onColorButton (View view) {
        switch (view.getId()) {
            case R.id.red_button:
                drawingPaper.setPaintColor(Color.RED);
                break;
            case R.id.green_button:
                drawingPaper.setPaintColor(Color.GREEN);
                break;
            case R.id.blue_button:
                drawingPaper.setPaintColor(Color.BLUE);
                break;
        }
    }
}
