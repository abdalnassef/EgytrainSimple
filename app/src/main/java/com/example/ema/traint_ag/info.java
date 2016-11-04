package com.example.ema.traint_ag;

import android.app.Activity;
import android.content.res.Resources;

/**
 * Created by EMA on 06/05/2016.
 */

public class info extends Activity{
    private String from;
    private String to;
    private String timestart;
    private String timearrive;
    private Kind kind;
    private int salary;
    public info (String f,String t){
        from=f;
        to=t;
    }
    public info (String timestart,String timearrive,Kind kind,int salary){
         this.timestart=timestart;
        this.timearrive=timearrive;
        this.kind=kind;
        this.salary=salary;
    }
    public void infoo(){
        String xx=from+"-"+to;
    }
    public String getTimestart() {
        return timestart;
    }

    public void setTimestart(String timestart) {
        this.timestart = timestart;
    }

    public String getTimearrive() {
        return timearrive;
    }

    public void setTimearrive(String timearrive) {
        this.timearrive = timearrive;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
