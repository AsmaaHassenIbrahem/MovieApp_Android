package asmaa.movieapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Listener.HomeListener;
import asmaa.movieapp.R;
import asmaa.movieapp.ViewHolder.HomeViewHolder;

/**
 * Created by asmaa on 05/18/2018.
 */

public class FavAdapter  extends RecyclerView.Adapter<HomeViewHolder>{
    private ArrayList<Result> data;
      private HomeListener homeListener;

    public FavAdapter (ArrayList<Result>  data , HomeListener homeListener){
        this.data=data;
          this.homeListener = homeListener;
    }
    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_home, parent, false);
        return new HomeViewHolder(view,homeListener,parent.getContext());
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        Log.i("log:=====","log");
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        Log.i("sizee: ",""+data.size());
        return data.size();

    }
}
