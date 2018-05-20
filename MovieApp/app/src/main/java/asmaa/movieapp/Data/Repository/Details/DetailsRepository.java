package asmaa.movieapp.Data.Repository.Details;

import asmaa.movieapp.Data.Model.Result;
import asmaa.movieapp.Listener.OnMovieResult;
import asmaa.movieapp.Singleton.AppDatabase;

/**
 * Created by asmaa on 05/18/2018.
 */

public interface DetailsRepository {

    void findMovieById(int id , AppDatabase db,OnMovieResult onResult);
    void insertMovie(Result movie, AppDatabase db);
    void deleteMovieById(Result movie , AppDatabase db );
}
