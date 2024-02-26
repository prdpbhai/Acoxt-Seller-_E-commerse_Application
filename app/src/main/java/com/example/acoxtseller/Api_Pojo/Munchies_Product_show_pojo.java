package com.example.acoxtseller.Api_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Munchies_Product_show_pojo {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("productData")
    @Expose
    private List<ProductDatum> productData;
    @SerializedName("subcategoryData")
    @Expose
    private List<SubcategoryDatum> subcategoryData;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<ProductDatum> getProductData() {
        return productData;
    }

    public void setProductData(List<ProductDatum> productData) {
        this.productData = productData;
    }

    public List<SubcategoryDatum> getSubcategoryData() {
        return subcategoryData;
    }

    public void setSubcategoryData(List<SubcategoryDatum> subcategoryData) {
        this.subcategoryData = subcategoryData;
    }

    public class ProductDatum {

        @SerializedName("_id")
        @Expose
        private String id;
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

    public class SubcategoryDatum {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("img")
        @Expose
        private String img;
        @SerializedName("parentId")
        @Expose
        private String parentId;
        @SerializedName("subcategory")
        @Expose
        private String subcategory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(String subcategory) {
            this.subcategory = subcategory;
        }

    }

}
