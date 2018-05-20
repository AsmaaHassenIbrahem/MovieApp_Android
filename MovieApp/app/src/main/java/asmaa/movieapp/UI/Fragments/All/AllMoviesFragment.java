package asmaa.movieapp.UI.Fragments.All;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rey.material.widget.ProgressView;

import java.io.Serializable;
import java.util.ArrayList;

import asmaa.movieapp.Adapter.HomeAdapter;
import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Data.Repository.All.NetworkAllRepository;
import asmaa.movieapp.Injection.Injection;
import asmaa.movieapp.Listener.HomeListener;
import asmaa.movieapp.Listener.ViewListener;
import asmaa.movieapp.R;
import asmaa.movieapp.UI.Activities.Details.DetailsActivity;
import asmaa.movieapp.Utilities.ActionUtils;
import asmaa.movieapp.Utilities.EndlessRecyclerOnScrollListener;

public class AllMoviesFragment extends Fragment implements ViewListener ,HomeListener {

    private RecyclerView recyclerView;
    private int page=1;
    private boolean isLoading = false;
    private boolean moreDataAvailable = true;

    private AllMoviesPresenter presenter;
    private HomeAdapter homeAdapter;
    private ArrayList<Result> dataResult = new ArrayList<>();
    private ProgressView progressView;

    public AllMoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_movies, container, false);
        initView(view);
        return view;
    }

    private void initView(View v){

        recyclerView= v.findViewById(R.id.my_recycler_view);
        progressView=v.findViewById(R.id.pv_load);
        presenter = new AllMoviesPresenter(Injection.provideAllRepository());
        if (ActionUtils.isInternetConnected(getActivity())) {
            presenter.setView(page, this);
        }else{
            ActionUtils.showToast(getActivity(), "Connection Error");
        }
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
                        loadMoreData();
                }

            }
        });

    }

    private void loadMoreData() {

        homeAdapter.setLoading(true);
        presenter.getHomeData(page);
    }


    @Override
    public void showProgress() {
        isLoading = true;
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        isLoading = false;
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
            if (page == NetworkAllRepository.totalPage){
                moreDataAvailable =false;
            }else {
                page++;
            }
        }

        homeAdapter.setData(dataResult);
    }

    @Override
    public void errorMsg() {
        ActionUtils.showToast(getActivity(), "API error");
    }

    @Override
    public void onMovieClicked(Result result) {
        Intent switchToDetails = new Intent(getActivity(),DetailsActivity.class);
        switchToDetails.putExtra(DetailsActivity.movie, (Serializable)  result);
        startActivity(switchToDetails );

    }
}
