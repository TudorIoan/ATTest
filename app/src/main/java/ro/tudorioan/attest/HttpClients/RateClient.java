package ro.tudorioan.attest.HttpClients;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import ro.tudorioan.attest.Models.Rate;

public interface RateClient {

    // TODO - check header issue
    @Headers({"Accept: application/json"})
    @GET("rates.json")
    Call<List<Rate>> getRates();
}
