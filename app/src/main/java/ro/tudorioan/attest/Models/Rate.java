package ro.tudorioan.attest.Models;

import com.google.gson.annotations.SerializedName;

public class Rate {

    @SerializedName("from")
    private String mFrom;

    @SerializedName("to")
    private String mTo;

    @SerializedName("rate")
    private String mRate;


    public String getFrom() {
        return mFrom;
    }

    public String getTo() {
        return mTo;
    }

    public String getRate() {
        return mRate;
    }
}
