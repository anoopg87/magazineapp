package com.assesment.interfaces;
import com.assesment.model.Facts;
import retrofit2.Call;
import retrofit2.http.GET;


/*
Retrofit interface for the Asynchronous service call
getFacts returns Facts object
 */

public interface NewsServiceAPI {

    @GET("/u/746330/facts.json")
    Call<Facts> getFacts();
}
