package ro.tudorioan.attest.Providers;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ro.tudorioan.attest.AppBase;
import ro.tudorioan.attest.HttpClients.RateClient;
import ro.tudorioan.attest.Models.Rate;

public class RateProvider {

    private static RateProvider mInstance;
    private RateClient mRateClient;

    private List<Rate> mRateList;
    private String mError;

    public static RateProvider getInstance() {
        if (mInstance == null) {
            mInstance = new RateProvider();
        }

        return mInstance;
    }

    private RateProvider() {
        mRateClient = AppBase.mHttpClient.create(RateClient.class);
    }

    public List<Rate> getRates() {
        Call<List<Rate>> call = mRateClient.getRates();
        Response<List<Rate>> responseRateList = null;

        try {
            responseRateList = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
            mError = e.getLocalizedMessage();
        }

        if (responseRateList != null && responseRateList.isSuccessful()) {
            mRateList = responseRateList.body();
            return mRateList;
        } else {
            return null;
        }
    }

    public String getError() {
        return mError;
    }
}