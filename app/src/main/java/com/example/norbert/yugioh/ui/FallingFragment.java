package com.example.norbert.yugioh.ui;

import android.util.Log;
import android.view.View;

import com.example.norbert.yugioh.Client.models.Card;
import com.example.norbert.yugioh.Client.models.CardArray;
import com.example.norbert.yugioh.Client.models.CardList;
import com.example.norbert.yugioh.Client.models.YGOApiImpli;
import com.example.norbert.yugioh.adapters.FallingAdapter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FallingFragment extends BaseCardListFragment {

    private FallingAdapter mFallingAdapter;

    public static FallingFragment newInstance() {
        return new FallingFragment();
    }

    @Override
    public void setUpItems() {
        getActivity().setTitle("Falling Cards");
    }

    @Override
    public void loadCards() {
        YGOApiImpli.getInstance().getRisingAndFalling(new Callback<CardArray>() {
            @Override
            public void success(CardArray cardArray, Response response) {
                mProgress.setVisibility(View.INVISIBLE);

                CardList c = cardArray.getData();
                List<Card> cards = c.getFalling();

                mFallingAdapter = new FallingAdapter(getActivity(), cards);
                mCardList.setAdapter(mFallingAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("ERROR", error.getMessage());
            }
        });
    }
}
