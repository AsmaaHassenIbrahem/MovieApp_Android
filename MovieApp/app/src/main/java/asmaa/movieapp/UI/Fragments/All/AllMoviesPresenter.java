package asmaa.movieapp.UI.Fragments.All;

import android.util.Log;

import java.util.ArrayList;

import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Data.Repository.All.AllRepository;
import asmaa.movieapp.Data.Repository.All.NetworkAllRepository;
import asmaa.movieapp.Listener.OnResult;
import asmaa.movieapp.Listener.ViewListener;
import asmaa.movieapp.Utilities.ActionUtils;

/**
 * Created by asmaa on 05/18/2018.
 */

public class AllMoviesPresenter {

    private ArrayList<Result> data = new ArrayList<>();

    private AllRepository homeRepository;
    ViewListener view;

    public AllMoviesPresenter(AllRepository homeRepository){
        this.homeRepository =homeRepository;
    }

    public void setView(int page,ViewListener view) {
        this.view =view;


        Log.i("here ","here");
        if (data.size()== 0 ) {
            view.showProgress();
            getHomeData(page);
            Log.i("here ","here2");

        } else {
            view.hideProgress();
            view.setData(data);
            Log.i("here ","here3");

        }
    }

    public void getHomeData(final int page){
        data.clear();
        view.showProgress();
        homeRepository.getAllData(page,new OnResult() {
            @Override
            public void onSuccess(ArrayList<Result> listData) {
                data.addAll(listData);
                view.hideProgress();
                if (data.size() ==0){
                    view.showNoDataAvailable();
                }else{

                    if (page < NetworkAllRepository.totalPage){
                        view.hideLoadingMoreData();
                        view.setData(listData);
                    }else {
                        view.hideLoadingMoreData();
                        view.setData(listData);
                    }
                }
            }

            @Override
            public void onFailure() {

                view.errorMsg();

            }
        });

    }


}
