package com.mongodb.stitch.sdk.examples.todo;

public class DrugPreview {
    private String drugName;
    private String drugImageURL;

    public DrugPreview(String drugName, String drugImageURL) {
        this.drugName = drugName;
        this.drugImageURL = drugImageURL;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getDrugImageURL() {
        return drugImageURL;
    }
}
