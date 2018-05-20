package asmaa.movieapp.Data.Repository.All;

import android.util.Log;

import java.util.ArrayList;

import asmaa.movieapp.API.APIUrls;
import asmaa.movieapp.API.ApiEndpointInterface;
import asmaa.movieapp.Data.Model.MoviesData;
import asmaa.movieapp.Data.Model.Result;

import asmaa.movieapp.Listener.OnResult;
import asmaa.movieapp.Singleton.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by asmaa on 05/18/2018.
 */

/**
 * repository class to request api and get data
 */
public class NetworkAllRepository implements AllRepository {

    private ApiEndpointInterface mApi = App.getInstance().getApi();
    private ArrayList<Result>data;

    public  static int totalPage = 1000;

    @Override
    public void getAllData(int page, final OnResult onAllResult) {
        Call<MoviesData> call;
        call = mApi.getMovies(APIUrls.API_KEY,page);
        call.clone().enqueue(new Callback<MoviesData>() {
            @Override
            public void onResponse(Call<MoviesData> call, Response<MoviesData> response) {

                data = new ArrayList<>();
                if (response.body()!=null){
                    totalPage = response.body().getTotalPages();
                        for (int i = 0; i < response.body().getResults().size(); i++) {
                            data.add(response.body().getResults().get(i));
                        }
                        onAllResult.onSuccess(data);
                    }
            }

            @Override
            public void onFailure(Call<MoviesData> call, Throwable t) {

                Log.e("error: ",t.getMessage());
                onAllResult.onFailure();
            }
        });



    }

}
