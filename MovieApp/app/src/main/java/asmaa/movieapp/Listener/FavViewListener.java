package asmaa.movieapp.Listener;

import java.util.ArrayList;
import asmaa.movieapp.Data.Model.Result;

/**
 * Created by asmaa on 05/18/2018.
 */

public interface FavViewListener {

    void showProgress();
    void hideProgress();
    void setData(ArrayList<Result> listData);
    void errorMsg();
}
