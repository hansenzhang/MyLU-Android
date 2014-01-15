package com.mylu.main;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Hansen on 1/14/14.
 */
public class AddEvent extends FragmentActivity {
    private String mEventName;
    private String mEventDetails;
    private String mEventLocation; // Very temporary if we can get location services from maps api
    private Spinner mDateSpinner;
    private Spinner mTimeSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_item);

        mDateSpinner = (Spinner) findViewById(R.id.spinner_date);
        mTimeSpinner = (Spinner) findViewById(R.id.spinner_time);

        View.OnTouchListener Spinner_OnTouchDate = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    showDatePickerDialog(view);
                }
                return true;
            }
        };

        View.OnKeyListener Spinner_OnKeyDate = new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                    showDatePickerDialog(view);
                    return true;
                } else {
                    return false;
                }
            }
        };

        View.OnTouchListener Spinner_OnTouchTime = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    showTimePickerDialog(view);
                }
                return true;
            }
        };

        View.OnKeyListener Spinner_OnKeyTime = new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                    showTimePickerDialog(view);
                    return true;
                } else {
                    return false;
                }
            }
        };

        mDateSpinner.setOnTouchListener(Spinner_OnTouchDate);
        mDateSpinner.setOnKeyListener(Spinner_OnKeyDate);

        mTimeSpinner.setOnTouchListener(Spinner_OnTouchTime);
        mTimeSpinner.setOnKeyListener(Spinner_OnKeyTime);
    }

    private void showDatePickerDialog(View view) {
        DialogFragment f = new DatePickerFragment();
        f.show(getFragmentManager(), "Date");
    }

    private void showTimePickerDialog(View view) {
        DialogFragment f = new TimePickerFragment();
        f.show(getFragmentManager(), "Time");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.event_menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
