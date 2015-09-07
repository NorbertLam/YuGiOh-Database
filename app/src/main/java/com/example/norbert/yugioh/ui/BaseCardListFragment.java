package com.example.norbert.yugioh.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.norbert.yugioh.R;
import com.example.norbert.yugioh.Utility.Network;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseCardListFragment extends Fragment {

    @Bind(R.id.progress)
    ProgressBar mProgress;
    @Bind(R.id.rCards)
    RecyclerView mCardList;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycle_list, container, false);

        bindViews(view);
        setUpItems();
        if (Network.isNetworkConnected(getActivity()) == true) {
            loadCards();
        } else {
            Snackbar.make(view, "Cannot connect to network.", Snackbar.LENGTH_LONG).show();
        }
        setUpDetails();

        return view;
    }

    public void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    public void setUpDetails() {
        mProgress.setVisibility(View.VISIBLE);

        mCardList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());

        mLayoutManager = new LinearLayoutManager(getActivity());
        mCardList.setLayoutManager(mLayoutManager);
    }

    abstract void setUpItems();

    abstract void loadCards();
}
