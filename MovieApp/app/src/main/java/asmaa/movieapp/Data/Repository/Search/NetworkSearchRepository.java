package asmaa.movieapp.Data.Repository.Search;

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

public class NetworkSearchRepository implements SearchRepository{

    private ApiEndpointInterface mApi = App.getInstance().getApi();
    private ArrayList<Result>data;

    public  static int totalItems = 0;
    @Override
    public void getSearchData(String keyWord, int page, final OnResult onSearchResult) {
        Call<MoviesData> call;
        call = mApi.getSearchResult(APIUrls.API_KEY,keyWord,page);
        call.clone().enqueue(new Callback<MoviesData>() {
            @Override
            public void onResponse(Call<MoviesData> call, Response<MoviesData> response) {

                data = new ArrayList<>();
                if (response.body()!=null){
                    totalItems = response.body().getTotalPages();
                    for (int i = 0; i < response.body().getResults().size(); i++) {
                        data.add(response.body().getResults().get(i));
                    }
                    onSearchResult.onSuccess(data);
                }
            }

            @Override
            public void onFailure(Call<MoviesData> call, Throwable t) {

                Log.e("error: ",t.getMessage());
                onSearchResult.onFailure();
            }
        });

    }
}
