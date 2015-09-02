package com.example.norbert.yugioh.ui;

import android.util.Log;
import android.view.View;

import com.example.norbert.yugioh.Client.models.Card;
import com.example.norbert.yugioh.Client.models.CardList;
import com.example.norbert.yugioh.Client.models.YGOApiImpli;
import com.example.norbert.yugioh.adapters.ExpensiveAdapter;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ExpensiveFragment extends BaseCardListFragment {

    private ExpensiveAdapter mExpensiveAdapter;

    public static ExpensiveFragment newInstance() {
        return new ExpensiveFragment();
    }

    @Override
    public void setUpItems() {
        getActivity().setTitle("Top Expensive Cards");
    }

    @Override
    public void loadCards() {
        YGOApiImpli.getInstance().getTop100(new Callback<CardList>() {
            @Override
            public void success(CardList cardList, Response response) {
                mProgress.setVisibility(View.INVISIBLE);
                List<Card> card = cardList.getData();

                mExpensiveAdapter = new ExpensiveAdapter(getActivity(), card);
                mCardList.setAdapter(mExpensiveAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("ERROR", error.getMessage());
            }
        });
    }
}
