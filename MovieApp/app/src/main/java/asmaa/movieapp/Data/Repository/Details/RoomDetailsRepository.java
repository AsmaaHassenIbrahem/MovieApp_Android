package asmaa.movieapp.Data.Repository.Details;

import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Listener.OnMovieResult;
import asmaa.movieapp.Singleton.AppDatabase;

/**
 * Created by asmaa on 05/18/2018.
 */

public class RoomDetailsRepository implements DetailsRepository{

    @Override
    public void findMovieById(int id, AppDatabase db,OnMovieResult onResult) {
        onResult.onSuccessIsFav(db.movieDao().findById(id));
    }

    @Override
    public void insertMovie(Result movie, AppDatabase db) {
        db.movieDao().insertAll(movie);

    }

    @Override
    public void deleteMovieById(Result movie, AppDatabase db) {
      db.movieDao().delete(movie);

    }
}
