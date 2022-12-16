package com.wgu.studentprogressapp.Database;

import android.app.Application;

import com.wgu.studentprogressapp.DAO.AssessmentDAO;
import com.wgu.studentprogressapp.DAO.ClassDAO;
import com.wgu.studentprogressapp.DAO.CourseInstructorDAO;
import com.wgu.studentprogressapp.DAO.TermDAO;
import com.wgu.studentprogressapp.Entities.Assessment;
import com.wgu.studentprogressapp.Entities.Course;
import com.wgu.studentprogressapp.Entities.CourseInstructor;
import com.wgu.studentprogressapp.Entities.Term;

import java.util.List;


public class Repo {

    private TermDAO mtermDao;
    private ClassDAO mcourseDao;
    private AssessmentDAO mAssessmentDao;
    private CourseInstructorDAO mInstructorDao;

    private List<Term> mallTerms;
    private List<Course> mallCourses;
    private List<Assessment> mallAssessments;
    private List<CourseInstructor> mallInstructors;


    public Repo(Application application){
        AppDatabase database = AppDatabase.getDatabaseInstance(application);
        mtermDao = database.termDao();
        mcourseDao = database.courseDao();
        mAssessmentDao = database.assessmentDao();
        mInstructorDao = database.instructorDao();

        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    // TERMS
    // Terms getter
    public List<Term> getAllTermsFromRepo() {
        AppDatabase.dbWriteExecutor.execute(()->{
            mallTerms = mtermDao.getAllTermsFromTable();
        });
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mallTerms;
    }

    // Terms insert
    public void insert(Term termTable){
        AppDatabase.dbWriteExecutor.execute(()->{
            mtermDao.insert(termTable);
        });
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete (Term termTable){
        AppDatabase.dbWriteExecutor.execute(()->{
            mtermDao.delete(termTable);
        });
    }

    public void deleteAllTerms () {
        AppDatabase.dbWriteExecutor.execute(()->{
            mtermDao.deleteAllFromTermsTable();
        });
    }

    // COURSES
    public List<Course> getAllCoursesFromRep() {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mallCourses = mcourseDao.getAllCoursesFromTable();
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mallCourses;
    }

    public void insert(Course course) {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mcourseDao.insert(course);
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Course course) {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mcourseDao.delete(course);
        });
    }

    public void deleteAllCourses() {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mcourseDao.deleteAllFromCourseTable();
        });
    }

    // ASSESSMENTS
    public List<Assessment> getAllAssessmentsFromRepo() {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mallAssessments = mAssessmentDao.getAssessmentsFromTable();
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mallAssessments;
    }

    public void insert(Assessment assessment) {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mAssessmentDao.insert(assessment);
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment) {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mAssessmentDao.delete(assessment);
        });
    }

    public void deleteAllAssessments() {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mcourseDao.deleteAllFromCourseTable();
        });
    }

    // INSTRUCTORS

    public List<CourseInstructor> getAllInstructorsFromRepo(){
        AppDatabase.dbWriteExecutor.execute(()->{
            mallInstructors = mInstructorDao.getInstructorsFromTable();
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mallInstructors;
    }


    public void insert(CourseInstructor instructor) {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mInstructorDao.insert(instructor);
        });
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(CourseInstructor instructor) {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mInstructorDao.delete(instructor);
        });
    }

    public void deleteAllInstructors() {
        AppDatabase.dbWriteExecutor.execute(() -> {
            mInstructorDao.deleteAllFromInstructorTable();
        });
    }
}



