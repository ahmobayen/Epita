package main.datamodel;

public class Insurance {
    private Integer insurance_id;
    private String insurance_name;

    public Insurance() {
    }

    public Insurance(Integer insurance_id, String insurance_name) {
        this.insurance_id = insurance_id;
        this.insurance_name = insurance_name;
    }

    public Integer getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(Integer insurance_id) {
        this.insurance_id = insurance_id;
    }

    public String getInsurance_name() {
        return insurance_name;
    }

    public void setInsurance_name(String insurance_name) {
        this.insurance_name = insurance_name;
    }
}
