package com.example.norbert.yugioh.Client;

import com.example.norbert.yugioh.Client.models.CardArray;
import com.example.norbert.yugioh.Client.models.CardDataResponse;
import com.example.norbert.yugioh.Client.models.CardList;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Norbert on 5/22/2015.
 */

public class YGOClient {

    public static final String API_URL = "http://www.yugiohprices.com/api";

    public interface YGO {
        @GET("/get_card_prices/{card_name}")
        void getCardPrices(@Path("card_name") String cardName, Callback<CardList> cb);

        @GET("/price_for_print_tag/{print_tag}")
        void priceForPrintTag(@Path("print_tag") String printTag, Callback<CardList> cb);

        @GET("/price_history/{print_tag}?rarity={rarity}")
        void priceHistory(@Path("rarity") String rarity, Callback<CardList> cb);

        @GET("/set_data/{set_name}")
        void setData(@Path("set_name") String setName, Callback<CardList> cb);

        @GET("/rising_and_falling")
        void getRisingAndFalling(Callback<CardArray> cb);

        @GET("/top_100_cards")
        void getTop100(Callback<CardList> cb);

        @GET("/card_names")
        void cardList(Callback<List<String>> cb);

        @GET("/card_data/{card_name}")
        void getCardData(@Path("card_name") String cardName, Callback<CardDataResponse> cb);

        @GET("/card_versions/{card_name}")
        void cardVersions(@Path("card_name") String cardName, Callback<CardList> cb);

        @GET("/card_support/{card_name}")
        void cardSupport(@Path("card_name") String cardName, Callback<CardList> cb);

        @GET("/card_iamge/{card_name}")
        void getCardImage(@Path("card_name") String cardName, Callback<CardList> cb);

    }
}
