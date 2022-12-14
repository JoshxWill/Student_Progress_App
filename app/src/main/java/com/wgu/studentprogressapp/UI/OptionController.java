package com.wgu.studentprogressapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.wgu.studentprogressapp.R;

public class OptionController extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option_main);
    }

    public void allTerms(View view){
        startActivity(new Intent(OptionController.this, TermController.class));
    }

    public void allClass(View view){
        startActivity(new Intent(OptionController.this, ClassController.class));
    }

    public void allAssessments(View view){
        startActivity(new Intent(OptionController.this, AssessmentController.class));
    }
}
