package com.wgu.studentprogressapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class Entity
 *
 * @author Joshua Williams
 */
@Entity(tableName = "table_class")
public class Class {
    @PrimaryKey(autoGenerate = true)
    private int classID;
    private String className;
    private String startDate;
    private String endDate;
    private String classStatus;
    private String instructorName;
    private String instructorPhone;
    private String instructorEmail;
    private String classNotes;
    private int termID;

    public Class(int classID, String className, String startDate, String endDate, String classStatus, String instructorName, String instructorPhone, String instructorEmail, String classNotes, int termID){
        this.classID = classID;
        this.className = className;
        this.startDate = startDate;
        this.endDate = endDate;
        this.classStatus = classStatus;
        this.instructorName = instructorName;
        this.instructorPhone = instructorPhone;
        this.instructorEmail = instructorEmail;
        this.classNotes = classNotes;
        this.termID = termID;
    }
    //Getter-Setter for Class ID
    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }
    //Getter-Setter for Class Name
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    //Getter-Setter for Start Date
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    //Getter-Setter for End Date
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    //Getter-Setter for Status
    public String getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(String classStatus) {
        this.classStatus = classStatus;
    }
    //Getter-Setter for Instructor Name
    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
    //Getter-Setter for Instructor Phone
    public String getInstructorPhone() {
        return instructorPhone;
    }

    public void setInstructorPhone(String instructorPhone) {
        this.instructorPhone = instructorPhone;
    }
    //Getter-Setter for Instructor Email
    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }
    //Getter-Setter for Notes
    public String getClassNotes() {
        return classNotes;
    }

    public void setClassNotes(String classNotes) {
        this.classNotes = classNotes;
    }
    //Getter-Setter for Term ID
    public int getTermID() {
        return termID;
    }

    public void setTermID(int termID) {
        this.termID = termID;
    }
}
