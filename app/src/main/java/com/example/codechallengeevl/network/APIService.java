package com.example.codechallengeevl.network;

import com.example.codechallengeevl.model.BeersModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIService {
    @Headers("Content-Type: application/json")
    @GET("v2/beers")
    Call<List<BeersModel>> ConsultaCervezasPOST(@Query("page") int page);
}
