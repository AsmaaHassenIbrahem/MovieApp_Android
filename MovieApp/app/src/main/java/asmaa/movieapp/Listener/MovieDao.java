package asmaa.movieapp.Listener;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

/**
 * Created by asmaa on 05/18/2018.
 */

import asmaa.movieapp.Data.Model.Result;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
        List<Result> getAll();

    @Query("SELECT * FROM movie where id LIKE  :id")
   Result findById(int id);

    @Query("SELECT COUNT(*) from movie")
    int countMovies();

    @Insert
    void insertAll(Result... movie);

    @Delete
    void delete(Result movie);
}
