package com.example.ewallpaper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static  Retrofit retrofit = null;
    public static final String API ="563492ad6f91700001000001db1c916067e44f83890f144896f938cc";

    public static ApiInterFace getApiInterFace()
    {
//        if (retrofit == null)
//        {
//            retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return  retrofit.create(ApiInterFace.class);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(ApiInterFace.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return
                retrofit.create(ApiInterFace.class);


    }

}


//         String BASE_URL ="https://newsapi.org/v2/";
//    private static Retrofit retrofit = null;
//    public static ApiInterFace getApiInterface() {
//
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder().baseUrl(ApiInterFace.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//
//        return
//                retrofit.create(ApiInterFace.class);