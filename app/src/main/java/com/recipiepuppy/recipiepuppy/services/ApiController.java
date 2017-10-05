package com.recipiepuppy.recipiepuppy.services;

import android.os.Handler;

import com.recipiepuppy.recipiepuppy.contracts.IApiInteractor;
import com.recipiepuppy.recipiepuppy.model.ItemModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by sumit on 27/9/17.
 */

public class ApiController {
    private IApiInteractor iApiInteractor;
    private int RESULT_CODE = 200;
    private int DELAY_MILISEC = 500;

    public ApiController(IApiInteractor iApiInteractor) {
        this.iApiInteractor = iApiInteractor;
    }

    // Taking handler to delay the api for 500 miliseconds

    public void getDataServer(final String input, Handler handler
    ) {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    getRecipiesfromServer(input);
                }
            }, DELAY_MILISEC);
        } else
            getRecipiesfromServer(input);
    }

    // Api to fetch data from server
    private void getRecipiesfromServer(String input) {
        ApiService apiService = ConnectionFactory.getInstance().getRetroService();
        Call<ItemModel> recepieModelCall = apiService.getReciepe(input);
        recepieModelCall.enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                if (response != null) {
                    if (response.code() == RESULT_CODE)
                        iApiInteractor.onSuccess(response.body().getResults());
                    else
                        iApiInteractor.onFailure(response.message());

                } else {
                    iApiInteractor.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                iApiInteractor.onFailure(t.getMessage());
            }

        });
    }
}
