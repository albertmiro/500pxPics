package com.albertmiro.pics500px.data.ws;

import com.albertmiro.pics500px.data.model.Pictures;

import org.androidannotations.annotations.EBean;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean(scope = EBean.Scope.Singleton)
public class RetrofitInit {

    WSCalls wsCalls;
    Retrofit retrofit;

    public void initRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.500px.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            wsCalls = retrofit.create(WSCalls.class);
        }
    }


    public Call<Pictures> getPictures() {
        return wsCalls.getPictures();
    }
}
