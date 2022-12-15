package com.wgu.studentprogressapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wgu.studentprogressapp.Database.Repo;
import com.wgu.studentprogressapp.R;


public class MainController extends AppCompatActivity {

    static int alertNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        Repo databaseRepository = new Repo(getApplication());
        databaseRepository.getAllTermsFromRepo();

//        TermTable termTable = new TermTable(1, "aloha", "1/1/22", "2/2/22");
//        repository.insert(termTable); todo not working, not sure why, don't really need it

    }


    public void homeEnterBtn(View view) {
        Intent intent = new Intent(this,TermList.class);
        startActivity(intent);

    }
}
