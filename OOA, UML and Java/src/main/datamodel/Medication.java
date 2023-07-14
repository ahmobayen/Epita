package main.datamodel;

public class Medication {
    private String medicationCode;
    private String medicationName;
    private String medicationComment;

    public Medication(String medicationCode, String medicationName, String medicationComment) {
        this.medicationCode = medicationCode;
        this.medicationName = medicationName;
        this.medicationComment = medicationComment;
    }

    public String getMedicationCode() {
        return medicationCode;
    }

    public void setMedicationCode(String medicationCode) {
        this.medicationCode = medicationCode;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getMedicationComment() {
        return medicationComment;
    }

    public void setMedicationComment(String medicationComment) {
        this.medicationComment = medicationComment;
    }
}
