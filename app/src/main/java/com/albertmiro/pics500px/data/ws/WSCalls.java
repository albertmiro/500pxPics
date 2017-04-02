package com.albertmiro.pics500px.data.ws;


import com.albertmiro.pics500px.data.model.Pictures;
import com.albertmiro.pics500px.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WSCalls {

    @GET("photos" +
            "?consumer_key="+ Constants.CONSUMER_KEY_500PX +
            "&feature=user" +
            "&username=albertmiro" +
            "&sort=created_at" +
            "&image_size=4")
    Call<Pictures> getPictures();

}
