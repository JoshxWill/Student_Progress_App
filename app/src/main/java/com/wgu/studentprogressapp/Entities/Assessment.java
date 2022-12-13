package com.wgu.studentprogressapp.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Assessment Entity
 *
 * @author Joshua Williams
 */
@Entity(tableName = "table_assessment")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentName;
    private String assessmentType;
    private String endDate;
    private int classID;

    public Assessment(int assessmentID, String assessmentName, String assessmentType, String endDate, int classID){
        this.assessmentID = assessmentID;
        this.assessmentName = assessmentName;
        this.assessmentType = assessmentType;
        this.endDate = endDate;
        this.classID = classID;
    }
    //Getter-Setter for Class ID
    public int getClassID(){
        return classID;
    }

    public void setClassID(int classID){
        this.classID = classID;
    }
    //Getter-Setter for Assessment ID
    public int getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }
    //Getter-Setter for Assessment Name
    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }
    //Getter-Setter for Assessment Type
    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }
    //Getter-Setter for End Date
    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
