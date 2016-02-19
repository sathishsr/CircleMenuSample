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
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by SathishSr
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PriceWheelController extends DialogFragment {

    // --Commented out by Inspection (19/2/16 2:30 PM):private EditText mEditText;
    private double currentPrice;
    private float discrete = 0;
    private float start = 0;
    private float end = 100;
    private static String priceTag = "priceTag";

    public static PriceWheelController newInstance(double currentPrice) {
        PriceWheelController f = new PriceWheelController();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putDouble(priceTag, currentPrice);
        f.setArguments(args);

        return f;
    }


    private double getCurrentPrice() {
        return currentPrice;
    }

    private void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

// --Commented out by Inspection START (19/2/16 2:26 PM):
//    interface onSubmitListener {
//        void setOnSubmitListener(String arg);
//    }
// --Commented out by Inspection STOP (19/2/16 2:26 PM)

    private String decimalFormatter(double number) {
        //Should be based on Precision
        DecimalFormat numberFormat = new DecimalFormat("#.000");
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

        double priceValue = getArguments().getDouble(priceTag);
        final Dialog dialog = new Dialog(getActivity());
        Window window = dialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        dialog.setContentView(R.layout.seekbar_dailog);
        window.setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));


        final CircularSeekBar circularSeekBar = (CircularSeekBar) dialog.findViewById(R.id.circularSeekBar1);
        final TextView progressUpdate = (TextView) dialog.findViewById(R.id.main_selected_textView);

        setCurrentPrice(priceValue);
        double initialValue = getCurrentPrice();
        circularSeekBar.setProgress(50);

        progressUpdate.setText(String.valueOf(decimalFormatter(initialValue)));


        start = -10;//you need to give starting value of SeekBar
        end = 10;//you need to give end value of SeekBar

        circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(CircularSeekBar CircularSeekBar, int progress, boolean fromUser) {
                // To convert it as discrete value
                float dis = end - start;
                discrete = (start + (((float) progress / 100) * dis));// 100 -> should be based on precision
                float precision = discrete / 100;
                double forwardValue = getCurrentPrice() - precision;
                progressUpdate.setText(String.valueOf(decimalFormatter(forwardValue)));
            }
        });


        progressUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //mListener.setOnSubmitListener(mEditText.getText().toString());
                dismiss();
            }
        });
        return dialog;
    }

}
