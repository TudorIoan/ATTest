package ro.tudorioan.attest.HttpClients;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import ro.tudorioan.attest.Models.Transaction;

public interface TransactionClient {

    // TODO - check header issue
    @Headers({"Accept: application/json"})
    @GET("transactions.json")
    Call<List<Transaction>> getTransactions();

}
