package asmaa.movieapp.Data.Repository.Favourite;

import asmaa.movieapp.Listener.OnRoomResult;
import asmaa.movieapp.Singleton.AppDatabase;

/**
 * Created by asmaa on 05/18/2018.
 */

public class RoomFavouriteRepository implements  FavouriteRepository{
    @Override
    public void getFavMovies( AppDatabase db,OnRoomResult onResult) {
        onResult.onSuccess(db.movieDao().getAll());
    }


}
