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
import com.wgu.studentprogressapp.Entities.Term;
import com.wgu.studentprogressapp.R;

import java.util.List;

/**
 * Term Controller
 *
 * @author Joshua Williams
 */
public class TermController extends AppCompatActivity {
    private Repo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        loadRecycler();
    }

    private void loadRecycler() {
        repo = new Repo(getApplication());
        List<Term> allTerms = repo.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.allTermView);
        TermAdapter termAdapter = new TermAdapter(this, allTerms);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        List<Term> allTerms = repo.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.allTermView);
        TermAdapter termAdapter = new TermAdapter(this, allTerms);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        return true;
    }

    public void onResume(){
        loadRecycler();
        super.onResume();
    }

    public void addTerm(View view){
        startActivity(new Intent(TermController.this, TermDetailController.class));
    }

}
