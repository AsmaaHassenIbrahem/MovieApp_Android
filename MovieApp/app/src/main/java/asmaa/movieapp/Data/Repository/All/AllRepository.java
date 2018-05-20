package asmaa.movieapp.Data.Repository.All;


/**
 * Created by asmaa on 05/18/2018.
 */


import asmaa.movieapp.Listener.OnResult;

/**
 * interface repository to write the method will be use in repository to handle all repositories
 */
public interface AllRepository {
    void getAllData(int page , OnResult onAllResult);
}


