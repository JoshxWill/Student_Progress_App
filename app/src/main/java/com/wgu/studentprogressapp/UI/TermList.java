package com.wgu.studentprogressapp.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wgu.studentprogressapp.Adapter.TermAdapter;
import com.wgu.studentprogressapp.Database.Repo;
import com.wgu.studentprogressapp.Entities.Course;
import com.wgu.studentprogressapp.Entities.Term;
import com.wgu.studentprogressapp.R;

import java.util.List;


public class TermList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TermAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManger;
    Repo databaseRepository;

    public List<Term> termTableList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // may need something here todo
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        databaseRepository = new Repo(getApplication());

        // Deals with adapter and recyleview
        getTermsList();
        buildRecyclerView();

        // for Deleting with swipe
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // todo add code for delete not allowed here with courses
                for (Course course: databaseRepository.getAllCoursesFromRep()){
                    if (course.getCourseTermId() == mAdapter.getTermAt(viewHolder.getAdapterPosition()).getTermId())
                        if (mAdapter.getTermAt(viewHolder.getAdapterPosition()).getTermId() == course.getCourseTermId()){
                            mAdapter.notifyDataSetChanged();
                            Toast.makeText(TermList.this, "Please remove all courses in term before deleting", Toast.LENGTH_LONG).show();
                            return;
                        }
                }

                databaseRepository.delete(mAdapter.getTermAt(viewHolder.getAdapterPosition()));
                mAdapter.mTermsList.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                Toast.makeText(TermList.this, "Term Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(mRecyclerView);

    }

    // get application
    public void getTermsList() {
        termTableList = databaseRepository.getAllTermsFromRepo();
        //todo add course and assessment list here
    }

    // builder
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyler_view_terms);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(this);
        mAdapter = new TermAdapter(this);
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.termsSetter(databaseRepository.getAllTermsFromRepo());
        //todo add course version here
    }

    // Floating action button to add term
    public void addTermOnClick(View view) {
        Intent intent = new Intent(this, AddTerm.class);
        startActivity(intent);
    }


}
