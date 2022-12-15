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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Assessment assessment);

    @Delete
    void delete(Assessment assessment);

    @Query("DELETE FROM assessment_table")
    void deleteAllFromAssessmentTable();

    @Query("SELECT * FROM assessment_table ORDER BY assessmentId ASC")
    List<Assessment> getAssessmentsFromTable();

}
