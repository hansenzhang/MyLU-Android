package com.mylu.main;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.util.Calendar;

/**
 * Created by Hansen on 1/14/14.
 */
public class EventAddActivity extends FragmentActivity implements JSONParser.JSONParserCallback,
        TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{
    //private String mEventName;
    //private String mEventDetails;
    //private String mEventLocation; // Very temporary if we can get location services from maps api
    private EditText mEventName;
    private EditText mEventDetails;
    private EditText mEventLocation;
    private Button mDateSpinner;
    private Button mTimeSpinner;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_item);

        mEventName = (EditText) findViewById(R.id.editText_eventName);
        mEventDetails = (EditText) findViewById(R.id.editText_eventDetails);
        mEventLocation = (EditText) findViewById(R.id.editText_eventLocation);

        mDateSpinner = (Button) findViewById(R.id.spinner_date);
        mTimeSpinner = (Button) findViewById(R.id.spinner_time);
    }

    public void showDatePickerDialog(View view) {
        DialogFragment f = new DatePickerFragment();
        f.show(getFragmentManager(), "Date");
    }

    public void showTimePickerDialog(View view) {
        DialogFragment f = new TimePickerFragment();
        f.show(getFragmentManager(), "Time");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.event_menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_submit:
                //submitForm();
                return true; //TODO: this should throw an error (or alert) if fails...
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void submitForm() {
        JSONParser parser = new JSONParser();
        String url = "http://mylu.herokuapp.com/api/v1/events?";
        //TODO: NUll check all of these fields, throw warnings somehow.
        String title = mEventName.getText().toString();
        String location = mEventLocation.getText().toString();
        String author = "Temp User";
        String email = "temp@lehigh.edu";
        String date = new DateTime(mYear, mMonth, mDay, mHour, mMinute).toDateTimeISO().toString();
        parser.execute(url + title + location + author + email + date); // Execute will run async
        //parser.doInBackground(url);


    }

    @Override
    public void showList(JSONObject result) {

    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mDateSpinner.setText(new DateFormatSymbols().getShortMonths()[month] + " " + day + ", " + year);
        mYear = year;
        mMonth = month;
        mDay = day;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("h:mm a");
        mTimeSpinner.setText(fmt.print(new DateTime(1, 1, 1, hour, minute))); // make am/pm in here somewhere
        mHour = hour;
        mMinute = minute;
    }
}
