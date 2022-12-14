package com.wgu.studentprogressapp.UI;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu.studentprogressapp.Database.Repo;
import com.wgu.studentprogressapp.Entities.Assessment;
import com.wgu.studentprogressapp.Entities.Class;
import com.wgu.studentprogressapp.Entities.Term;
import com.wgu.studentprogressapp.R;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Class Detail Controller
 *
 * @author Joshua Williams
 */
public class ClassDetailController extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    DatePickerDialog datePickerDialog20;
    private Button startDateBtn;
    private Button endDateBtn;
    private TextView name;
    private TextView classNotes;
    private TextView mentorName;
    private TextView mentorPhone;
    private TextView mentorEmail;
    private int classID;
    private Spinner termSpin;
    private Spinner statusSpin;

    String id = null;
    ArrayAdapter<String> arrayAdapter;
    Repo repo = new Repo(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);

        startDateBtn = findViewById(R.id.StartDatePickerBtn);
        endDateBtn = findViewById(R.id.EndDatePickerBtn);
        name = findViewById(R.id.textClassNameBox);
        classNotes = findViewById(R.id.classNotes);
        mentorName = findViewById(R.id.instructorName);
        mentorPhone = findViewById(R.id.instructorPhone);
        mentorEmail = findViewById(R.id.instructorEmail);
        termSpin = findViewById(R.id.termList);
        statusSpin = findViewById(R.id.Status);

        originalDatePicker();
        originalDatePicker20();

        List<String> arraySpin = getAllTermNames();
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arraySpin);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpin.setAdapter(arrayAdapter);

        if (getIntent().getStringExtra("id") != null){
            this.id = getIntent().getStringExtra("id");
            fillForm(id);
        }

        else if (getIntent().getStringExtra("termID") != null){
            try {
                fillForm();
                String stringTermID = String.valueOf(getIntent().getStringExtra("termID"));

                List<Term> mAllTerms = repo.getAllTerms();
                int TermID = Integer.parseInt(stringTermID);

                int i = 0;

                while(i < mAllTerms.size()){
                    if (mAllTerms.get(i).getTermID() == TermID){
                        termSpin.setSelection(arrayAdapter.getPosition(mAllTerms.get(i).getTermName()));
                        int test = arrayAdapter.getPosition(mAllTerms.get(i).getTermName());
                        i = mAllTerms.size();
                    }

                    i = i + 1;
                }
            }catch (Exception ignore){

            }
        }

        else {
            fillForm();
        }
    }

    private void fillForm() {
        startDateBtn.setText(DateAssistance.getDateToday());
        endDateBtn.setText(DateAssistance.getDateToday());
    }

    public void onResume(){
        loadRecycler();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_notification, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.startnotify:
                //Start Notification
                String className = name.getText().toString();
                String datefrombtn = startDateBtn.getText().toString();
                String dateformat = "MM/dd/yyyy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateformat, Locale.US);
                Calendar calendar = Calendar.getInstance();

                int year = calendar.get(Calendar.YEAR)-1900;
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH)-1;

                Date past = new Date(year, month, day, 23, 59);
                Date personalDate = null;
                try {
                    personalDate = simpleDateFormat.parse(datefrombtn);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (personalDate.after(past)){
                    Long trigger = personalDate.getTime();
                    Intent intent = new Intent(ClassDetailController.this, Receiver.class);
                    intent.putExtra("key", "Class " + className + " starts on " + datefrombtn + ".");
                    PendingIntent sender = PendingIntent.getBroadcast(ClassDetailController.this, ++MainActivity.numAlert, intent, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                }
                return true;

            case R.id.endnotify:
                //End Notification
                String className2 = name.getText().toString();
                String datefrombtn2 = endDateBtn.getText().toString();
                String dateformat2 = "MM/dd/yyyy";
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(dateformat2, Locale.US);
                Calendar calendar2 = Calendar.getInstance();

                int year2 = calendar2.get(Calendar.YEAR)-1900;
                int month2 = calendar2.get(Calendar.MONTH);
                int day2 = calendar2.get(Calendar.DAY_OF_MONTH)-1;

                Date past2 = new Date(year2, month2, day2, 23, 59);
                Date personalDate2 = null;
                try {
                    personalDate2 = simpleDateFormat2.parse(datefrombtn2);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (personalDate2.after(past2)){
                    Long trigger2 = personalDate2.getTime();
                    Intent intent2 = new Intent(ClassDetailController.this, Receiver.class);
                    intent2.putExtra("key", "Class " + className2 + " ends on " + datefrombtn2 + ".");
                    PendingIntent sender2 = PendingIntent.getBroadcast(ClassDetailController.this, ++MainActivity.numAlert, intent2, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sender2);
                }
                return true;

            case R.id.refresh:
                loadRecycler();
                return true;

        }
        return true;
    }

    private void loadRecycler() {
        List<Assessment> allAssessments = repo.getmAllAssessments();
        RecyclerView recyclerView = findViewById(R.id.classAssessmentView);
        List<Assessment> classAssessments = new ArrayList<Assessment>();

        try {
            int cID = Integer.parseInt(getIntent().getStringExtra("id"));
            int d = 0;
            while (d < allAssessments.size()){
                if (allAssessments.get(d).getClassID() == cID){
                    Assessment currentAssessment = allAssessments.get(d);
                    classAssessments.add(currentAssessment);
                }
                d = d + 1;
            }
        }catch (Exception Ignore){

        }

        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this, classAssessments);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<String> getAllTermNames() {
        ArrayList<String> termNames = new ArrayList<String>();
        List<Term> allTerms = repo.getAllTerms();
        int i = 0;
        while(i < allTerms.size()){
            termNames.add(allTerms.get(i).getTermName());
            i = i + 1;
        }
        return termNames;
    }

    private void fillForm(String id) {
        name.setText(String.valueOf(getIntent().getStringExtra("name")));
        classNotes.setText(String.valueOf(getIntent().getStringExtra("notes")));
        mentorName.setText(String.valueOf(getIntent().getStringExtra("instructorName")));
        mentorPhone.setText(String.valueOf(getIntent().getStringExtra("instructorPhone")));
        mentorEmail.setText(String.valueOf(getIntent().getStringExtra("instructorEMail")));
        startDateBtn.setText(String.valueOf(getIntent().getStringExtra("start")));
        endDateBtn.setText(String.valueOf(getIntent().getStringExtra("end")));
        String stringTermID = String.valueOf(getIntent().getStringExtra("term"));

        List<Term> mAllTerms = repo.getAllTerms();
        int TermID = Integer.parseInt(stringTermID);
        int i = 0;
        while(i < mAllTerms.size()){
            if(mAllTerms.get(i).getTermID() == TermID){
                termSpin.setSelection(arrayAdapter.getPosition(mAllTerms.get(i).getTermName()));
                int test = arrayAdapter.getPosition(mAllTerms.get(i).getTermName());
                i = mAllTerms.size();
            }

            i = i + 1;
        }

        String classStatus = String.valueOf(getIntent().getStringExtra("status"));
        String[] stringArray = getResources().getStringArray(R.array.class_status);
        statusSpin.setSelection(Arrays.asList(stringArray).indexOf(classStatus));
        loadRecycler();
    }

    public void startDatePicker(View view){
        datePickerDialog.show();
    }
    private void originalDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = DateAssistance.createDateString(dayOfMonth, month, year);
                startDateBtn.setText(date);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, onDateSetListener, year, month, day);
    }

    public void endDatePicker(View view){
        datePickerDialog20.show();
    }
    private void originalDatePicker20() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = DateAssistance.createDateString(dayOfMonth, month, year);
                endDateBtn.setText(date);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog20 = new DatePickerDialog(this, style, onDateSetListener, year, month, day);
    }

    public void saveClass(View view){
        String sStart = String.valueOf(startDateBtn.getText());
        String sEnd = String.valueOf(endDateBtn.getText());
        String sName = String.valueOf(name.getText());
        String sClassNotes = String.valueOf(classNotes.getText());
        String sMentorName = String.valueOf(mentorName.getText());
        String sMentorPhone = String.valueOf(mentorPhone.getText());
        String sMentorEmail = String.valueOf(mentorEmail.getText());
        String sTermString = termSpin.getSelectedItem().toString();
        String sStatus = statusSpin.getSelectedItem().toString();

        int sTerm = 0;

        List<Term> mAllTerms = repo.getAllTerms();
        int i = 0;
        while(i < mAllTerms.size()){
            if (mAllTerms.get(i).getTermName().equals(sTermString)){
                sTerm = mAllTerms.get(i).getTermID();
                i = mAllTerms.size();
            }

            i = i + 1;
        }

        if (id != null){
            int sClassID = Integer.parseInt(id);
            Class currentClass = new Class(sClassID, sName, sStart, sEnd, sStatus, sMentorName, sMentorPhone, sMentorEmail, sClassNotes, sTerm);
            repo.update(currentClass);
        }

        else{
            List<Class> mAllClass = repo.getAllClasses();
            int sClassID = mAllClass.get(mAllClass.size()-1).getClassID()+1;
            Class currentClass = new Class(sClassID, sName, sStart, sEnd, sStatus, sMentorName, sMentorPhone, sMentorEmail, sClassNotes, sTerm);
            repo.insert(currentClass);
        }
        finish();
    }

    public void cancelClass(View view){
        finish();
    }

    public void deleteClass(View view){
        if(id !=null){
            int dClassID = Integer.parseInt(id);
            List<Class> mAllClasses = repo.getAllClasses();
            Class current = null;
            int i = 0;
            while(i < mAllClasses.size()){
                if(dClassID == mAllClasses.get(i).getClassID()){
                    current = mAllClasses.get(i);
                    i = mAllClasses.size();
                }
                i = i + 1;
            }

            List<Assessment> dAssessments = repo.getmAllAssessments();
            List<Assessment> classAssessments = new ArrayList<>();
            int d = 0;
            while(d < dAssessments.size()){
                if(dAssessments.get(d).getClassID() == dClassID){
                    classAssessments.add(dAssessments.get(d));
                    d = dAssessments.size();
                }
                d = d + 1;
            }
            if(classAssessments.size()!=0){
                AlertDialog.Builder cbuilder = new AlertDialog.Builder(ClassDetailController.this);
                cbuilder.setCancelable(true);
                cbuilder.setTitle("Confirm Delete");
                cbuilder.setMessage("Before you delete class, all associated assessments must be deleted.  Delete class with all associated assessments?");
                cbuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteRecursive();
                            }
                        });
                cbuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = cbuilder.create();
                dialog.show();

            }
            else{
                deleteRecursive();
            }
    }
        else {
            finish();
        }

}

    private void deleteRecursive() {
        if(id !=null){
            int dClassID = Integer.parseInt(id);
            List<Class> mAllClasses = repo.getAllClasses();
            Class current = null;
            int i = 0;
            while(i<mAllClasses.size()){
                if(dClassID == mAllClasses.get(i).getClassID()){
                    current = mAllClasses.get(i);
                    i = mAllClasses.size();
                }
                i = i + 1;
            }
            List<Assessment> dAssessments = repo.getmAllAssessments();
            int d = 0;
            while(d < dAssessments.size()){
                if(dAssessments.get(d).getClassID() == dClassID){
                    Assessment currentAssessment = dAssessments.get(d);
                    repo.delete(currentAssessment);
                }
                d = d + 1;
            }
            repo.delete(current);
        }
        Toast.makeText(ClassDetailController.this, "Deletion Complete", Toast.LENGTH_LONG).show();
        finish();
    }

    public void shareNotes(View view) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String note = String.valueOf(classNotes.getText());
        String className = String.valueOf(name.getText());
        String shareBody = className+": "+note;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, className+" notes");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void addAssessment(View view) {
        Intent intent = new Intent(ClassDetailController.this, AssessmentDetailController.class);
        intent.putExtra("classID",String.valueOf(getIntent().getStringExtra("id")));
        startActivity(intent);
    }
}
