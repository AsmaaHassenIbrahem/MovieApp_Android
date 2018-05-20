package asmaa.movieapp.UI.Activities.Details;

import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Data.Repository.Details.DetailsRepository;
import asmaa.movieapp.Listener.OnMovieResult;
import asmaa.movieapp.Listener.ViewDetails;
import asmaa.movieapp.Singleton.AppDatabase;

/**
 * Created by asmaa on 05/18/2018.
 */

public class DetailsPresenter {

    private Result data = new Result();

   private ViewDetails view;

    private DetailsRepository detailsRepository;
    public DetailsPresenter(DetailsRepository detailsRepository){
        this.detailsRepository=detailsRepository;
    }

    public void setView(Result data ,AppDatabase db, ViewDetails view) {
        this.view=view;
        setDetailsData(db,data);
    }

    public void setDetailsData(AppDatabase db, Result data) {
        this.data = data;
        if (data!=null){
            view.setId(data.getId());
            view.setPoster(data.getBackdrop_path());
            view.setVote(data.getVote_average());
            view.setTitle(data.getTitle());
            view.setOverview(data.getOverview());
            findById(data.getId(),db);
        }
    }

    public void insertMovie(Result movie, AppDatabase db){
        detailsRepository.insertMovie(movie,db);
        view.showDone("insert is Done");
    }

    public void findById(int id , AppDatabase db){
        detailsRepository.findMovieById(id, db, new OnMovieResult() {
            @Override
            public void onSuccessIsFav(Result isFav) {
                if (isFav !=null){
                    view.isFav(true);
                }else {
                    view.isFav(false);
                }
            }
        });
    }

    public void deleteMovie(Result movie,AppDatabase db){
        detailsRepository.deleteMovieById(movie, db);
                view.showDone("delete movie");
    }

}
