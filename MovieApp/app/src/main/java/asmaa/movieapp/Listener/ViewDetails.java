package asmaa.movieapp.Listener;


/**
 * Created by asmaa on 05/18/2018.
 */

public interface ViewDetails {
    void showProgress();
    void hideProgress();
    void setTitle(String title);
    void setOverview(String overview);
    void setPoster(String img);
    void setVote(float vote);
    void setId(int id);
    void isFav(boolean isFav);
    void showDone(String msg);
}

