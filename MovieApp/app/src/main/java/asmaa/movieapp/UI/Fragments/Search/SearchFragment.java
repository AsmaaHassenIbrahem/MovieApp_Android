package asmaa.movieapp.UI.Fragments.Search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.rey.material.widget.ProgressView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import asmaa.movieapp.Adapter.HomeAdapter;
import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Data.Repository.All.NetworkAllRepository;
import asmaa.movieapp.Data.Repository.Search.NetworkSearchRepository;
import asmaa.movieapp.Injection.Injection;
import asmaa.movieapp.Listener.HomeListener;
import asmaa.movieapp.Listener.ViewListener;
import asmaa.movieapp.R;
import asmaa.movieapp.UI.Activities.Details.DetailsActivity;
import asmaa.movieapp.Utilities.ActionUtils;
import asmaa.movieapp.Utilities.EndlessRecyclerOnScrollListener;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SearchFragment extends Fragment implements ViewListener, HomeListener {

    private RecyclerView recyclerView;
    private int page=1;
    private boolean isLoading = false;
    private boolean moreDataAvailable = true;
    private Boolean stillLoading=false;

    private SearchPresenter presenter;
    private HomeAdapter homeAdapter;
    private ArrayList<Result> dataResult = new ArrayList<>();
    private ProgressView progressView;
    private EditText txtSearch;
    private String mLastSearchQuery = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initView(view);
        return view;
    }

    private void initView(View v){
        recyclerView= v.findViewById(R.id.my_recycler_view);
        progressView=v.findViewById(R.id.pv_load);
        txtSearch =v.findViewById(R.id.txtSearch);
        presenter = new SearchPresenter(Injection.provideSearchRepository());
        presenter.setView(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        homeAdapter = new HomeAdapter(gridLayoutManager,this);
        homeAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(homeAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(gridLayoutManager/*recyclerView.getLayoutManager()*/) {
            @Override
            public void onLoadMore() {
                if (!isLoading && moreDataAvailable ) {
                    isLoading = true;
                    loadMoreSearchData();
                }
            }
        });

        RxTextView.textChangeEvents(txtSearch)
                .onBackpressureLatest()
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        dataResult.clear();
                        homeAdapter.clearData();
                        // dataResult.clear();
                        String newSearchQuery = textViewTextChangeEvent.text() + "";
                        if( !mLastSearchQuery.equals(newSearchQuery) &&  !newSearchQuery.equals(null)
                                && !newSearchQuery.equals("search") ) {
                            // isSearch=true;
                            mLastSearchQuery = newSearchQuery;
                            if (ActionUtils.isInternetConnected(getActivity())) {
                                presenter.getSearchData(mLastSearchQuery, page);
                            }else {
                                ActionUtils.showToast(getActivity(), "connection error");

                            }
                        }
                    }
                });

    }


    private void loadMoreSearchData() {

        homeAdapter.setLoading(true);
        Log.d("pageeLodMore" , page + "");

        presenter.getSearchData(mLastSearchQuery,page);
    }

    @Override
    public void showProgress() {
        isLoading = true;
        //   homeAdapter.setLoading(false);
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        isLoading = false;
        // homeAdapter.setLoading(false);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingMoreData() {
        homeAdapter.setLoading(true);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingMoreData() {
        homeAdapter.setLoading(false);
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataAvailable() {

    }

    @Override
    public void setData(ArrayList<Result> listData) {
        isLoading=false;
        if (listData.size() > 0){
            dataResult.addAll(listData);
            if (page == NetworkSearchRepository.totalItems){
                moreDataAvailable =false;
            }else {
                page++;
            }
        }

        homeAdapter.setData(dataResult);
    }

    @Override
    public void errorMsg() {
        // ActionUtils.showToast(this, getResources().getString(R.string.api_error));
    }




    @Override
    public void onMovieClicked(Result result) {

        Intent switchToDetails = new Intent(getActivity(),DetailsActivity.class);
        switchToDetails.putExtra(DetailsActivity.movie, (Serializable)  result);
        startActivity(switchToDetails );
    }

}
