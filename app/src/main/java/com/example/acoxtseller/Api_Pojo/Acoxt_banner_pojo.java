package com.example.acoxtseller.Api_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Acoxt_banner_pojo {

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


    public class Datum {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("root")
        @Expose
        private List<Root> root;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Root> getRoot() {
            return root;
        }

        public void setRoot(List<Root> root) {
            this.root = root;
        }

    }
    public class Root {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("discount")
        @Expose
        private String discount;
        @SerializedName("img")
        @Expose
        private String img;
        @SerializedName("parentId")
        @Expose
        private String parentId;
        @SerializedName("position")
        @Expose
        private String position;

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

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

    }
}
