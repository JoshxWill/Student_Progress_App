package com.wgu.studentprogressapp.Database;

import android.app.Application;

import com.wgu.studentprogressapp.DAO.AssessmentDAO;
import com.wgu.studentprogressapp.DAO.ClassDAO;
import com.wgu.studentprogressapp.DAO.TermDAO;
import com.wgu.studentprogressapp.Entities.Assessment;
import com.wgu.studentprogressapp.Entities.Class;
import com.wgu.studentprogressapp.Entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repo {
    private AssessmentDAO mAssessmentDAO;
    private List<Assessment> mAllAssessments;

    private ClassDAO mClassDAO;
    private List<Class> mAllClasses;

    private TermDAO mTermDAO;
    private List<Term> mAllTerms;

    private static int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repo(Application application) {
        StudentDatabase db = StudentDatabase.getDatabase(application);
        mAssessmentDAO = db.assessmentDAO();
        mClassDAO = db.classDAO();
        mTermDAO = db.termDAO();
    }

    //Assessments
    public List<Assessment> getmAllAssessments() {
        databaseExecutor.execute(() -> {
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void update(Assessment assessment) {
        databaseExecutor.execute(()->{
            mAssessmentDAO.update(assessment);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insert(Assessment assessment) {
        databaseExecutor.execute(()-> {
            mAssessmentDAO.insert(assessment);
        });

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment) {
        databaseExecutor.execute(()->{
            mAssessmentDAO.delete(assessment);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    //Classes
    public List<Class> getAllClasses() {
        databaseExecutor.execute(()-> {
            mAllClasses = mClassDAO.getAllClasses();
        });

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        return mAllClasses;
    }

    public void update(Class currentClass) {
        databaseExecutor.execute(()->{
            mClassDAO.update(currentClass);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insert(Class currentClass) {
        databaseExecutor.execute(()-> {
            mClassDAO.insert(currentClass);
        });

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Class current) {
        databaseExecutor.execute(()->{
            mClassDAO.delete(current);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    //Terms
    public List<Term> getAllTerms() {
        databaseExecutor.execute(()-> {
            mAllTerms = mTermDAO.getAllTerms();
        });

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void update(Term currentTerm) {
        databaseExecutor.execute(()->{
            mTermDAO.update(currentTerm);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insert(Term currentTerm) {
        databaseExecutor.execute(()-> {
            mTermDAO.insert(currentTerm);
        });

        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Term currentTerm) {
        databaseExecutor.execute(()->{
            mTermDAO.delete(currentTerm);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
