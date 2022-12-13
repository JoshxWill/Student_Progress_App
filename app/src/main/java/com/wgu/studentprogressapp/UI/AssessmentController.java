package com.wgu.studentprogressapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu.studentprogressapp.Database.Repo;
import com.wgu.studentprogressapp.Entities.Assessment;
import com.wgu.studentprogressapp.R;

import java.util.List;

/**
 * Assessment Controller
 *
 * @author Joshua Williams
 */
public class AssessmentController extends AppCompatActivity {
    private Repo repo;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessments);
        loadRecycler();
    }

    private void loadRecycler() {
        repo = new Repo(getApplication());
        List<Assessment> allClass = repo.getmAllAssessments();
        RecyclerView recyclerView = findViewById(R.id.allAssessmentsView);
        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this, allClass);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        List<Assessment> allClass = repo.getmAllAssessments();
        RecyclerView recyclerView = findViewById(R.id.allAssessmentsView);
        AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this, allClass);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        return true;
    }

    public void addAssessment(View view){
        startActivity(new Intent(AssessmentController.this, AssessmentDetailController.class));
    }
}
