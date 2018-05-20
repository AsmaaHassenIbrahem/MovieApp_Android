package asmaa.movieapp.UI.Fragments.Favourite;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Data.Repository.Favourite.FavouriteRepository;
import asmaa.movieapp.Listener.FavViewListener;
import asmaa.movieapp.Listener.OnRoomResult;
import asmaa.movieapp.Singleton.AppDatabase;

/**
 * Created by asmaa on 05/18/2018.
 */

public class FavouritePresenter {

    public static FavViewListener view;

    public static FavouriteRepository favouriteRepository;
    public FavouritePresenter(FavouriteRepository favouriteRepository){
        this.favouriteRepository=favouriteRepository;
    }

    public void setView(AppDatabase db , FavViewListener view) {
        this.view=view;
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private ArrayList<Result> data = new ArrayList<>();


        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            data.clear();
            favouriteRepository.getFavMovies( mDb,new OnRoomResult() {
                @Override
                public void onSuccess(List<Result> listData) {
                    if (listData!=null) {
                        data.addAll(listData);
                    }
                }


            });

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            view.showProgress();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.hideProgress();
            view.setData(data);
        }
    }
}
