package asmaa.movieapp.Adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Listener.HomeListener;
import asmaa.movieapp.R;
import asmaa.movieapp.Utilities.EndlessAdapter;
import asmaa.movieapp.ViewHolder.HomeViewHolder;

/**
 * Created by asmaa on 05/18/2018.
 */

public class HomeAdapter extends EndlessAdapter<Result> {

    private HomeListener homeListener;
    public HomeAdapter(LinearLayoutManager linearLayoutManager,HomeListener homeListener) {
        super(linearLayoutManager);
        this.homeListener=homeListener;
        setLoadingView(R.layout.item_loader);
    }

    @Override
    protected RecyclerView.ViewHolder createMyViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_home, parent, false);
        return new HomeViewHolder(view,homeListener,parent.getContext());
    }

    @Override
    protected void bindMyViewController(RecyclerView.ViewHolder holder, int position) {
        ((HomeViewHolder)holder).setData(getDataItem(position));
    }
}
