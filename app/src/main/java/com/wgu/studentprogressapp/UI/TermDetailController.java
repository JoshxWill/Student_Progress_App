package com.wgu.studentprogressapp.UI;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Term Detail Controller
 *
 * @author Joshua Williams
 */
public class TermDetailController extends AppCompatActivity {

    DatePickerDialog datePickerDialog;
    DatePickerDialog datePickerDialog20;
    private Button startDateBtn;
    private Button endDateBtn;
    Repo repo;
    private TextView TermNameBox;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail20);
        repo = new Repo(getApplication());
        startDateBtn = findViewById(R.id.StartDatePickerBtn);
        endDateBtn = findViewById(R.id.EndDatePickerBtn);
        TermNameBox = findViewById(R.id.textTermNameBox);

        originalDatePicker();
        originalDatePicker20();

        if (getIntent().getStringExtra("id") != null){
            fillForm(getIntent().getStringExtra("id"));
        }

        else{
            fillForm();
        }
    }

    private void fillForm() {
        startDateBtn.setText(DateAssistance.getDateToday());
        endDateBtn.setText(DateAssistance.getDateToday());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        loadRecycler();
        return true;
    }

    public void onResume(){
        loadRecycler();
        super.onResume();
    }

    private void loadRecycler() {
        if (getIntent().getStringExtra("id") != null){
            List<Class> allClass = repo.getAllClasses();
            RecyclerView recyclerView = findViewById(R.id.termClassView);
            int TermID = Integer.parseInt(getIntent().getStringExtra("id"));
            List<Class> classList = new ArrayList<Class>();

            int i = 0;
            while(i < allClass.size()){
                if (allClass.get(i).getTermID() == TermID){
                    classList.add(allClass.get(i));
                }

                i = i + 1;
            }

            ClassAdapter classAdapter = new ClassAdapter(this, classList);
            recyclerView.setAdapter(classAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void fillForm(String id) {
        String test = String.valueOf(getIntent().getStringExtra("termName"));
        TermNameBox.setText(test);
        startDateBtn.setText(String.valueOf(getIntent().getStringExtra("startTermDate")));
        endDateBtn.setText(String.valueOf(getIntent().getStringExtra("endTermDate")));
        loadRecycler();
    }


    private void originalDatePicker20() {
        DatePickerDialog.OnDateSetListener onDateSetListener= new DatePickerDialog.OnDateSetListener() {
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

    private void originalDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener= new DatePickerDialog.OnDateSetListener() {
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

    public void startDatePicker(View view){
        datePickerDialog.show();
    }

    public void endDatePicker(View view){
        datePickerDialog20.show();
    }

    public void saveTerm(View view){
        int termID;
        String termName = String.valueOf(TermNameBox.getText());
        String startDate = String.valueOf(startDateBtn.getText());
        String endDate = String.valueOf(endDateBtn.getText());

        if (getIntent().getStringExtra("id") != null){
            termID = Integer.parseInt(getIntent().getStringExtra("id"));
            Term currentTerm = new Term(termID, termName, startDate, endDate);
            repo.update(currentTerm);
        }

        else{
            List<Term> allTerms = repo.getAllTerms();
            termID = allTerms.get(allTerms.size() -1).getTermID() +1;
            Term currentTerm = new Term(termID, termName, startDate, endDate);
            repo.insert(currentTerm);
        }

        Term currentTerm = new Term(termID, termName, startDate, endDate);

        finish();
    }

    public void cancelTerm(View view){
        finish();
    }

    public void deleteTerm(View view){
        if(getIntent().getStringExtra("id")!=null){
            int termID = Integer.parseInt(getIntent().getStringExtra("id"));
            List<Class> allClasses = repo.getAllClasses();
            List<Class> termClasses = new ArrayList<>();
            int d = 0;
            while(d < allClasses.size()) {
                if (allClasses.get(d).getTermID() == termID) {
                    termClasses.add(allClasses.get(d));
                    d = allClasses.size();
                }
                d = d + 1;
            }
            if(termClasses.size()!=0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TermDetailController.this);
                builder.setCancelable(true);
                builder.setTitle("Confirm Delete");
                builder.setMessage("Before deleting term, all associated classes and assessments must be deleted.  Delete term with all associated classes and assessments?");
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteRecursive();
                            }
                        });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                deleteRecursive();
            }
        }
        else{
            finish();
        }
    }

    private void deleteRecursive() {
        int termID = Integer.parseInt(getIntent().getStringExtra("id"));
        Term currentTerm = null;
        List<Term> allTerms = repo.getAllTerms();
        int i = 0;
        while(i < allTerms.size()){
            if(allTerms.get(i).getTermID() == termID){
                currentTerm = allTerms.get(i);
                i = allTerms.size();
            }
            i=i+1;
        }
        List<Class> allClasses = repo.getAllClasses();
        int j =0;
        while(j < allClasses.size()){
            if(allClasses.get(j).getTermID() == termID){
                Class currentClass = allClasses.get(j);
                int classID = currentClass.getClassID();
                int k = 0;
                List<Assessment> allAssessments = repo.getmAllAssessments();
                while(k < allAssessments.size()){
                    if(allAssessments.get(k).getClassID() == classID){
                        Assessment currentAssessment = allAssessments.get(k);
                        repo.delete(currentAssessment);
                    }
                    k = k + 1;
                }
                repo.delete(currentClass);
            }
            j = j + 1;
        }
        repo.delete(currentTerm);


        Toast.makeText(TermDetailController.this, "Delete Complete", Toast.LENGTH_LONG).show();
        finish();
    }
}
