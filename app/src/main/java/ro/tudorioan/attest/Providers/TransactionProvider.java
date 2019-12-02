package ro.tudorioan.attest.Providers;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ro.tudorioan.attest.AppBase;
import ro.tudorioan.attest.HttpClients.TransactionClient;
import ro.tudorioan.attest.Models.Transaction;

public class TransactionProvider {

    private static TransactionProvider mInstance;
    private TransactionClient mTransactionClient;

    private List<Transaction> mTransactionList;
    private String mError;

    public static TransactionProvider getInstance() {
        if (mInstance == null) {
            mInstance = new TransactionProvider();
        }

        return mInstance;
    }

    private TransactionProvider() {
        mTransactionClient = AppBase.mHttpClient.create(TransactionClient.class);
    }

    public List<Transaction> getTransactions() {
        mError = null;
        Call<List<Transaction>> call = mTransactionClient.getTransactions();
        Response<List<Transaction>> responseTransactionList = null;

        try {
            responseTransactionList = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            mError = e.getLocalizedMessage();
        }

        if (responseTransactionList != null && responseTransactionList.isSuccessful()) {
            return responseTransactionList.body();
        } else {
            return null;
        }
    }

    public String getError() {
        return mError;
    }

}
