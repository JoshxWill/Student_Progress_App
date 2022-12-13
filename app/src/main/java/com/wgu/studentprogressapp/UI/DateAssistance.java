package com.wgu.studentprogressapp.UI;


import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.view.View;

public class DateAssistance {
    DatePickerDialog datePickerDialog;

    public static String getDateToday(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return month + "/" + day + "/" + year;
    }

    public static String createDateString(int day, int month, int year){
        return month + "/" + day + "/" + year;
    }

    public void datePicker(View view){
        datePickerDialog.show();
    }
}
