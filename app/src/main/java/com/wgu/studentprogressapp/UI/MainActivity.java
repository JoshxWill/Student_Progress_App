package com.wgu.studentprogressapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.wgu.studentprogressapp.Database.Repo;
import com.wgu.studentprogressapp.Entities.Assessment;
import com.wgu.studentprogressapp.Entities.Class;
import com.wgu.studentprogressapp.Entities.Term;
import com.wgu.studentprogressapp.R;

public class MainActivity extends AppCompatActivity {

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repo repo = new Repo(getApplication());
        Term term1 = new Term(1, "Term 1", "08/25/2021", "12/10/2021");
        repo.insert(term1);
        Term term2 = new Term(2, "Term 2", "08/25/2021", "12/10/2021");
        repo.insert(term2);
        Term term3 = new Term(3, "Term 3", "08/25/2021", "12/10/2021");
        repo.insert(term3);

        Class class1 = new Class(1, "Class 1", "01/01/2022", "06/06/2022", "In-Progress", "Mr. Saturday", "333-444-5555", "mrsaturday@progress.com", "", 1);
        repo.insert(class1);
        Class class2 = new Class(2, "Class 2", "01/01/2022", "06/06/2022", "In-Progress", "Mr. Saturday", "333-444-5555", "mrsaturday@progress.com", "", 1);
        repo.insert(class2);
        Class class3 = new Class(3, "Class 3", "01/01/2022", "06/06/2022", "In-Progress", "Mr. Saturday", "333-444-5555", "mrsaturday@progress.com", "", 1);
        repo.insert(class3);

        Assessment assessment1 = new Assessment(1, "Test 1", "Objective", "05/20/2022", 1);
        repo.insert(assessment1);
        Assessment assessment2 = new Assessment(2, "Test 2", "Performance", "05/20/2022", 1);
        repo.insert(assessment2);
        Assessment assessment3 = new Assessment(3, "Test 3", "Performance", "05/20/2022", 1);
        repo.insert(assessment3);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, OptionController.class));
            }
        }, 1500);
    }
}