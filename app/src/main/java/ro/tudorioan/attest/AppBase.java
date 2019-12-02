package ro.tudorioan.attest;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppBase extends MultiDexApplication {

    public static Retrofit mHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create());
        mHttpClient = builder.build();
    }

}
