package asmaa.movieapp.Listener;

import java.util.ArrayList;

import asmaa.movieapp.Data.Model.Result;


/**
 * Created by asmaa on 05/18/2018.
 */

public interface OnResult {
    void onSuccess(ArrayList<Result> listData);
    void onFailure();
}
