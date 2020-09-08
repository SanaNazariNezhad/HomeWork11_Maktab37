package org.maktab.homework11_maktab37.controller.model;

import java.util.Date;
import java.util.UUID;

public class Task {
    private String mTitle;
    private String mDescription;
    private Date mDate;
    private String mState;
    private UUID mId;

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Task(String title, String description, Date date, String state) {
        mTitle = title;
        mDescription = description;
        mDate = date;
        mState = state;
        mId = UUID.randomUUID();
    }
}
