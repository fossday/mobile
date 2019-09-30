package org.fossday.Model;

public class Lecture {

    String name;
    String description;
    String period;
    int time;
    String room;
    int speakerId;

    public Lecture() {}

    public Lecture(String name, String description, String period, int time, String room, int speakerId) {
        this.name = name;
        this.description = description;
        this.period = period;
        this.time = time;
        this.room = room;
        this.speakerId = speakerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }
}

