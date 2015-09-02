package com.example.norbert.yugioh.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.norbert.yugioh.Client.models.Card;
import com.example.norbert.yugioh.R;
import com.example.norbert.yugioh.ui.CardDetailActivity;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseCardAdapter extends RecyclerView.Adapter<BaseCardAdapter.ViewHolder> {

    protected DecimalFormat df = new DecimalFormat("#0.00");
    private List<Card> mCardList;
    private Context mContext;

    public BaseCardAdapter(Context context, List<Card> cards) {
        mContext = context;
        mCardList = cards;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.rcardName)
        TextView rcardName;
        @Bind(R.id.price)
        TextView price;
        @Bind(R.id.cardSet)
        TextView cardSet;
        @Bind(R.id.cardTag)
        TextView cardTag;
        @Bind(R.id.rarity)
        TextView rarity;

        public ViewHolder(View v) {
            super(v);

            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View view) {
            String cardName = mCardList.get(getAdapterPosition()).getName();

            Intent intent = new Intent(mContext, CardDetailActivity.class);
            intent.putExtra("CARD_NAME", cardName);
            mContext.startActivity(intent);
        }
    }

    @Override
    public BaseCardAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle_cost, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Card card = mCardList.get(position);
        viewHolder.cardTag.setText(card.getCard_number());
        viewHolder.cardSet.setText(card.getCard_set());
        viewHolder.rarity.setText(card.getRarity());
        viewHolder.price.setText(getCardPrice(card));
        viewHolder.rcardName.setText(card.getName());
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    abstract String getCardPrice(Card card);
}
