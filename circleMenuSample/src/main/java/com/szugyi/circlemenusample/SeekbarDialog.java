package com.szugyi.circlemenusample;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by sathishsr on 11/12/15.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SeekbarDialog extends DialogFragment {

    Button mButton;
    EditText mEditText;
    onSubmitListener mListener;
    String text = "";
    private TextView tvPlus;
    private TextView tvMinus;
    private double progressValue;
    private double currentPrice;
    private int startProgress;
    private int stopProgress;
    float[] mNum;
    private double initialValue;
    private int oldProgress;

    float discrete = 0;
    float start = 0;
    float end = 100;
    float start_pos = 0;
    int start_position = 0;


    public static SeekbarDialog newInstance(float[] num) {
        SeekbarDialog f = new SeekbarDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putFloatArray("position", num);
        f.setArguments(args);

        return f;
    }



    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    interface onSubmitListener {
        void setOnSubmitListener(String arg);
    }

    private String decimalFormatter(double number) {
        DecimalFormat numberFormat = new DecimalFormat("#.00000");
        return numberFormat.format(number);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (manager.findFragmentByTag(tag) == null) {
            super.show(manager, tag);
        }
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//        mNum = getArguments().getFloatArray("position");
        final Dialog dialog = new Dialog(getActivity());
        Window window = dialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        dialog.setContentView(R.layout.seekbar_dailog);
        window.setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));


//        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
//
//        Log.e("Position", mNum[0] + " " + mNum[1]);
////        wmlp.gravity =/* Gravity.CENTER |*/ Gravity.RIGHT;
//        wmlp.x = (int) mNum[0];   //x position
//        wmlp.y = (int) mNum[1];   //y position
//        window.setAttributes(wmlp);

//        dialog.show();


        final CircularSeekBar circularSeekBar = (CircularSeekBar) dialog.findViewById(R.id.circularSeekBar1);
        final TextView progressUpdate = (TextView) dialog.findViewById(R.id.main_selected_textView);
        tvPlus = (TextView) dialog.findViewById(R.id.tvPlus);
        tvMinus = (TextView) dialog.findViewById(R.id.tvMinus);

        setCurrentPrice(1.20691);
        initialValue = getCurrentPrice();
        progressValue = getCurrentPrice();
        circularSeekBar.setProgress(50);

        tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    progressValue = circularSeekBar.getProgress() * 100000;
//                    double output = ((double) progressValue / 100000);
//                    circularSeekBar.setProgress((int) output);
//                    progressUpdate.setText(String.valueOf(output));
            }
        });

        tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    progressValue = circularSeekBar.getProgress() - 100001;
//                    double output = ((double) progressValue / 100000);
//                    circularSeekBar.setProgress((int) output);
//                    progressUpdate.setText(String.valueOf(output));
            }
        });
        progressUpdate.setText(getCurrentPrice() + "");


        start = -10;                              //you need to give starting value of SeekBar
        end = 10;                 //you need to give end value of SeekBar

        circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {
// TODO Auto-generated method stub

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(CircularSeekBar CircularSeekBar, int progress, boolean fromUser) {
// To convert it as discrete value
                float temp = progress;
                float dis = end - start;
                discrete = (start + ((temp / 100) * dis));
                float precision=discrete/10000;
                double forwardValue = getCurrentPrice()-precision;
                progressUpdate.setText(String.valueOf(decimalFormatter(forwardValue)));
            }
        });


//        circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(CircularSeekBar seekBar, int progress, boolean fromUser) {
//
//
//                Log.i("S.Progress===="+startProgress,"Current Progress==="+progress);
//                int updatedProgress = startProgress-progress;
//
//                Log.i("Updated Progress====",updatedProgress+"");
//                if (seekBar.getProgress() == 50) {
//                    progressValue = initialValue;
//                } else if (seekBar.getProgress() > 50 && seekBar.getProgress() < 100) {
//                    if(updatedProgress>0)
//                        progressValue = progressValue + 0.00010;
//                    else if(updatedProgress<0)
//                        progressValue = progressValue - 0.00010;
//
//                } else if (seekBar.getProgress() < 50 && seekBar.getProgress() > 0) {
//                    if(updatedProgress>0)
//                        progressValue = progressValue + 0.00010;
//                    else if(updatedProgress<0)
//                        progressValue = progressValue - 0.00010;
//                }
//
//                if(progress>=100)
//                {
//                    seekBar.setProgress(100);
//                }
//
//
////                if (startProgress < progress)
////                    progressValue = getCurrentPrice() - progress;
////                else
////                    progressValue = getCurrentPrice() + progress;
////                    if (progress == 50) {
////                        progressValue = getCurrentPrice();
////                    }
////                    if (progress > currentProgress && progress < 100) {
////                        progressValue = progressValue - 1;
////                    } else if (progress < currentProgress && progress > 0) {
////                        progressValue = progressValue + 1;
////                    }
////                    Log.e("PROGRESS CHANGED", progressValue + "");
//                Log.e("progress", progressValue + "");
//                progressUpdate.setText(String.valueOf(decimalFormatter(progressValue)));
//            }
//
//            @Override
//            public void onStopTrackingTouch(CircularSeekBar seekBar) {
//                stopProgress = seekBar.getProgress();
////                    if (seekBar.getProgress() == 50) {
////                        progressValue = getCurrentPrice();
////                    }
////                    if (seekBar.getProgress() > currentProgress && seekBar.getProgress() < 100) {
////                        progressValue = progressValue - 1;
////                    } else if (seekBar.getProgress() < currentProgress && seekBar.getProgress() > 0) {
////                        progressValue = progressValue + 1;
////                    }
//
//
////                    progressUpdate.setText(String.valueOf(decimalFormatter(progressValue)));
//            }
//
//            @Override
//            public void onStartTrackingTouch(CircularSeekBar seekBar) {
//                startProgress = seekBar.getProgress();
////                    if (seekBar.getProgress() == 50) {
////                        progressValue = getCurrentPrice();
////                    }
////                    if (seekBar.getProgress() > currentProgress && seekBar.getProgress() < 100) {
////                        progressValue = progressValue - 1;
////                    } else if (seekBar.getProgress() < currentProgress && seekBar.getProgress() > 0) {
////                        progressValue = progressValue + 1;
////                    }
////                    progressUpdate.setText(String.valueOf(decimalFormatter(progressValue)));
//            }
//        });


        progressUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.setOnSubmitListener(mEditText.getText().toString());
                dismiss();
            }
        });
        return dialog;
    }

}
