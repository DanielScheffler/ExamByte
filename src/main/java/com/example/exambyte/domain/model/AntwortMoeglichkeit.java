package com.example.exambyte.domain.model;

public class AntwortMoeglichkeit {
    private String text;
    private boolean istWahr;

    public AntwortMoeglichkeit() {}

    public AntwortMoeglichkeit(String text, boolean istWahr) {
        this.text = text;
        this.istWahr = istWahr;
    }

    public boolean isIstWahr() {
        return istWahr;
    }

    public String getText() {
        return text;
    }

    public String text() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean istWahr() {
        return istWahr;
    }

    public void setIstWahr(boolean istWahr) {
        this.istWahr = istWahr;
    }
}
