package com.example.acoxtseller.Web_Service;

import com.example.acoxtseller.Api_Pojo.Accept_customer_order_pojo;
import com.example.acoxtseller.Api_Pojo.Acoxt_banner_pojo;
import com.example.acoxtseller.Api_Pojo.Acoxt_freg_offer_pojo;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_horizental_item_pojo;
import com.example.acoxtseller.Api_Pojo.Acoxt_fregment_vertical_item_pojo;
import com.example.acoxtseller.Api_Pojo.Add_product_in_cart_pojo;
import com.example.acoxtseller.Api_Pojo.Add_product_pojo;
import com.example.acoxtseller.Api_Pojo.Cart_data_show_pojo;
import com.example.acoxtseller.Api_Pojo.Checkout_pojo;
import com.example.acoxtseller.Api_Pojo.Decrement_cart_item_pojo;
import com.example.acoxtseller.Api_Pojo.Delivery_confirm_otp_pojo;
import com.example.acoxtseller.Api_Pojo.Email_login_pojo;
import com.example.acoxtseller.Api_Pojo.Email_verify_pojo;
import com.example.acoxtseller.Api_Pojo.Forget_password_verify_password;
import com.example.acoxtseller.Api_Pojo.Forget_password_verify_pojo;
import com.example.acoxtseller.Api_Pojo.GST_Verify_pojo;
import com.example.acoxtseller.Api_Pojo.Generate_QR_pojo;
import com.example.acoxtseller.Api_Pojo.Google_login_pojo;
import com.example.acoxtseller.Api_Pojo.Gst_Otp_Verify_pojo;
import com.example.acoxtseller.Api_Pojo.Increment_cart_item_pojo;
import com.example.acoxtseller.Api_Pojo.Inventory_show_pojo;
import com.example.acoxtseller.Api_Pojo.Login_pojo;
import com.example.acoxtseller.Api_Pojo.Low_Stock_Pojo;
import com.example.acoxtseller.Api_Pojo.Map_pojo;
import com.example.acoxtseller.Api_Pojo.Munchies_Product_show_pojo;
import com.example.acoxtseller.Api_Pojo.MyStore_POJO;
import com.example.acoxtseller.Api_Pojo.New_pass_genrate_pojo;
import com.example.acoxtseller.Api_Pojo.Notification_pojo;
import com.example.acoxtseller.Api_Pojo.Order_status_update_pojo;
import com.example.acoxtseller.Api_Pojo.Out_of_stock_pojo;
import com.example.acoxtseller.Api_Pojo.Profile_show_data_Pojo;
import com.example.acoxtseller.Api_Pojo.Profile_update_pojo;
import com.example.acoxtseller.Api_Pojo.Registration_pojo_class;
import com.example.acoxtseller.Api_Pojo.Registration_register_number_pojo;
import com.example.acoxtseller.Api_Pojo.Search_pojo;
import com.example.acoxtseller.Api_Pojo.Select_fixed_seller_pojo;
import com.example.acoxtseller.Api_Pojo.Show_banner_pojo;
import com.example.acoxtseller.Api_Pojo.Show_order_detail_management_customer_pojo;
import com.example.acoxtseller.Api_Pojo.Store_Name_Show_pojo;
import com.example.acoxtseller.Api_Pojo.Store_name_address_pojo;
import com.example.acoxtseller.Api_Pojo.Text_forget_pass_pojo;
import com.example.acoxtseller.Api_Pojo.Update_order_status_pojo;
import com.example.acoxtseller.Api_Pojo.Verify_otp_pojo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class Web_Service_login {
    private static WebServiceInterface webApiInterface;
    public static WebServiceInterface getClient() {
        if (webApiInterface == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okclient = new OkHttpClient.Builder()
                    .addInterceptor(logging)

                    .connectTimeout(10, TimeUnit.MINUTES)
                    .readTimeout(10, TimeUnit.MINUTES)
                    .writeTimeout(10, TimeUnit.MINUTES)
                    .build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .setLenient()
                    .create();

            Retrofit client = new Retrofit.Builder()
//                    .baseUrl("https://api.acoxt.fourbrick.in/")
                    .baseUrl("https://2ecf-119-82-81-122.ngrok-free.app")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okclient)
                    .build();

            webApiInterface = client.create(WebServiceInterface.class);
        }
        return webApiInterface;

    }

    public interface WebServiceInterface {
        @POST("generate_otp")
        @FormUrlEncoded
        Call<Login_pojo> pojo(
                @Field("phone") String Phonenumber,
                @Field("type") String type
        );


        @POST("verify_otp")
        @FormUrlEncoded
        Call<Verify_otp_pojo> verify_otp(
                @Field("phone") String PhoneNumber,
                @Field("otp") String otp,
                @Field("role") String role
        );

        @POST("generate_gst_otp")
        @FormUrlEncoded
        Call<GST_Verify_pojo> gst_verify(
                @Header("Authorization") String Token,
                @Field("gst") String Gst_number
        );

        @POST("verify_gst_otp")
        @FormUrlEncoded
        Call<Gst_Otp_Verify_pojo> gst_otp_verify(
                @Header("Authorization") String Token,
                @Field("gst") String gst_num,
                @Field("otp") String gst_otp
        );

        @GET()
        Call<Acoxt_fregment_vertical_item_pojo> acoxt_fregment_pojo(
                @Header("Authorization") String Token,
                @Url String url

        );

        @GET()
        Call<Acoxt_fregment_horizental_item_pojo> acoxt_horzental_pojo(
                @Header("Authorization") String Token,
                @Url String url
        );

        @POST("storename")
        @FormUrlEncoded
        Call<MyStore_POJO> addstore(
                @Header("Authorization") String Token,
                @Field("YourStoreName") String storename
        );


        //        @POST("storename")
//        @FormUrlEncoded
//        Call<MyStore_POJO>compliteadress(
//                @Header("Authorization")String Token,
//                @Field("PinCode")String storename,
//                @Field("City")String City,
//                @Field("State")String State,
//                @Field("AddStreetAndBuildingNumber")String AddStreetAndBuildingNumber
//        );
        @POST("shipmethod ")
        @FormUrlEncoded
        Call<MyStore_POJO> shipmethod(
                @Header("Authorization") String Token,
                @Field("ShippingMethod") String storename
        );

        @POST("ShippingMethod")
        @FormUrlEncoded
        Call<Select_fixed_seller_pojo> fixed_seller_pojo(
                @Header("Authorization") String Token,
                @Field("userType") String user_type
        );

        @GET()
        Call<Generate_QR_pojo> generate_QR(
                @Header("Authorization") String Token,
                @Url String URL
        );

        //        @POST("signup_for_app")
//        @FormUrlEncoded
//        Call<Registration_pojo_class>registration_pojo(
//                @Field("fullName") String fullName,
//                @Field("phone")String phone_num,
//                @Field("email")String email_adr,
//                @Field("password")String password,
//                @Field("otp")String otp
//        );
        @POST("location")
        @FormUrlEncoded
        Call<Map_pojo> map_pojo(
                @Header("Authorization") String Token,
                @Field("latitude") Double latitude,
                @Field("longitude") Double longitude
        );

        @POST("signup_for_app")
        @FormUrlEncoded
        Call<Registration_pojo_class> registration_pojo_class(
                @Field("fullName") String name,
                @Field("phone") String phone,
                @Field("email") String email,
                @Field("password") String password,
                @Field("userType") String userType,
                @Field("phoneOtp") String phoneOtp,
                @Field("phoneOtp") String emailOtp
        );

        @POST("login")
        @FormUrlEncoded
        Call<Email_login_pojo> email_login_pojo(
                @Field("email") String email,
                @Field("password") String password,
                @Field("role") String role
        );

        @POST("forget_password")
        Call<Text_forget_pass_pojo> text_forget_pass_pojo(
                @Body RequestBody Text_forget_pass_pojo
        );

        @POST("verify_code")
        Call<Forget_password_verify_password> forget_pass_verify(
                @Body RequestBody Forget_password_verify_password
        );

        @POST("verify_code")
        Call<Forget_password_verify_pojo> forget_password_verify_pojo(
                @Body RequestBody Forget_password_verify_password
        );

        @POST("reset_password")
        Call<New_pass_genrate_pojo> new_pass_genrate_pojo(
                @Body RequestBody New_pass_genrate_pojo
        );

        @POST("validatePhone")
        @FormUrlEncoded
        Call<Registration_register_number_pojo> registration_register_number(
                @Field("phone") String phone
        );

        @POST("validateEmail")
        @FormUrlEncoded
        Call<Email_verify_pojo> email_verify_pojo(
                @Field("email") String email
        );

        @GET()
        Call<Store_Name_Show_pojo> store_name_show_pojo(
                @Header("Authorization") String Token,
                @Url String URL
        );

        @POST("ShopAddress")
        @FormUrlEncoded
        Call<Store_name_address_pojo> store_name_address_pojo(
                @Header("Authorization") String Token,
                @Field("PinCode") String pincode,
                @Field("City") String city,
                @Field("State") String state,
                @Field("AddStreetAndBuildingNumber") String building_num
        );

        @GET()
        Call<Profile_show_data_Pojo> profile_show_data_pojo(
                @Header("Authorization") String Token,
                @Url String URL
        );

//        @PUT("profile_update")
//        @FormUrlEncoded
//        Call<Profile_update_pojo>profile_update_pojo(
//                @Header("Authorization")String Token,
//                @Field("img") MultipartBody image,
//                @Field("fullname") String name,
//                @Field("email") String mail,
//                @Field("phone") String phone_number,
//                @Field("gstNo") String gst_no,
//                @Field("gender") String gender,
//                @Field("dob") String dob
//
//        );

        @PUT("profile_update")
        @Multipart
        Call<Profile_update_pojo> edit_profile(
                @Header("Authorization")String Token,
                @Part("fullname") RequestBody username,
                @Part("email") RequestBody email,
                @Part("phone") RequestBody phone,
                @Part("gstNo") RequestBody gst_no,
                @Part("gender") RequestBody gender,
                @Part("dob") RequestBody dob
//                @Part MultipartBody.Part image

        );

        @POST("ssologin")
        @FormUrlEncoded
        Call<Google_login_pojo> ssologin(
                @Field("fullName") String fullname,
                @Field("phone") String mobile,
                @Field("email") String email,
                @Field("password") String password,
                @Field("userType") String usertype
        );

        @GET()
        Call<Munchies_Product_show_pojo>munchies_product_show_pojo(
                @Header("Authorization") String Token,
                @Url String url
        );

        @GET()
        Call<Add_product_pojo>add_product_pojo(
                @Header("Authorization") String Token,
                @Url String url
        );
        @POST("addtocard/{ulr}")
        Call<Add_product_in_cart_pojo>add_product_cart_pojo(
                @Header("Authorization") String Token,
                @Path(value = "ulr") String ulr
                );
        @GET()
        Call<Cart_data_show_pojo>cart_data_pojo(
                @Header("Authorization") String Token,
                @Url String url
        );
        @GET()
        Call<Decrement_cart_item_pojo>decrement_cart_item_pojo(
                @Header("Authorization") String Token,
                @Url String url
        );
        @GET()
        Call<Increment_cart_item_pojo>increment_cart_item_pojo(
                @Header("Authorization") String Token,
                @Url String url
        );
        @POST("fixedSellerInventory")
            Call<Checkout_pojo>checkout_pojo(
                @Header("Authorization") String Token,
                @Body RequestBody reqedjsf
            );

        @GET()
        Call<Show_order_detail_management_customer_pojo>show_order_detail_management_customer(
                @Header("Authorization") String Token,
                @Url String url
        );

        @PUT()
        @FormUrlEncoded
        Call<Accept_customer_order_pojo>accept_customer_order_pojo(
                @Header("Authorization") String Token,
                @Url String url,
                @Field("response") String tr
                        );

        @PUT()
        @FormUrlEncoded
        Call<Update_order_status_pojo>update_order_status_pojo(
                @Header("Authorization") String Token,
                @Url String url,
                @Field("response") String tr
        );

        @GET()
        Call<Inventory_show_pojo>inventory_show_pojo(
                @Header("Authorization") String Token,
                @Url String url
        );

        @GET()
        Call<Low_Stock_Pojo>low_stock_pojo(
                @Header("Authorization") String Token,
                @Url String url,
                @Query("q") String stock
        );
        @GET()
        Call<Out_of_stock_pojo>out_of_stock_pojo(
                @Header("Authorization") String Token,
                @Url String url,
                @Query("q") String stock
        );


        @GET()
        Call<Acoxt_freg_offer_pojo>acoxt_freg_offer_pojo(
                @Url String url
        );
        @GET()
        Call<Acoxt_banner_pojo>acoxt_banner_pojo(
                @Url String url
        );

        @GET()
        Call<Search_pojo>search_pojo(
                @Url String url,
                @Query("productname") String stock
        );

        @GET()
        Call<Notification_pojo>notification_pojo(
                @Url String url
        );
        @PUT("orderRequestUpdate")
        @FormUrlEncoded
        @Multipart
        Call<Order_status_update_pojo> order_status_update_pojo(
                @Url String url,
                @Field("response") String tr
        );

        @POST("verifyDeliveryCode/{product_id}")
        @FormUrlEncoded
        Call<Delivery_confirm_otp_pojo> delivery_confirm_otp_pojo(
                @Path("product_id") String productId,
                @Field("otp") String otp
        );

        @GET()
        Call<Show_banner_pojo>show_banner_pojo(
                @Url String url
        );


    }
}
