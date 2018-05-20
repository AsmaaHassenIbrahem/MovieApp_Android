package asmaa.movieapp.Data.Repository.Search;

import asmaa.movieapp.Listener.OnResult;

/**
 * Created by asmaa on 05/18/2018.
 */

public interface SearchRepository {
    void getSearchData(String keyWord, int page , OnResult onSearchResult);

}
