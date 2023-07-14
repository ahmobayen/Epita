package main.datamodel;

public class Prescription {
    private String prescID;
    private String prescRefPat;
    private String prescCode;
    private String prescDays;

    public Prescription(String prescID, String prescRefPat, String prescCode, String prescDays) {
        this.prescID = prescID;
        this.prescRefPat = prescRefPat;
        this.prescCode = prescCode;
        this.prescDays = prescDays;
    }

    public String getPrescID() {
        return prescID;
    }

    public void setPrescID(String prescID) {
        this.prescID = prescID;
    }

    public String getPrescRefPat() {
        return prescRefPat;
    }

    public void setPrescRefPat(String prescRefPat) {
        this.prescRefPat = prescRefPat;
    }

    public String getPrescCode() {
        return prescCode;
    }

    public void setPrescCode(String prescCode) {
        this.prescCode = prescCode;
    }

    public String getPrescDays() {
        return prescDays;
    }

    public void setPrescDays(String prescDays) {
        this.prescDays = prescDays;
    }
}
