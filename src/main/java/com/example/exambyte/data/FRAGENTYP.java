package com.example.exambyte.data;

public enum FRAGENTYP {
    FRAGENTYP_FREITEXT ("Freitext"),
    FRAGENTYP_MULTIPLECHOICE ("Multiple Choice");


    String title;
    FRAGENTYP(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
