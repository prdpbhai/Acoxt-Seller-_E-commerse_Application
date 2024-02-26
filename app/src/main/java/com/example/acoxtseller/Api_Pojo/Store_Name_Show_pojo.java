package com.example.acoxtseller.Api_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store_Name_Show_pojo {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("sellerType")
    @Expose
    private String sellerType;
    @SerializedName("storeName")
    @Expose
    private String storeName;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

}
