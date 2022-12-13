package com.wgu.studentprogressapp.UI;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wgu.studentprogressapp.Database.Repo;
import com.wgu.studentprogressapp.Entities.Assessment;
import com.wgu.studentprogressapp.Entities.Class;
import com.wgu.studentprogressapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Assessment Detail Controller
 *
 * @author Joshua Williams
 */
public class AssessmentDetailController extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    private Button dateBtn;
    private TextView name;
    private Spinner course;
    private Spinner type;

    String id = null;
    ArrayAdapter<String> arrayAdapter;
    Repo repo = new Repo(getApplication());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments_detail);

        originDatePicker();
        dateBtn = findViewById(R.id.DatePickerBtn);
        dateBtn.setText(getCurrentDate());
        Spinner course = findViewById(R.id.courseList);
        List<String> arraySpinner = getAllCourseNames();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        course.setAdapter(arrayAdapter);

        if (getIntent().getStringExtra("id") != null) {
            this.id = getIntent().getStringExtra("id");
            fillItems(id);
        }

        if (getIntent().getStringExtra("classID") != null) {
            try {
                int assessmentCourseID = Integer.parseInt(getIntent().getStringExtra("classID"));
                String assessmentCourse = null;

                int i = 0;
                List<Class> mAllClasses = repo.getAllClasses();
                while (i < mAllClasses.size()) {
                    if (mAllClasses.get(i).getClassID() == assessmentCourseID) {
                        assessmentCourse = mAllClasses.get(i).getClassName();
                        i = mAllClasses.size();

                        course.setSelection(arrayAdapter.getPosition(assessmentCourse));
                    }
                    i = i + 1;
                }
            } catch (Exception ignore) {

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.notification_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String assessmentName = name.getText().toString();
        String datefrombutton = dateBtn.getText().toString();
        String dateformat = "MM/dd/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateformat, Locale.US);
        Date personalDate = null;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR) - 1900;
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH) - 1;

        Date past = new Date(year, month, day, 23, 59);

        try {
            personalDate = simpleDateFormat.parse(datefrombutton);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (personalDate.after(past)) {
            Long trigger = personalDate.getTime();
            Intent intent = new Intent(AssessmentDetailController.this, Receiver.class);
            intent.putExtra("key", "Assessment " + assessmentName + "is due " + datefrombutton + ".");
            PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetailController.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
        }
        return true;
    }

    private void fillItems(String id) {
        name = findViewById(R.id.textAssessmentNameBox);
        course = findViewById(R.id.courseList);
        type = findViewById(R.id.assessmentType);
        String assessmentName = String.valueOf(getIntent().getStringExtra("name"));
        String assessmentType = String.valueOf(getIntent().getStringExtra("type"));
        int assessmentCourseID = Integer.parseInt(getIntent().getStringExtra("course"));
        String assessmentEnd = String.valueOf((getIntent().getStringExtra("end")));
        String assessmentCourse = null;

        int i = 0;
        List<Class> mAllClasses = repo.getAllClasses();
        while (i < mAllClasses.size()) {
            if (mAllClasses.get(i).getClassID() == assessmentCourseID) {
                assessmentCourse = mAllClasses.get(i).getClassName();
                i = mAllClasses.size();
            }
            i = i + 1;
        }

        name.setText(assessmentName);
        course.setSelection(arrayAdapter.getPosition(assessmentCourse));
        String[] stringArray = getResources().getStringArray(R.array.assessment_types);
        type.setSelection((Arrays.asList(stringArray).indexOf(assessmentType)));
        dateBtn.setText(assessmentEnd);
    }

    private ArrayList<String> getAllCourseNames() {
        ArrayList<String> courseNames = new ArrayList<String>();
        List<Class> allClasses = repo.getAllClasses();

        int i = 0;
        while (i < allClasses.size()) {
            courseNames.add(allClasses.get(i).getClassName());
            i = i + 1;
        }
        return courseNames;
    }

    private String getCurrentDate() {
        android.icu.util.Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        month = month + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return month + "/" + day + "/" + year;
    }

    private void originDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dateString(dayOfMonth, month, year);
                dateBtn.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, onDateSetListener, year, month, day);
    }

    private String dateString(int day, int month, int year) {
        return month + "/" + day + "/" + year;
    }

    public void datePicker(View view) {
        datePickerDialog.show();
    }

    public void saveAssessment(View view) {
        if (id != null) {
            id = getIntent().getStringExtra("id");
            int cID = Integer.parseInt(id);

            name = findViewById(R.id.textAssessmentNameBox);
            course = findViewById(R.id.courseList);
            type = findViewById(R.id.assessmentType);
            dateBtn = findViewById(R.id.DatePickerBtn);

            String cName = String.valueOf(name.getText());
            String cEnd = String.valueOf(dateBtn.getText());
            String cType = type.getSelectedItem().toString();
            String cCourseString = course.getSelectedItem().toString();
            List<Class> allClasses = repo.getAllClasses();

            int cCourseID = 0;
            int i = 0;

            while (i < allClasses.size()) {
                if (allClasses.get(i).getClassName().equals(cCourseString)) {
                    cCourseID = allClasses.get(i).getClassID();
                    i = allClasses.size();
                }

                i = i + 1;
            }

            Assessment assessmentCurrent = new Assessment(cID, cName, cType, cEnd, cCourseID);
            repo.update(assessmentCurrent);
            finish();
        } else {
            List<Assessment> mAllAssessments = repo.getmAllAssessments();

            int last = mAllAssessments.size();
            int cID = 1;
            if (last > 0) {
                int lastID = mAllAssessments.get(last - 1).getAssessmentID();
                cID = lastID + 1;
            }

            name = findViewById(R.id.textAssessmentNameBox);
            course = findViewById(R.id.courseList);
            type = findViewById(R.id.assessmentType);
            dateBtn = findViewById(R.id.DatePickerBtn);

            String cName = String.valueOf(name.getText());
            String cEnd = String.valueOf(dateBtn.getText());
            String cType = type.getSelectedItem().toString();
            String cCourseString = course.getSelectedItem().toString();
            List<Class> allClasses = repo.getAllClasses();

            int cCourseID = 0;
            int i = 0;

            while (i < allClasses.size()) {
                if (allClasses.get(i).getClassName().equals(cCourseString)) {
                    cCourseID = allClasses.get(i).getClassID();
                    i = allClasses.size();

                }
                i = i + 1;
            }
            Assessment assessmentCurrent = new Assessment(cID, cName, cType, cEnd, cCourseID);
            repo.insert(assessmentCurrent);
            finish();
        }
    }

    public void cancelAssessment(View view){
        finish();
    }

    public void deleteAssessment(View view){
        if (id != null){
            List<Assessment> mAllAssessments = repo.getmAllAssessments();
            Assessment assessmentCurrent = null;
            int i = 0;
            while(i < mAllAssessments.size()){
                if (id.equalsIgnoreCase(String.valueOf(mAllAssessments.get(i).getAssessmentID()))){
                    assessmentCurrent = mAllAssessments.get(i);
                    i = mAllAssessments.size();
                }

                i = i + 1;
            }

            repo.delete(assessmentCurrent);
            Toast.makeText(AssessmentDetailController.this, "Delete Complete", Toast.LENGTH_LONG).show();
            finish();
        }

        finish();
    }
}