package com.example.acoxtseller.Api_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart_data_show_pojo {
    @SerializedName("data")
    @Expose
    private List<Datum> data;
    @SerializedName("total")
    @Expose
    private Integer total;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public class Datum {

        @SerializedName("_id")
        @Expose
        private String id;
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
}
