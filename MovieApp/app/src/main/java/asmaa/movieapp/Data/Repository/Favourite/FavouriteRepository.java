package asmaa.movieapp.Data.Repository.Favourite;


import asmaa.movieapp.Listener.OnRoomResult;
import asmaa.movieapp.Singleton.AppDatabase;

/**
 * Created by asmaa on 05/18/2018.
 */

public interface FavouriteRepository {
    void getFavMovies(AppDatabase db,OnRoomResult onResult);

}
