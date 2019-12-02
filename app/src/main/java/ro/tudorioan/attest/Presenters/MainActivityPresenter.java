package ro.tudorioan.attest.Presenters;

import android.accounts.Account;
import android.icu.util.Currency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ro.tudorioan.attest.MainActivity;
import ro.tudorioan.attest.Models.Rate;
import ro.tudorioan.attest.Models.Transaction;
import ro.tudorioan.attest.Providers.ProviderMng;
import ro.tudorioan.attest.Utils.Utils;

public class MainActivityPresenter {

    public MainActivity.TRANSACTION_DISPLAY_STATE mTransactionDisplayState = MainActivity.TRANSACTION_DISPLAY_STATE.SIMPLE;

    private View mView;
    private HashMap<String, List<Transaction>> mTransactionHash;
    private String mSelectedSku;
    private List<Rate> mRateList;


    public MainActivityPresenter(View v) {
        mView = v;
    }

    public void fetchTransactions() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Transaction> transactions = ProviderMng.mTransactionProvider.getTransactions();

                String error = null;
                if (transactions != null) {
                    mTransactionHash = new HashMap<>();
                    for (Transaction transaction : transactions) {
                        if (mTransactionHash.containsKey(transaction.getSku())) {
                            mTransactionHash.get(transaction.getSku()).add(transaction);
                        } else {
                            mTransactionHash.put(transaction.getSku(), new ArrayList<Transaction>());
                        }
                    }
                } else {
                    error = ProviderMng.mTransactionProvider.getError();
                }

                updateView(error);

            }
        }).start();
    }

    public void fetchRates() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Rate> rates = ProviderMng.mRateProvider.getRates();

                String error = null;
                if (rates != null) {
                    mRateList = rates;
                } else {
                    error = ProviderMng.mRateProvider.getError();
                    updateView(error);
                }
            }
        }).start();
    }

    public void selectSku(String sku) {
        mSelectedSku = sku;

        if (sku == null)
        {
            mTransactionDisplayState = MainActivity.TRANSACTION_DISPLAY_STATE.SIMPLE;
        } else {
            mTransactionDisplayState = MainActivity.TRANSACTION_DISPLAY_STATE.DETAIL;
        }
        updateView(null);
    }

    public String getTotalAmmountForSelectedSku() {

        Double totalAmmount = 0d;
        List<Transaction> transactions = mTransactionHash.get(mSelectedSku);

        // Add all transactions for selected sku
        for (Transaction trans : transactions) {
            Double ammount = 0d;

            if (trans.getCurrency().equals("EUR")) {
                ammount = Double.parseDouble(trans.getAmmount());
            } else {
                Double combinedRate = Utils.getTraversedRate(mRateList, trans.getCurrency(), "EUR");
                ammount = Double.parseDouble(trans.getAmmount()) * combinedRate;
            }

            totalAmmount += ammount;
        }

        // TODO - use locale and currency style format
        return String.format("%.2f", totalAmmount);
    }

    private void updateView(String error) {
        if (error != null) {
            mView.displayError(error);
        } else {
            switch (mTransactionDisplayState) {
                case SIMPLE:
                    List<String> skuList = new ArrayList<String>();
                    skuList.addAll(mTransactionHash.keySet());
                    mView.setTransactionData(skuList);
                    mView.refreshTransactionViews();
                    break;
                case DETAIL:
                    List<Transaction> transactionList = mTransactionHash.get(mSelectedSku);
                    mView.setTransactionData(transactionList);
                    mView.refreshTransactionViews();
                    break;
            }
        }
    }

    public interface View {
        void setTransactionData(List data);

        void refreshTransactionViews();

        void displayError(String error);
    }


}
