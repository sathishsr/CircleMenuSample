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
                    SeekbarDialog newFragment = SeekbarDialog.newInstance(num);


//                    SeekbarDialog.newInstance(num);
                    newFragment.show(getFragmentManager(), "");


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
                    newFragment.show(getFragmentManager(), "");


                    return false;
                }
            });

//            final CircularSeekBar circularSeekBar = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
//            final TextView progressUpdate = (TextView) findViewById(R.id.main_selected_textView);
//            tvPlus = (TextView) findViewById(R.id.tvPlus);
//            tvMinus = (TextView) findViewById(R.id.tvMinus);
//
//            setCurrentPrice(1.20691);
//            progressValue = getCurrentPrice();
//            circularSeekBar.setProgress(50);
//
//            tvPlus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    progressValue = circularSeekBar.getProgress() * 100000;
////                    double output = ((double) progressValue / 100000);
////                    circularSeekBar.setProgress((int) output);
////                    progressUpdate.setText(String.valueOf(output));
//                }
//            });
//
//            tvMinus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    progressValue = circularSeekBar.getProgress() - 100001;
////                    double output = ((double) progressValue / 100000);
////                    circularSeekBar.setProgress((int) output);
////                    progressUpdate.setText(String.valueOf(output));
//                }
//            });
//            progressUpdate.setText(getCurrentPrice() + "");
//
//            circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
//                @Override
//                public void onProgressChanged(CircularSeekBar circularSeekBar, int progress, boolean fromUser) {
//
//                    if(startProgress <progress)
//                        progressValue = getCurrentPrice() - progress;
//                    else
//                        progressValue = getCurrentPrice() + progress;
////                    if (progress == 50) {
////                        progressValue = getCurrentPrice();
////                    }
////                    if (progress > currentProgress && progress < 100) {
////                        progressValue = progressValue - 1;
////                    } else if (progress < currentProgress && progress > 0) {
////                        progressValue = progressValue + 1;
////                    }
////                    Log.e("PROGRESS CHANGED", progressValue + "");
//                    progressUpdate.setText(String.valueOf(decimalFormatter(progressValue)));
//                }
//
//                @Override
//                public void onStopTrackingTouch(CircularSeekBar seekBar) {
//                    stopProgress= seekBar.getProgress();
////                    if (seekBar.getProgress() == 50) {
////                        progressValue = getCurrentPrice();
////                    }
////                    if (seekBar.getProgress() > currentProgress && seekBar.getProgress() < 100) {
////                        progressValue = progressValue - 1;
////                    } else if (seekBar.getProgress() < currentProgress && seekBar.getProgress() > 0) {
////                        progressValue = progressValue + 1;
////                    }
//                    Log.e("STOP", stopProgress + "");
////                    progressUpdate.setText(String.valueOf(decimalFormatter(progressValue)));
//                }
//
//                @Override
//                public void onStartTrackingTouch(CircularSeekBar seekBar) {
//                    startProgress = seekBar.getProgress();
////                    if (seekBar.getProgress() == 50) {
////                        progressValue = getCurrentPrice();
////                    }
////                    if (seekBar.getProgress() > currentProgress && seekBar.getProgress() < 100) {
////                        progressValue = progressValue - 1;
////                    } else if (seekBar.getProgress() < currentProgress && seekBar.getProgress() > 0) {
////                        progressValue = progressValue + 1;
////                    }
//                    Log.e("START", startProgress + "");
////                    progressUpdate.setText(String.valueOf(decimalFormatter(progressValue)));
//                }
//            });

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
