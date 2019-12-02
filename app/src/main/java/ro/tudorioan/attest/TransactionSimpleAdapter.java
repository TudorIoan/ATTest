package ro.tudorioan.attest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ro.tudorioan.attest.Presenters.MainActivityPresenter;

public class TransactionSimpleAdapter extends RecyclerView.Adapter<TransactionSimpleAdapter.ViewHolder> {

    List<String> mSkuList;
    private MainActivityPresenter mPresenter;

    public TransactionSimpleAdapter(MainActivityPresenter presenter) {
        mPresenter = presenter;
    }

    public void setData(List<String> skuList){
        this.mSkuList = skuList;
    }

    @NonNull
    @Override
    public TransactionSimpleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_simple_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionSimpleAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.mSkuTextView.setText(mSkuList.get(i));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.selectSku(mSkuList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSkuList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.sku_text_view) public TextView mSkuTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
