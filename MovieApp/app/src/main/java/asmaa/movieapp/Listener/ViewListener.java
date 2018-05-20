package asmaa.movieapp.Listener;

import java.util.ArrayList;

import asmaa.movieapp.Data.Model.Result;

/**
 * Created by asmaa on 05/18/2018.
 */

public interface ViewListener {
    void showProgress();
    void hideProgress();
    void showLoadingMoreData();
    void hideLoadingMoreData();
    void showNoDataAvailable();
    void setData(ArrayList<Result> listData);
    void errorMsg();

}
