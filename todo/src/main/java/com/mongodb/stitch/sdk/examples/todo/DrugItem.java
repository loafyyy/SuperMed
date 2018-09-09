package com.mongodb.stitch.sdk.examples.todo;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

public class DrugItem {

    private ObjectId _id;
    private List<String> drugClass;
    private List<String> purpose;
    private List<String> USBrands;
    private String USIngredientName;
    private List<String> ChinaBrands;
    private String ChineseIngredientName;
    private List<String> FranceBrands;
    private String FrenchIngredientName;
    private List<String> GermanyBrands;
    private String GermanIngredientName;
    private List<String> ItalyBrands;
    private String ItalianIngredientName;
    private List<String> SpainBrands;
    private String SpanishIngredientName;
    private List<String> ThailandBrands;
    private String ThaiIngredientName;

    // picture
    private List<String> USPicture;
    private List<String> FrancePicture;

    public DrugItem(final Document document) {
        _id = document.getObjectId("_id");
        drugClass = (List<String>) document.get("drug-class");
        purpose = (List<String>) document.get("purpose");
        USBrands = (List<String>) document.get("US-brands");
        USIngredientName = document.getString("US-ingredient-name");
        ChinaBrands = (List<String>) document.get("China-brands");
        ChineseIngredientName = document.getString("Chinese-ingredient-name");
        FranceBrands = (List<String>) document.get("France-brands");
        FrenchIngredientName = document.getString("French-ingredient-name");
        GermanyBrands = (List<String>) document.get("Germany-brands");
        GermanIngredientName = document.getString("German-ingredient-name");
        ItalyBrands = (List<String>) document.get("Italy-brands");
        ItalianIngredientName = document.getString("Italian-ingredient-name");
        SpainBrands = (List<String>) document.get("Spain-brands");
        SpanishIngredientName = document.getString("Spanish-ingredient-name");
        ThailandBrands = (List<String>) document.get("Thailand-brands");
        ThaiIngredientName = document.getString("Thai-ingredient-name");

        USPicture = (List<String>) document.get("US-picture");
        FrancePicture = (List<String>) document.get("France-picture");
    }

    public List<String> getUSBrands() {
        return USBrands;
    }

    public List<String> getChinaBrands() {
        return ChinaBrands;
    }

    public List<String> getDrugClass() {
        return drugClass;
    }

    public List<String> getFranceBrands() {
        return FranceBrands;
    }

    public List<String> getGermanyBrands() {
        return GermanyBrands;
    }

    public List<String> getItalyBrands() {
        return ItalyBrands;
    }

    public List<String> getPurpose() {
        return purpose;
    }

    public List<String> getSpainBrands() {
        return SpainBrands;
    }

    public List<String> getThailandBrands() {
        return ThailandBrands;
    }

    public String getChineseIngredientName() {
        return ChineseIngredientName;
    }

    public String getFrenchIngredientName() {
        return FrenchIngredientName;
    }

    public String getGermanIngredientName() {
        return GermanIngredientName;
    }

    public String getItalianIngredientName() {
        return ItalianIngredientName;
    }

    public String getSpanishIngredientName() {
        return SpanishIngredientName;
    }

    public String getThaiIngredientName() {
        return ThaiIngredientName;
    }

    public String getUSIngredientName() {
        return USIngredientName;
    }

    public List<String> getFrancePicture() {
        return FrancePicture;
    }

    public List<String> getUSPicture() {
        return USPicture;
    }
}
