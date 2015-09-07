package com.example.norbert.yugioh.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.norbert.yugioh.Client.models.YGOApiImpli;
import com.example.norbert.yugioh.R;
import com.example.norbert.yugioh.Utility.Network;
import com.example.norbert.yugioh.adapters.CardAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CardSearchFragment extends Fragment {

    @Bind(R.id.lvCards)
    ListView mCardList;
    private CardAdapter mCardAdapter;
    @Bind(R.id.editText)
    EditText mEditText;
    @Bind(R.id.progress)
    ProgressBar mProgress;

    public static CardSearchFragment newInstance() {
        return new CardSearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_search, container, false);

        bindViews(view);

        getActivity().setTitle("Search");

        mProgress.setVisibility(View.VISIBLE);

        if(Network.isNetworkConnected(getActivity()) == true) {
            loadCards();
        }
        else {
            Snackbar.make(view, "Cannot connect to network.", Snackbar.LENGTH_LONG).show();
        }

        setUpSearch();
        setUpOnClick();

        return view;
    }

    public void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    public void loadCards() {
        YGOApiImpli.getInstance().cardList(new Callback<List<String>>() {
            @Override
            public void success(List<String> cards, Response response) {
                mProgress.setVisibility(View.INVISIBLE);
                mCardAdapter = new CardAdapter(getActivity(), cards);
                mCardList.setAdapter(mCardAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("ERROR", error.getMessage());
            }
        });
    }

    public void setUpOnClick() {
        mCardList.setAdapter(mCardAdapter);
        mCardList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), CardDetailActivity.class);
                String cardName = (String) adapterView.getItemAtPosition(i);
                intent.putExtra("CARD_NAME", cardName);
                startActivity(intent);
            }
        });
    }

    public void setUpSearch() {
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCardAdapter.getFilter().filter(s);
            }
        });
    }
}
