package com.wgu.studentprogressapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.wgu.studentprogressapp.Entities.Assessment;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Assessment assessment);

    @Update
    public void update(Assessment assessment);

    @Delete
    public void delete(Assessment assessment);

    @Query("DELETE FROM table_assessment")
    public void deleteAllAssessments();

    @Query("SELECT * FROM table_assessment ORDER BY assessmentID ASC")
    List<Assessment> getAllAssessments();
}
