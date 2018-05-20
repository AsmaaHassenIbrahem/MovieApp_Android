package asmaa.movieapp.ViewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Listener.HomeListener;
import asmaa.movieapp.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by asmaa on 05/17/2018.
 */

public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
    private HomeListener homeListener;
    private View itemView;
    private TextView txtName;
    private TextView txtRate;
    private TextView txtDate;
    private CircleImageView img;
    private Result homeModel;

    public HomeViewHolder(View itemView ,HomeListener homeListener,Context context) {
        super(itemView);
        this.context=context;
        this.itemView= itemView;
        this.homeListener=homeListener;
        itemView.setOnClickListener(this);
        initializeViews();
    }
    private void initializeViews() {
        txtName = itemView.findViewById(R.id.tvname);
        txtRate = itemView.findViewById(R.id.tvRate);
        txtDate = itemView.findViewById(R.id.tvDate);
        img = itemView.findViewById(R.id.img);
    }

    public void setData(Result data){
        this.homeModel=data;
        txtName.setText(homeModel.getTitle());
        txtRate.setText(String.valueOf(homeModel.getVote_count()));
        txtDate.setText(homeModel.getRelease_date());
        if(data.getPoster_path() != null ) {
            Glide.with(context).load("http://image.tmdb.org/t/p/w185/"+data.getPoster_path()).centerCrop().placeholder(R.drawable.movie).into(img);
        }
    }

    @Override
    public void onClick(View view) {
        homeListener.onMovieClicked(homeModel);
    }
}
