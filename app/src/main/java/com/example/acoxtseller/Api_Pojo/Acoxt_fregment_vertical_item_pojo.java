package com.example.acoxtseller.Api_Pojo;

import android.content.Context;

import com.example.acoxtseller.DB_Helper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Acoxt_fregment_vertical_item_pojo {

    public class Datum {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("finalprice")
        @Expose
        private String finalprice;
        @SerializedName("img")
        @Expose
        private List<String> img;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("quantity")
        @Expose
        private Integer quantity;
        @SerializedName("subcategory")
        @Expose
        private String subcategory;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("unit")
        @Expose
        private String unit;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getFinalprice() {
            return finalprice;
        }

        public void setFinalprice(String finalprice) {
            this.finalprice = finalprice;
        }

        public List<String> getImg() {
            return img;
        }

        public void setImg(List<String> img) {
            this.img = img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(String subcategory) {
            this.subcategory = subcategory;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

    }

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private List<Datum> data;
    @SerializedName("msg")
    @Expose
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
