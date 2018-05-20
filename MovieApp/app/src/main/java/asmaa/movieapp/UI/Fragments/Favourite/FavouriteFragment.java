package asmaa.movieapp.UI.Fragments.Favourite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rey.material.widget.ProgressView;

import java.io.Serializable;
import java.util.ArrayList;

import asmaa.movieapp.Adapter.FavAdapter;
import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Injection.Injection;
import asmaa.movieapp.Listener.FavViewListener;
import asmaa.movieapp.Listener.HomeListener;
import asmaa.movieapp.R;
import asmaa.movieapp.Singleton.AppDatabase;
import asmaa.movieapp.UI.Activities.Details.DetailsActivity;

public class FavouriteFragment extends Fragment implements HomeListener ,FavViewListener {

    private RecyclerView recyclerView;

    private FavouritePresenter presenter;
    private FavAdapter favAdapter;
    private ArrayList<Result> dataResult = new ArrayList<>();
    private ProgressView progressView;
    public FavouriteFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        initView(view);
        return view;
    }

    private void initView(View v){
        recyclerView= v.findViewById(R.id.my_recycler_view);
        progressView=v.findViewById(R.id.pv_load);
        presenter = new FavouritePresenter(Injection.provideFavouriteRepository());
        presenter.setView(AppDatabase.getAppDatabase(getActivity()),this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressView.setVisibility(View.GONE);
    }


    @Override
    public void setData(ArrayList<Result> listData) {
        dataResult.clear();
        if (listData.size() > 0){
            dataResult.addAll(listData);
            favAdapter = new FavAdapter(listData,this);
            favAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(favAdapter);
        }

    }

    @Override
    public void errorMsg() {
    }
    @Override
    public void onMovieClicked(Result result) {
        Intent switchToDetails = new Intent(getActivity(),DetailsActivity.class);
        switchToDetails.putExtra(DetailsActivity.movie, (Serializable)  result);
        startActivity(switchToDetails );

    }
}
