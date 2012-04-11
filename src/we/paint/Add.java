package we.paint;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class Add extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		
		// Circle
		final ImageView circleView = (ImageView) findViewById(R.id.circlePic);
		circleView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	if (event.getAction() == MotionEvent.ACTION_DOWN){
                    circleView.setImageResource(R.drawable.circle_pressed);
                    sendShape("Circle");
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                	circleView.setImageResource(R.drawable.circle);
                }
                return true;
            }
        });
		
		// Square
		final ImageView squareView = (ImageView) findViewById(R.id.squarePic);
		squareView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	if (event.getAction() == MotionEvent.ACTION_DOWN){
                    squareView.setImageResource(R.drawable.square_pressed);
                    sendShape("Square");
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                	squareView.setImageResource(R.drawable.square);
                }
                return true;
            }
        });

		// Ellipse
		final ImageView ellipseView = (ImageView) findViewById(R.id.ellipsePic);
		ellipseView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	if (event.getAction() == MotionEvent.ACTION_DOWN){
                    ellipseView.setImageResource(R.drawable.ellipse_pressed);
                    sendShape("Ellipse");
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                	ellipseView.setImageResource(R.drawable.ellipse);
                }
                return true;
            }
        });
		
		// Rectangle
		final ImageView rectangleView = (ImageView) findViewById(R.id.rectanglePic);
		rectangleView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	if (event.getAction() == MotionEvent.ACTION_DOWN){
                    rectangleView.setImageResource(R.drawable.rectangle_pressed);
                    sendShape("Rectangle");
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                	rectangleView.setImageResource(R.drawable.rectangle);
                }
                return true;
            }
        });
		
		// Triangle
		final ImageView triangleView = (ImageView) findViewById(R.id.trianglePic);
		triangleView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	if (event.getAction() == MotionEvent.ACTION_DOWN){
                    triangleView.setImageResource(R.drawable.triangle_pressed);
                    sendShape("Triangle");
                } else if(event.getAction() == MotionEvent.ACTION_UP){
                	triangleView.setImageResource(R.drawable.triangle);
                }
                return true;
            }
        });
	}
	
	private void sendShape(String shape){
		Communicator.minyDriver.message(shape+"");
	}
}
