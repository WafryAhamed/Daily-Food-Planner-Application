package com.example.dishdiary.model;

import com.google.gson.annotations.SerializedName;


public class Category {

    @SerializedName("idCategory")
    String idCategory;

    @SerializedName("strCategory")
    String strCategory;

    @SerializedName("strCategoryThumb")
    String strCategoryThumb;

    @SerializedName("strCategoryDescription")
    String strCategoryDescription;


    public Category(String idCategory, String strCategory, String strCategoryThumb, String strCategoryDescription) {
        this.idCategory = idCategory;
        this.strCategory = strCategory;
        this.strCategoryThumb = strCategoryThumb;
        this.strCategoryDescription = strCategoryDescription;
    }


    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }
    public String getIdCategory() {
        return idCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }
    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategoryThumb(String strCategoryThumb) {
        this.strCategoryThumb = strCategoryThumb;
    }
    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }

    public void setStrCategoryDescription(String strCategoryDescription) {
        this.strCategoryDescription = strCategoryDescription;
    }
    public String getStrCategoryDescription() {
        return strCategoryDescription;
    }

}