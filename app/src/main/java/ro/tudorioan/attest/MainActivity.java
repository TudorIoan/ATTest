package ro.tudorioan.attest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.tudorioan.attest.Presenters.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    public enum TRANSACTION_DISPLAY_STATE {
        SIMPLE,
        DETAIL
    }

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.layoutTop)
    LinearLayout mTopLayout;

    @BindView(R.id.backBtn)
    Button mBackButton;

    @BindView(R.id.selectedSku)
    TextView mSelectedSku;

    @BindView(R.id.totalAmmount)
    TextView mTotalAmmount;

    MainActivityPresenter mPresenter;

    TransactionAdapter mTransactionAdapter;
    TransactionSimpleAdapter mSimpleTransactionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainActivityPresenter(this);

        initTransactionRecycler();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.fetchRates();
        mPresenter.fetchTransactions();
    }

    private void initTransactionRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSimpleTransactionAdapter = new TransactionSimpleAdapter(mPresenter);
        mTransactionAdapter = new TransactionAdapter();
    }

    @OnClick(R.id.backBtn)
    public void backBtnClick() {
        mPresenter.selectSku(null);
    }

    @Override
    public void setTransactionData(final List data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (mPresenter.mTransactionDisplayState) {
                    case SIMPLE:
                        mSimpleTransactionAdapter.setData(data);
                        mRecyclerView.setAdapter(mSimpleTransactionAdapter);
                        break;
                    case DETAIL:
                        mTransactionAdapter.setData(data);
                        mRecyclerView.setAdapter(mTransactionAdapter);
                        break;
                }
            }
        });
    }

    @Override
    public void refreshTransactionViews() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (mPresenter.mTransactionDisplayState) {
                    case SIMPLE:
                        mTopLayout.setVisibility(View.GONE);
                        mSimpleTransactionAdapter.notifyDataSetChanged();
                        break;
                    case DETAIL:
                        mTopLayout.setVisibility(View.VISIBLE);
                        mTotalAmmount.setText(mPresenter.getTotalAmmountForSelectedSku());
                        mTransactionAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });
    }

    @Override
    public void displayError(String error) {
        // Display Error message
    }
}
