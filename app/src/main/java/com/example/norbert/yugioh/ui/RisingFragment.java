package com.example.norbert.yugioh.ui;

import android.util.Log;
import android.view.View;

import com.example.norbert.yugioh.Client.models.Card;
import com.example.norbert.yugioh.Client.models.CardArray;
import com.example.norbert.yugioh.Client.models.CardList;
import com.example.norbert.yugioh.Client.models.YGOApiImpli;
import com.example.norbert.yugioh.adapters.RisingAdapter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RisingFragment extends BaseCardListFragment {

    private RisingAdapter mRisingAdapter;

    public static RisingFragment newInstance() {
        return new RisingFragment();
    }

    @Override
    public void setUpItems() {
        getActivity().setTitle("Rising Cards");
    }

    @Override
    public void loadCards() {
        YGOApiImpli.getInstance().getRisingAndFalling(new Callback<CardArray>() {
            @Override
            public void success(CardArray cardArray, Response response) {
                mProgress.setVisibility(View.INVISIBLE);

                CardList c = cardArray.getData();
                List<Card> cards = c.getRising();

                mRisingAdapter = new RisingAdapter(getActivity(), cards);
                mCardList.setAdapter(mRisingAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("ERROR", error.getMessage());
            }
        });
    }
}
