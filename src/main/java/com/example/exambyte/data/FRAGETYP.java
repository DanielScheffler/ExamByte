package com.example.exambyte.data;

public enum FRAGETYP {
    FRAGETYP_FREITEXT("Freitext"),
    FRAGETYP_MULTIPLE_CHOICE("Multiple Choice");
    private String typ;
    FRAGETYP(String typ) {
        this.typ = typ;
    }
    public String getTyp() {
        return typ;
    }
}
