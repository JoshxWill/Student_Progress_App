package com.wgu.studentprogressapp.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.wgu.studentprogressapp.DAO.AssessmentDAO;
import com.wgu.studentprogressapp.DAO.ClassDAO;
import com.wgu.studentprogressapp.DAO.TermDAO;
import com.wgu.studentprogressapp.Entities.Assessment;
import com.wgu.studentprogressapp.Entities.Term;

@androidx.room.Database(entities = {Assessment.class, Class.class, Term.class}, version = 3, exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();
    public abstract ClassDAO classDAO();
    public abstract TermDAO termDAO();

    private static volatile StudentDatabase INSTANCE;

    static StudentDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (StudentDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), StudentDatabase.class, "schoolDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;

    }

}
