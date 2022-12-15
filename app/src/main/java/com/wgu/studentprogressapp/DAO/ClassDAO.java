package com.wgu.studentprogressapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.wgu.studentprogressapp.Entities.Course;

import java.util.List;

@Dao
public interface ClassDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Course course);

    //TODO may have to change to replace instead of ignore
    //TODO may need to add an insert all

    @Delete
    void delete (Course course );


    @Query("DELETE FROM course_table")
    void deleteAllFromCourseTable();

    @Query("SELECT * FROM course_table ORDER BY courseId ASC") // change to order by todo
    List<Course> getAllCoursesFromTable();


}
