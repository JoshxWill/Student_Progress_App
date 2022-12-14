package com.wgu.studentprogressapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.wgu.studentprogressapp.Entities.Class;

import java.util.List;

@Dao
public interface ClassDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Class classes);

    @Update
    void update(Class classes);

    @Delete
    void delete(Class classes);

    @Query("DELETE FROM table_class")
    void deleteAllClasses();

    @Query("SELECT * FROM table_class ORDER BY classID ASC")
    List<Class> getAllClasses();
}
