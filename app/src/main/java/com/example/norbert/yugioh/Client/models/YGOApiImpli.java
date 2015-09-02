package com.example.norbert.yugioh.Client.models;

import com.example.norbert.yugioh.Client.YGOClient;

import retrofit.RestAdapter;

public class YGOApiImpli {

    private static YGOClient.YGO api;

    protected YGOApiImpli() {
    }

    public static YGOClient.YGO getInstance() {
        if (api == null) {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(YGOClient.API_URL)
                    .build();
            api = restAdapter.create(YGOClient.YGO.class);
        }
        return api;
    }
}
