package com.example.acoxtseller.Api_Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category_Acoxt_Fregmrnt_vertical {

        @SerializedName("data")
        @Expose
        private List<Acoxt_fregment_vertical_item_pojo.Datum> data;
        @SerializedName("msg")
        @Expose
        private String msg;

        public List<Acoxt_fregment_vertical_item_pojo.Datum> getData() {
            return data;
        }

        public void setData(List<Acoxt_fregment_vertical_item_pojo.Datum> data) {
            this.data = data;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }


}
