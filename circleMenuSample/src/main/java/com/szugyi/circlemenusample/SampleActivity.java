package com.szugyi.circlemenusample;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.szugyi.circlemenu.view.CircleImageView;
import com.szugyi.circlemenu.view.CircleLayout;
import com.szugyi.circlemenu.view.CircleLayout.OnCenterClickListener;
import com.szugyi.circlemenu.view.CircleLayout.OnItemClickListener;
import com.szugyi.circlemenu.view.CircleLayout.OnItemSelectedListener;
import com.szugyi.circlemenu.view.CircleLayout.OnRotationFinishedListener;

import java.text.DecimalFormat;

public class SampleActivity extends Activity implements OnItemSelectedListener,
        OnItemClickListener, OnRotationFinishedListener, OnCenterClickListener {
    public static final String ARG_LAYOUT = "layout";
    RectF rectF;
    private TextView selectedTextView;
    private ImageView ivBackground;
    private TextView tvPlus;
    private TextView tvMinus;
    private double progressValue;
    private double currentPrice;
    private int startProgress;
    private int stopProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view by passed extra
        Bundle extras = getIntent().getExtras();
        int layoutId = extras.getInt(ARG_LAYOUT);

        if (layoutId == R.layout.sample) {
            setContentView(R.layout.sample);


            Button btn1 = (Button) findViewById(android.R.id.button1);
            btn1.setOnTouchListener(new View.OnTouchListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    float num[] = {event.getX(), event.getY()};

                   SeekbarDialog dialog = new SeekbarDialog();
                    dialog.show(getFragmentManager(),"");

//                    SeekbarDialog newFragment = SeekbarDialog.newInstance(num);
//
//
////                    SeekbarDialog.newInstance(num);
//                    newFragment.show(getFragmentManager(), "");


                    return false;
                }
            });
            Button btn2 = (Button) findViewById(android.R.id.button2);
            btn2.setOnTouchListener(new View.OnTouchListener() {
                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    float num[] = {event.getX(), event.getY()};
                    SeekbarDialog newFragment = SeekbarDialog.newInstance(num);


//                    SeekbarDialog.newInstance(num);
//                    newFragment.show(getFragmentManager(), "");


                    return false;
                }
            });


        } else {
            setContentView(R.layout.sample_with_background);


            ru.biovamp.widget.CircleLayout circleLayout = (ru.biovamp.widget.CircleLayout) findViewById(R.id.bg_circle);

//            circleLayout.setInnerRadius(100);
            for (int i = 0; i < 11; i++) {
                View view = new View(this);
                view.setBackgroundColor(getResources().getColor(((i == 4) ? android.R.color.holo_green_dark : android.R.color.black)));
                circleLayout.addView(view);
            }
            // Set listeners
            CircleLayout circleMenu = (CircleLayout) findViewById(R.id.main_circle_layout);
            circleMenu.setOnItemSelectedListener(this);
            circleMenu.setOnItemClickListener(this);
            circleMenu.setOnRotationFinishedListener(this);
            circleMenu.setOnCenterClickListener(this);

            selectedTextView = (TextView) findViewById(R.id.main_selected_textView);
            selectedTextView.setText(((CircleImageView) circleMenu
                    .getSelectedItem()).getName());
        }
        // ATTENTION: This was auto-generated to implement the App Indexingld();
    }

    private String decimalFormatter(double number) {
        DecimalFormat numberFormat = new DecimalFormat("#.00000");
        return numberFormat.format(number);
    }


    @Override
    public void onItemSelected(View view, String name) {
        selectedTextView.setText(name);

        switch (view.getId()) {
            case R.id.main_calendar_image:
                // Handle calendar selection
                break;
            case R.id.main_cloud_image:
                // Handle cloud selection
                break;
            case R.id.main_facebook_image:
                // Handle facebook selection
                break;
//			case R.id.main_key_image:
//				// Handle key selection
//				break;
//			case R.id.main_profile_image:
//				// Handle profile selection
//				break;
            case R.id.main_tap_image:
                // Handle tap selection
                break;
        }
    }

    @Override
    public void onItemClick(View view, String name) {
        Toast.makeText(getApplicationContext(),
                getResources().getString(R.string.start_app) + " " + name,
                Toast.LENGTH_SHORT).show();

        switch (view.getId()) {
            case R.id.main_calendar_image:
                // Handle calendar click
                break;
            case R.id.main_cloud_image:
                // Handle cloud click
                break;
            case R.id.main_facebook_image:
                // Handle facebook click
                break;
//			case R.id.main_key_image:
//				// Handle key click
//				break;
//			case R.id.main_profile_image:
//				// Handle profile click
//				break;
            case R.id.main_tap_image:
                // Handle tap click
                break;
        }
    }

    @Override
    public void onRotationFinished(View view, String name) {
//        Animation animation = new RotateAnimation(0, 360, view.getWidth() / 2,
//                view.getHeight() / 2);
//        animation.setDuration(250);
//        view.startAnimation(animation);
    }

    @Override
    public void onCenterClick() {
        Toast.makeText(getApplicationContext(), R.string.center_click,
                Toast.LENGTH_SHORT).show();
    }


    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }


}
