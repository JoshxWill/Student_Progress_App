package com.wgu.studentprogressapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wgu.studentprogressapp.Entities.CourseInstructor;

import java.util.List;

@Dao
public interface CourseInstructorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CourseInstructor instructor);

    @Delete
    void delete(CourseInstructor instructor);

    @Query("DELETE FROM instructor_table")
    void deleteAllFromInstructorTable();

    @Query("SELECT * FROM instructor_table ORDER BY instructorID ASC")
    List<CourseInstructor> getInstructorsFromTable();


}
