package com.example.norbert.yugioh.adapters;

import android.content.Context;

import com.example.norbert.yugioh.Client.models.Card;

import java.util.List;

public class RisingAdapter extends BaseCardAdapter {

    public RisingAdapter(Context context, List<Card> cards) {
        super(context, cards);
    }

    @Override
    protected String getCardPrice(Card card) {
        return "Price: $" + String.valueOf(df.format(Double.parseDouble(card.getPrice()))) +
                "     Shift: +" + String.valueOf(df.format(Double.parseDouble(card.getPrice_shift
                ()) * 100)) + "%";
    }
}
