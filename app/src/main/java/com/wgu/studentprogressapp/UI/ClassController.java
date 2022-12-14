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
import com.wgu.studentprogressapp.Entities.Class;
import com.wgu.studentprogressapp.R;

import java.util.List;

/**
 * Class Controller
 *
 * @author Joshua Williams
 */
public class ClassController extends AppCompatActivity {
    private Repo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        loadRecycler();
    }
    public void onResume(){
        loadRecycler();
        super.onResume();
    }

    private void loadRecycler() {
        repo = new Repo(getApplication());
        List<Class> allClass = repo.getAllClasses();
        RecyclerView recyclerView = findViewById(R.id.allClassView);
        ClassAdapter classAdapter = new ClassAdapter(this, allClass);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.refresh_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        repo = new Repo(getApplication());
        List<Class> allClass = repo.getAllClasses();
        RecyclerView recyclerView = findViewById(R.id.allClassView);
        ClassAdapter classAdapter = new ClassAdapter(this, allClass);
        recyclerView.setAdapter(classAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        return true;
    }

    public void addClass(View view){
        startActivity(new Intent(ClassController.this, ClassDetailController.class));
    }

}
