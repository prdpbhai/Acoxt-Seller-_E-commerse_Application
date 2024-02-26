package com.example.acoxtseller.Api_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profile_show_data_Pojo {
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("user")
    @Expose
    private User user;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public class ShopAddress {

        @SerializedName("addStreetAndBuildingNumber")
        @Expose
        private String addStreetAndBuildingNumber;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("pinCode")
        @Expose
        private String pinCode;
        @SerializedName("state")
        @Expose
        private String state;

        public String getAddStreetAndBuildingNumber() {
            return addStreetAndBuildingNumber;
        }

        public void setAddStreetAndBuildingNumber(String addStreetAndBuildingNumber) {
            this.addStreetAndBuildingNumber = addStreetAndBuildingNumber;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPinCode() {
            return pinCode;
        }

        public void setPinCode(String pinCode) {
            this.pinCode = pinCode;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }


        public class User {

            @SerializedName("_id")
            @Expose
            private String id;
            @SerializedName("dob")
            @Expose
            private String dob;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("fullname")
            @Expose
            private String fullname;
            @SerializedName("gender")
            @Expose
            private String gender;
            @SerializedName("gstNo")
            @Expose
            private String gstNo;
            @SerializedName("gst_otp")
            @Expose
            private Integer gstOtp;
            @SerializedName("img")
            @Expose
            private String img;
            @SerializedName("phone")
            @Expose
            private String phone;
            @SerializedName("qrimg")
            @Expose
            private String qrimg;
            @SerializedName("role")
            @Expose
            private String role;
            @SerializedName("shopAddress")
            @Expose
            private List<ShopAddress> shopAddress;
            @SerializedName("storeName")
            @Expose
            private String storeName;
            @SerializedName("uniqueId")
            @Expose
            private String uniqueId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDob() {
                return dob;
            }

            public void setDob(String dob) {
                this.dob = dob;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getFullname() {
                return fullname;
            }

            public void setFullname(String fullname) {
                this.fullname = fullname;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getGstNo() {
                return gstNo;
            }

            public void setGstNo(String gstNo) {
                this.gstNo = gstNo;
            }

            public Integer getGstOtp() {
                return gstOtp;
            }

            public void setGstOtp(Integer gstOtp) {
                this.gstOtp = gstOtp;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getQrimg() {
                return qrimg;
            }

            public void setQrimg(String qrimg) {
                this.qrimg = qrimg;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public List<ShopAddress> getShopAddress() {
                return shopAddress;
            }

            public void setShopAddress(List<ShopAddress> shopAddress) {
                this.shopAddress = shopAddress;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getUniqueId() {
                return uniqueId;
            }

            public void setUniqueId(String uniqueId) {
                this.uniqueId = uniqueId;
            }
        }
}
