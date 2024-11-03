package com.example.exambyte.data;

public enum FRAGETYP {
    FRAGETYP_FREITEXT("Freitext"),
    FRAGETYP_MULTIPLE_CHOICE("Multiple Choice");
    private String title;
    FRAGETYP(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
