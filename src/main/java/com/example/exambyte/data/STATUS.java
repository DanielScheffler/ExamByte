package com.example.exambyte.data;

public enum STATUS {
    STATUS_AUSSTEHEND("Ausstehend"),
    STATUS_BEARBEITBAR("Bearbeitbar"),
    STATUS_IN_BEARBEITUNG("In Bearbeitung"),
    STATUS_BESTANDEN("Bestanden"),
    STATUS_NICHT_BESTANDEN("Nicht Bestanden");

    private String title;

    STATUS(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
