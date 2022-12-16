package com.wgu.studentprogressapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.wgu.studentprogressapp.Entities.Term;

import java.util.List;

@Dao
public interface TermDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Term term);

    @Delete
    void delete (Term term );

    @Query("DELETE FROM terms_table")
    void deleteAllFromTermsTable();

    @Query("SELECT * FROM terms_table ORDER BY termId ASC") // change to order by todo
    List<Term> getAllTermsFromTable();


}
