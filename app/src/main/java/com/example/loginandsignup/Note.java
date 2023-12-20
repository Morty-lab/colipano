package com.example.loginandsignup;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Note implements Serializable {
    private String key;
    private String noteTitle;
    private String noteContent; // Updated field name

    public Note(String key, String title, String content) {
        this.key = key;
        this.noteTitle = title;
        this.noteContent = content;
    }

    public Note() {
        // Default constructor required for calls to DataSnapshot.getValue(Note.class)
    }

    // getters and setters
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getnoteTitle() {
        return noteTitle;
    }

    public void setnoteTitle(String title) {
        this.noteTitle = title;
    }

    public String getnoteContent() {
        return noteContent;
    }

    public void setnoteContent(String content) {
        this.noteContent = content;
    }
}
