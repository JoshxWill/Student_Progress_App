package com.wgu.studentprogressapp.Entities;

import android.util.StringBuilderPrinter;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Term Entity
 *
 * @author Joshua Williams
 */
@Entity(tableName = "table_term")
public class Term {
    @PrimaryKey(autoGenerate = true)
    private int termID;
    private String termName;
    private String startDate;
    private String endDate;

    public Term(int termID, String termName, String startDate, String endDate){
        this.termID = termID;
        this.termName = termName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //Getter-Setter for Term ID
    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }
    //Getter-Setter for Term Name
    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }
    //Getter-Setter for Start Date
    public String getStartDate(){
        return startDate;
    }

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }
    //Getter-Setter for End Date
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
