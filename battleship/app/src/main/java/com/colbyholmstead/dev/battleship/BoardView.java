package com.colbyholmstead.dev.battleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
//import javax.swing.text.html.ImageView;

public class BoardView extends AppCompatImageView {

  Paint paint;

  public BoardView(Context context, AttributeSet attrs) {
    super(context, attrs);
    paint = new Paint();
    paint.setColor(Color.BLUE);
    paint.setStrokeWidth(5);
    paint.setStyle(Paint.Style.FILL_AND_STROKE);

  }


  @Override
  protected void onDraw(Canvas canvas) {
    int height = getHeight() - 1;
    int width = getWidth() - 1 ;
    int cellWidth = width / 11;
    int cellHeight = height / 11;
//    canvas.drawLine(0, 0, width, 0, paint);
//    canvas.drawLine(0, 0, 0, height, paint);
//    canvas.drawLine(0, height, width, height, paint);
//    canvas.drawLine(width, height, 0, height, paint);
//    canvas.drawLine(width, 0, width, height, paint);


    for (int i = 0; i < 12; i++ ){
      canvas.drawLine(0, (cellHeight * i),   width - 7 , (cellHeight * i)  , paint);
      canvas.drawLine((cellWidth * i), 0,   (cellWidth * i), height - 7 , paint);
    }



  }


}
