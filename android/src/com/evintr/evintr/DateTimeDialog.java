package com.evintr.evintr;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class DateTimeDialog extends Dialog implements OnDismissListener
{
    Context context;
    public static String timestr = "";
    static String hour, minute;
    static DatePicker date;

    public DateTimeDialog(Context context)
    {
        super(context);
        this.context = context;
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetime);
        
        date = (DatePicker)findViewById(R.id.date);
        TimePicker time = (TimePicker)findViewById(R.id.time);
        
        time.setOnTimeChangedListener(new OnTimeChangedListener()
        {
            public void onTimeChanged(TimePicker arg0, int arg1, int arg2)
            {
                hour = arg1 + "";
                minute = arg2 + "";
                if (minute.length() < 2)
                    minute = "0" + minute;
                if (hour.length() < 2)
                    hour = "0" + hour;
                timestr = months(date.getMonth() + 1) + " " + date.getDayOfMonth() + "," + date.getYear();
                timestr += " " + hour + ":" + minute + ":" + "00";
            }
        });
    }

    public void onDismiss(DialogInterface d)
    {
    }

    public String toString()
    {
        return timestr;
    }
    
    public String months(int m)
    {
        switch(m)
        {
        case 1:
            return "January";
        case 2:
            return "February";
        case 3:
            return "March";
        case 4:
            return "April";
        case 5:
            return "May";
        case 6:
            return "June";
        case 7:
            return "July";
        case 8:
            return "August";
        case 9:
            return "September";
        case 10:
            return "October";
        case 11:
            return "November";
        case 12:
            return "December";
        }
        return "January";
    }
}
