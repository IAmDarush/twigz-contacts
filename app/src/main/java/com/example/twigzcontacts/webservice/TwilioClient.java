package com.example.twigzcontacts.webservice;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by darush on 2/27/18.
 */

public interface TwilioClient {

    @FormUrlEncoded
    @POST("/2010-04-01/Accounts/{ACCOUNT_SID}/Messages.json")
    Call<ResponseBody> sendMessage(
            @Path("ACCOUNT_SID") String accountSId,
            @Header("Authorization") String signature,
            @FieldMap Map<String, String> metadata
            );


}
