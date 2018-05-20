package asmaa.movieapp.UI.Fragments.Search;

import java.util.ArrayList;

import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Data.Repository.Search.NetworkSearchRepository;
import asmaa.movieapp.Data.Repository.Search.SearchRepository;
import asmaa.movieapp.Listener.OnResult;
import asmaa.movieapp.Listener.ViewListener;

/**
 * Created by asmaa on 05/18/2018.
 */

public class SearchPresenter {

    private ArrayList<Result> searchData = new ArrayList<>();


    private SearchRepository searchRepository;
    ViewListener view;

    public SearchPresenter(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public void setView(ViewListener view) {
        this.view = view;
    }

    public void getSearchData(String keyWord, final int page) {
        view.showProgress();
        searchRepository.getSearchData(keyWord, page, new OnResult() {
            @Override
            public void onSuccess(ArrayList<Result> listData) {
                searchData.clear();
                searchData.addAll(listData);
                view.hideProgress();
                if (searchData.size() == 0) {
                    view.showNoDataAvailable();
                } else {
                    if (page < NetworkSearchRepository.totalItems) {
                        view.hideLoadingMoreData();
                        view.hideProgress();
                        view.setData(listData);
                    } else {
                        view.hideLoadingMoreData();
                        view.hideProgress();
                        view.setData(listData);
                    }
                }
            }

            @Override
            public void onFailure() {
                view.hideProgress();
                view.errorMsg();

            }
        });

    }
}
