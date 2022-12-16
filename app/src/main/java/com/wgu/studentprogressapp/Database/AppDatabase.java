package com.wgu.studentprogressapp.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.wgu.studentprogressapp.DAO.AssessmentDAO;
import com.wgu.studentprogressapp.DAO.ClassDAO;
import com.wgu.studentprogressapp.DAO.CourseInstructorDAO;
import com.wgu.studentprogressapp.DAO.TermDAO;
import com.wgu.studentprogressapp.Entities.Assessment;
import com.wgu.studentprogressapp.Entities.Course;
import com.wgu.studentprogressapp.Entities.CourseInstructor;
import com.wgu.studentprogressapp.Entities.Term;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Term.class, Course.class, Assessment.class, CourseInstructor.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TermDAO termDao();
    public abstract ClassDAO courseDao();
    public abstract AssessmentDAO assessmentDao();
    public abstract CourseInstructorDAO instructorDao();

    private static int NUMBER_OF_THREADS = 4;
    static ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile AppDatabase instance;



    public static AppDatabase getDatabaseInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                instance = Room.databaseBuilder(context.getApplicationContext(),
                                AppDatabase.class, "app_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(dbCallback) // used for adding data to database
                        .build();
            }

        }
        return instance;
    }


    private static RoomDatabase.Callback dbCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            TermDAO termDao = instance.termDao();
            ClassDAO courseDao = instance.courseDao();
            AssessmentDAO assessmentDao = instance.assessmentDao();
            CourseInstructorDAO instructorDao = instance.instructorDao();


            dbWriteExecutor.execute(()->{

                termDao.deleteAllFromTermsTable();
                courseDao.deleteAllFromCourseTable();
                assessmentDao.deleteAllFromAssessmentTable();
                instructorDao.deleteAllFromInstructorTable();


                Term termDbTable = new Term(1,"Term 1", "01/01/2022", "01/28/2022");
                termDao.insert(termDbTable);
                Term terDbTable2 = new Term(2,"Term 2", "02/01/2022", "02/28/2022");
                termDao.insert(terDbTable2);




                Course courseDbTable = new Course(101, "Course 1", "01/01/2022","01/28/2022", "Complete","First programming course",1);
                courseDao.insert(courseDbTable);
                Course courseDbTable2 = new Course(102, "Course 2", "02/01/2022","02/30/2022", "In Progress","second programming course",1);
                courseDao.insert(courseDbTable2);
                Course courseDbTable3 = new Course(103, "Course 3", "03/01/2022","03/30/2022", "Dropped","third programming course",2);
                courseDao.insert(courseDbTable3);
                Course courseDbTable11 = new Course(104, "Course 4", "04/01/2022","04/28/2022", "Incomplete","fourth programming course",2);
                courseDao.insert(courseDbTable11);



                Assessment assessmentTable = new Assessment(201,"Assessment 1", "Peformance", "11/01/2021", "11/01/2021", 101);
                assessmentDao.insert(assessmentTable);
                Assessment assessmentTable2 = new Assessment(202,"Assessment 2", "Performance", "02/01/2022",  "02/01/2022", 102);
                assessmentDao.insert(assessmentTable2);
                Assessment assessmentTable3 = new Assessment(203,"Assessment 3", "Performance", "01/01/2022",  "02/01/2022", 103);
                assessmentDao.insert(assessmentTable3);
                Assessment assessmentTable4 = new Assessment(204,"Assessment 4", "Objective", "02/01/2022", "02/30/2021", 104);




                CourseInstructor instructorTable = new CourseInstructor(301, "Instructor 1", "333-333-4444", "grinch@progress.edu", 101);
                instructorDao.insert(instructorTable);
                CourseInstructor instructorTable2 = new CourseInstructor(302, "Instructor 2", "333-333-3333", "homealone@progress.edu", 102);
                instructorDao.insert(instructorTable2);
                CourseInstructor instructorTable3 = new CourseInstructor(303, "Instructor 3", "333-333-2222", "rudolph@progress.edu", 103);
                instructorDao.insert(instructorTable3);
                CourseInstructor instructorTable4 = new CourseInstructor(304, "Instructor 4", "333-333-1111", "charliebrown@progress.edu", 104);
                instructorDao.insert(instructorTable4);

            });
        }

    };

}
