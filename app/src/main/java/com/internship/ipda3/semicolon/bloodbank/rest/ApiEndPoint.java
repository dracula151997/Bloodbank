package com.internship.ipda3.semicolon.bloodbank.rest;

import android.os.Bundle;

import com.internship.ipda3.semicolon.bloodbank.model.donation.details.DonationDetails;
import com.internship.ipda3.semicolon.bloodbank.model.donation.request.CreateRequest;
import com.internship.ipda3.semicolon.bloodbank.model.donation.requests.DonationRequests;
import com.internship.ipda3.semicolon.bloodbank.model.general.cities.Cities;
import com.internship.ipda3.semicolon.bloodbank.model.general.contact.ContactUs;
import com.internship.ipda3.semicolon.bloodbank.model.general.governorates.Governorates;
import com.internship.ipda3.semicolon.bloodbank.model.general.report.Report;
import com.internship.ipda3.semicolon.bloodbank.model.general.settings.Settings;
import com.internship.ipda3.semicolon.bloodbank.model.login.Login;
import com.internship.ipda3.semicolon.bloodbank.model.logs.Logs;
import com.internship.ipda3.semicolon.bloodbank.model.notification.Token;
import com.internship.ipda3.semicolon.bloodbank.model.notification.count.NotificationCount;
import com.internship.ipda3.semicolon.bloodbank.model.notification.notifications.NotificationList;
import com.internship.ipda3.semicolon.bloodbank.model.notification.setting.NotificationSetting;
import com.internship.ipda3.semicolon.bloodbank.model.posts.details.PostDetials;
import com.internship.ipda3.semicolon.bloodbank.model.posts.favorite.FavoritePost;
import com.internship.ipda3.semicolon.bloodbank.model.posts.post.Posts;
import com.internship.ipda3.semicolon.bloodbank.model.posts.toggle.FavoritePostToggle;
import com.internship.ipda3.semicolon.bloodbank.model.register.Register;
import com.internship.ipda3.semicolon.bloodbank.model.users.password.change.NewPassword;
import com.internship.ipda3.semicolon.bloodbank.model.users.password.rest.RestPassword;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEndPoint {
    @POST("register")
    @FormUrlEncoded
    Call<Register> register(@Field("name")
                                    String name,
                            @Field("email")
                                    String email,
                            @Field("birth_date")
                                    String birth_date,
                            @Field("city_id")
                                    int id,
                            @Field("phone")
                                    String phone,
                            @Field("donation_last_date")
                                    String donation_last_date,
                            @Field("password")
                                    String password,
                            @Field("blood_type")
                                    String blood_type);

    @POST("login")
    @FormUrlEncoded
    Call<Login> login(@Field("phone")
                              String phone,
                      @Field("password")
                              String password);

    @POST("reset-password")
    @FormUrlEncoded
    Call<RestPassword> resetPassword(@Field("phone")
                                             String phone);

    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> newPassword(@Field("password")
                                          String password,
                                  @Field("password_confirmation")
                                          String confirmation,
                                  @Field("pin_code")
                                          String pinCode,
                                  @Field("phone")
                                          String phone);

    @POST("profile")
    @FormUrlEncoded
    Call<Login> updateProfile(@Field("name")
                                      String name,
                              @Field("email")
                                      String email,
                              @Field("birth_date")
                                      String birth_date,
                              @Field("city_id")
                                      int city_id,
                              @Field("phone")
                                      String phone,
                              @Field("donation_last_date")
                                      String donation_last_date,
                              @Field("password")
                                      String password,
                              @Field("password_confirmation")
                                      String password_confirmation,
                              @Field("blood_type")
                                      String blood_type,
                              @Field("api_token")
                                      String api_token);

    @POST("register-token")
    @FormUrlEncoded
    Call<Token> registerNotificationToken(@Field("token")
                                                  String token,
                                          @Field("api_token")
                                                  String api_token,
                                          @Field("platform")
                                                  String platform);

    Call<Token> removeNotificationToken(@Field("token")
                                                String token,
                                        @Field("api_token")
                                                String api_token);

    @POST("notifications-settings")
    @Headers("Accept: application/json")
    @FormUrlEncoded
    Call<NotificationSetting> notificationSettings(@Field("api_token")
                                                           String api_token,
                                                   @Field("cities[0]")
                                                           String[] cities,
                                                   @Field("blood_types[0]")
                                                           String[] blood_types);

    @GET("notifications")
    @Headers("Accept: application/json")
    Call<NotificationList> getNotificationList(@Query("api_token")
                                                       String api_token);

    @GET("notifications-count")
    @Headers("Accept: application/json")
    Call<NotificationCount> getNotificationCount(@Query("api_token")
                                                         String api_token);

    @POST("donation-request/create")
    @FormUrlEncoded
    Call<CreateRequest> createDonationRequest(@Field("api_token")
                                                      String api_token,
                                              @Field("patient_name")
                                                      String patient_name,
                                              @Field("patient_age")
                                                      String patient_age,
                                              @Field("blood_type")
                                                      String blood_type,
                                              @Field("bags_num")
                                                      String bags_num,
                                              @Field("hospital_name")
                                                      String hospital_name,
                                              @Field("hospital_address")
                                                      String hospital_address,
                                              @Field("city_id")
                                                      Integer city_id,
                                              @Field("phone")
                                                      String phone,
                                              @Field("notes")
                                                      String notes,
                                              @Field("latitude")
                                                      Double latitude,
                                              @Field("longitude")
                                                      Double longitude);

    @GET("donation-requests")
    @Headers("Accept: application/json")
    Call<DonationRequests> getDonationRequests(@Query("api_token")
                                                       String api_token);

    @GET("donation-request")
    @Headers("Accept: application/json")
    Call<DonationDetails> getDonationDetails(@Query("api_token")
                                                     String api_token,
                                             @Query("donation_id")
                                                     String donation_id);

    @GET("posts")
    @Headers("Accept: application/json")
    Call<Posts> getPosts(@Query("api_token")
                                 String api_token,
                         @Query("page")
                                 int page);

    @GET("post")
    @Headers("Accept: application/json")
    Call<PostDetials> getPostDetails(@Query("api_token")
                                             String api_token,
                                     @Query("post_id")
                                             String post_id);

    @GET("my-favourites")
    @Headers("Accept: application/json")
    Call<FavoritePost> getFavouritesList(@Query("api_token")
                                                 String api_token);

    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<FavoritePostToggle> postToggleFavourite(@Field("post_id")
                                                         String post_id,
                                                 @Field("api_token")
                                                         String api_token);

    @GET("governorates")
    Call<Governorates> getGovernorates();

    @GET("cities")
    Call<Cities> getCities(@Query("governorate_id")
                                   long governorate_id);

    @GET("settings")
    @Headers("Accept: application/json")
    Call<Settings> settings(@Query("api_token")
                                    String api_token);

    @POST("contact")
    @FormUrlEncoded
    Call<ContactUs> contactUs(@Field("api_token")
                                      String api_token,
                              @Field("title")
                                      String title,
                              @Field("message")
                                      String message);

    @POST("report")
    @FormUrlEncoded
    Call<Report> report(@Field("api_token")
                                String api_token,
                        @Field("message")
                                String message);

    @GET("logs")
    Call<Logs> logs();


}
