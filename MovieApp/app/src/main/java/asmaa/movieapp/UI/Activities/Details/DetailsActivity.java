package asmaa.movieapp.UI.Activities.Details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Injection.Injection;
import asmaa.movieapp.Listener.ViewDetails;
import asmaa.movieapp.R;
import asmaa.movieapp.Singleton.AppDatabase;
import asmaa.movieapp.Utilities.ActionUtils;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener,ViewDetails {

    public final static String movie ="movie";
    private DetailsPresenter presenter;

    private ImageView img,imgFav;
    private TextView tvName, tvVote, tvId, tvOverView;
    private boolean isFavo= false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initView();
    }

    private void initView(){
        img=findViewById(R.id.img);
        imgFav=findViewById(R.id.imgFav);
        imgFav.setOnClickListener(this);
        tvName =findViewById(R.id.tvname);
        tvId =findViewById(R.id.tvId);
        tvVote=findViewById(R.id.tvVote);
        tvOverView=findViewById(R.id.tvOverView);
        presenter = new DetailsPresenter(Injection.provideDetailsRepository());
        presenter.setView((Result) getIntent().getSerializableExtra(movie),AppDatabase.getAppDatabase(this),this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgFav:
                if (isFavo) {
                    isFavo=false;
                    presenter.deleteMovie((Result) getIntent().getSerializableExtra(movie), AppDatabase.getAppDatabase(this));
                    imgFav.setImageResource(getResources().getIdentifier("@drawable/dis_like", "drawable", getPackageName()));
                   presenter.setView((Result) getIntent().getSerializableExtra(movie), AppDatabase.getAppDatabase(this),this);
                }
                else {
                    isFavo=true;
                    presenter.insertMovie((Result) getIntent().getSerializableExtra(movie), AppDatabase.getAppDatabase(this));
                    imgFav.setImageResource(getResources().getIdentifier("@drawable/like", "drawable", getPackageName()));
                    presenter.setView((Result) getIntent().getSerializableExtra(movie), AppDatabase.getAppDatabase(this),this);
                }
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setTitle(String title) {
        tvName.setText(title);

    }

    @Override
    public void setOverview(String overview) {
        tvOverView.setText(overview);
    }

    @Override
    public void setPoster(String imgs) {
        if(imgs != null ) {
            Glide.with(this).load("http://image.tmdb.org/t/p/w185/"+imgs).centerCrop().placeholder(R.drawable.movie).into(img);
        }
    }

    @Override
    public void setVote(float vote) {
        tvVote.setText(String.valueOf(vote));
    }


    @Override
    public void setId(int id) {
        tvId.setText(String.valueOf(id));

    }

    @Override
    public void isFav(boolean isFav) {
        if (isFav){
            isFavo=true;
            imgFav.setImageResource(getResources().getIdentifier("@drawable/like", "drawable", getPackageName()));

        }else {
            isFavo=false;
            imgFav.setImageResource(getResources().getIdentifier("@drawable/dis_like", "drawable", getPackageName()));
        }

    }

    @Override
    public void showDone(String msg) {
        ActionUtils.showToast(this,msg);
    }
}
