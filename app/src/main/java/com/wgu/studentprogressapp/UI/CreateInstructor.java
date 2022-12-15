package com.wgu.studentprogressapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.wgu.studentprogressapp.Database.Repo;
import com.wgu.studentprogressapp.Entities.Course;
import com.wgu.studentprogressapp.Entities.CourseInstructor;
import com.wgu.studentprogressapp.R;

import java.util.List;


public class CreateInstructor extends AppCompatActivity {


    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    List<Course>mCourseList;
    Course mCourse;
    CourseInstructor instructorToEdit;

    int mCourseId;
    int mInstructorId;
    int mAssessCourseId;

    Repo mRepository;
    List<CourseInstructor> mInstructorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_course_instructor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mRepository = new Repo(getApplication());

        mCourseId = getIntent().getIntExtra("courseId", -1);
        mInstructorId = getIntent().getIntExtra("InstructorId", -1);
        mAssessCourseId = getIntent().getIntExtra("assessCourseId", -1);


        mInstructorList = mRepository.getAllInstructorsFromRepo();
        for (CourseInstructor instructor: mInstructorList){
            if (instructor.getInstructorID() == mInstructorId){
                instructorToEdit = instructor;
            }
        }

        editTextName = findViewById(R.id.instructor_name);
        editTextPhone = findViewById(R.id.instructor_phone);
        editTextEmail = findViewById(R.id.instructor_email);

        if (mInstructorId != -1 ){
            editTextName.setText(instructorToEdit.getInstructorName());
            editTextPhone.setText(instructorToEdit.getInstructorPhone());
            editTextEmail.setText(instructorToEdit.getInstructorEmail());
        }

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void SaveInstructorButton(View view) {

        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();

        mInstructorList = mRepository.getAllInstructorsFromRepo();
        int instId = 1;
        for (CourseInstructor i : mInstructorList ){
            if (i.getInstructorID() >= instId){
                instId = i.getInstructorID();
            }
        }

        if (mInstructorId != -1){
            CourseInstructor updateTable = new CourseInstructor(mInstructorId, name, phone, email, mAssessCourseId);
            mRepository.insert(updateTable);
        }else{
            CourseInstructor insertInstructor = new CourseInstructor(++instId, name, phone, email, mCourseId);
            mRepository.insert(insertInstructor);
        }


        Intent intent = new Intent(this, CreateCourse.class);
        if (mCourseId != -1){
            intent.putExtra("courseId", mCourseId);
        }
        else {
            intent.putExtra("courseId", mAssessCourseId);
        }
        startActivity(intent);
    }
}
