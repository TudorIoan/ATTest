package ro.tudorioan.attest.Models;

import com.google.gson.annotations.SerializedName;

public class Transaction {


    @SerializedName("sku")
    private String mSku;

    @SerializedName("amount")
    private String mAmmount;

    @SerializedName("currency")
    private String mCurrency;


    public String getSku() {
        return mSku;
    }

    public String getAmmount() {
        return mAmmount;
    }

    public String getCurrency() {
        return mCurrency;
    }
}
