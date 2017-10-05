package com.recipiepuppy.recipiepuppy.contracts;


import com.recipiepuppy.recipiepuppy.model.ItemModel;

import java.util.List;

/**
 * Created by sumit on 4/10/17.
 */

public interface IApiInteractor {
    void onSuccess(List<ItemModel.Result> results);
    void onFailure(String message);
}
