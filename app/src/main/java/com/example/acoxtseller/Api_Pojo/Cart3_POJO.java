package com.example.acoxtseller.Api_Pojo;


public class Cart3_POJO {


    String title;
    String productid;
    Double price;
    Double disprice;

    String img;
    String discription;
    String qunitity;

    public Cart3_POJO(String title, String productid, Double price, Double disprice, String img, String discription, String qunitity) {
        this.title = title;
        this.productid = productid;
        this.price = price;
        this.disprice = disprice;
        this.img = img;
        this.discription = discription;
        this.qunitity = qunitity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDisprice() {
        return disprice;
    }

    public void setDisprice(Double disprice) {
        this.disprice = disprice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getQunitity() {
        return qunitity;
    }

    public void setQunitity(String qunitity) {
        this.qunitity = qunitity;
    }
}
