package com.example.acoxtseller.Api_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Show_banner_pojo {

    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("data")
    @Expose
    private List<Datum> data;

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


    public class Datum {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("archive")
        @Expose
        private Boolean archive;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("dis")
        @Expose
        private Integer dis;
        @SerializedName("discount")
        @Expose
        private Integer discount;
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
        @SerializedName("unitType")
        @Expose
        private String unitType;
        @SerializedName("unitValue")
        @Expose
        private String unitValue;
        @SerializedName("wishlist")
        @Expose
        private Boolean wishlist;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Boolean getArchive() {
            return archive;
        }

        public void setArchive(Boolean archive) {
            this.archive = archive;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getDis() {
            return dis;
        }

        public void setDis(Integer dis) {
            this.dis = dis;
        }

        public Integer getDiscount() {
            return discount;
        }

        public void setDiscount(Integer discount) {
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

        public String getUnitType() {
            return unitType;
        }

        public void setUnitType(String unitType) {
            this.unitType = unitType;
        }

        public String getUnitValue() {
            return unitValue;
        }

        public void setUnitValue(String unitValue) {
            this.unitValue = unitValue;
        }

        public Boolean getWishlist() {
            return wishlist;
        }

        public void setWishlist(Boolean wishlist) {
            this.wishlist = wishlist;
        }

    }
}
