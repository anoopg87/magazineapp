package com.assesment.PresenterImplementation;

import com.assesment.interfaces.NewsListView;
import com.assesment.interfaces.NewsServiceAPI;
import com.assesment.magazineapp.MagazineApp;
import com.assesment.model.Facts;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Retrofit service implementation
 */

public class ServiceImpl {

     public  void invokeFactsApi(final NewsListView presenter){

         // Creating NewServiceAPI instance using the Global retrofit object
         NewsServiceAPI newsServiceAPI = MagazineApp.getRetrofit().create(NewsServiceAPI.class);
         Call<Facts> call= newsServiceAPI.getFacts();

         // clone is used for calling the same url with same parameters to the same server multiple time
         call.clone().enqueue(new Callback<Facts>() {
             @Override
             public void onResponse(Response<Facts> response) {
                 if(response.isSuccess()){

                     // On success call it return the body to the presenter
                   presenter.onSuccess(response.body());
                 }
             }
             @Override
             public void onFailure(Throwable t) {

                 // on error it send error notification to the presneter

                 presenter.onError();
             }


         });



    }
}
