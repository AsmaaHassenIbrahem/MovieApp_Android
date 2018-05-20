package asmaa.movieapp.Injection;

/**
 * Created by asmaa on 05/17/2018.
 */


import asmaa.movieapp.Data.Repository.All.AllRepository;
import asmaa.movieapp.Data.Repository.All.NetworkAllRepository;
import asmaa.movieapp.Data.Repository.Details.DetailsRepository;
import asmaa.movieapp.Data.Repository.Details.RoomDetailsRepository;
import asmaa.movieapp.Data.Repository.Favourite.FavouriteRepository;
import asmaa.movieapp.Data.Repository.Favourite.RoomFavouriteRepository;
import asmaa.movieapp.Data.Repository.Search.NetworkSearchRepository;
import asmaa.movieapp.Data.Repository.Search.SearchRepository;

/**
 *  injection class to manage my contexts to start specific repository to get data from api request
 *  or local connection Database like sqlLite to  continue the sequence
 *  "start repository >> get data >> presenter >> set data in view"
 */
public class Injection {

    public static AllRepository provideAllRepository(){
        return new NetworkAllRepository();
    }

    public static SearchRepository provideSearchRepository(){
        return new NetworkSearchRepository();
    }

    public static FavouriteRepository provideFavouriteRepository(){
        return new RoomFavouriteRepository();
    }

    public static DetailsRepository provideDetailsRepository(){
        return new RoomDetailsRepository();
    }

}
