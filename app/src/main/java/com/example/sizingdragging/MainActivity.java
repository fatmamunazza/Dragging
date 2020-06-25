package com.example.sizingdragging;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et,et1;
    TextView tv1,tv2;
    ImageView imageView;
    LinearLayout iv;
    SeekBar sb;
    RelativeLayout root;
    private int xDelta;
    private int yDelta;
    int windowheight,windowwidth;
    private int i=1,j=1,k=1;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // et=findViewById(R.id.et);
        //sb=findViewById(R.id.seekBar);
      //  imageView=findViewById(R.id.imageView);
        root=findViewById(R.id.root);
        tv1=findViewById(R.id.tv2);
        tv2=findViewById(R.id.tv3);
        iv=findViewById(R.id.iv);
       // et1=findViewById(R.id.et1);




        root.post(new Runnable() {
            @Override
            public void run() {
                windowwidth = root.getWidth();
                windowheight = root.getHeight();
            }
        });

        iv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        // _xDelta and _yDelta record how far inside the view we have touched. These
                        // values are used to compute new margins when the view is moved.
                        xDelta = X - view.getLeft();
                        yDelta = Y - view.getTop();
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        if(X - xDelta<0){
                            lp.leftMargin = view.getLeft();
                        }
                        else{
                            lp.leftMargin = X - xDelta;
                        }
                        if(Y-yDelta<0){
                            lp.topMargin = view.getTop();
                        }
                        else{
                            lp.topMargin = Y - yDelta;
                        }

                        if((lp.leftMargin +  view.getWidth())>windowwidth){
                            lp.rightMargin = view.getRight();
                            lp.leftMargin=view.getLeft();
                        }else{
                            lp.rightMargin = view.getWidth() - lp.leftMargin - windowwidth;
                        }
                        if((lp.topMargin +  view.getHeight())>windowheight){
                            lp.topMargin = view.getTop();
                            lp.bottomMargin=view.getBottom();
                        }else{
                            lp.bottomMargin = view.getHeight() - lp.topMargin - windowheight;
                        }
                        view.setLayoutParams(lp);
                        break;
                }
                return true;
            }
        });

    }

}
