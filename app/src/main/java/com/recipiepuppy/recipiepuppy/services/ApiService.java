package com.recipiepuppy.recipiepuppy.services;


import com.recipiepuppy.recipiepuppy.model.ItemModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sumit on 27/9/17.
 */

public interface ApiService {


    @GET("api/")
    Call<ItemModel> getReciepe(@Query("q") String str);

}
