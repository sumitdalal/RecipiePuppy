package com.recipiepuppy.recipiepuppy.presenters;

import android.os.Handler;

import com.recipiepuppy.recipiepuppy.contracts.IApiInteractor;
import com.recipiepuppy.recipiepuppy.contracts.IViewInteractor;
import com.recipiepuppy.recipiepuppy.model.ItemModel;
import com.recipiepuppy.recipiepuppy.services.ApiController;

import java.util.List;

/**
 * Created by sumit on 4/10/17.
 */

public class ItemPresenter implements IApiInteractor {
    private IViewInteractor iViewInteractor;
    private ApiController apiController;

    public ItemPresenter(IViewInteractor iViewInteractor) {
        this.iViewInteractor = iViewInteractor;
        apiController = new ApiController(this);
    }

    public void getItems(String input, Handler handler) {
        apiController.getDataServer(input, handler);
    }

    @Override
    public void onSuccess(List<ItemModel.Result> results) {
        iViewInteractor.refreshAdapter(results);

    }

    @Override
    public void onFailure(String message) {

    }
}
