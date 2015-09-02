package com.example.norbert.yugioh.Client.models;

import java.util.List;

public class CardList {
    private List<Card> data;
    private List<Card> rising;
    private List<Card> falling;

    public List<Card> getData() {
        return data;
    }

    public List<Card> getRising() {
        return rising;
    }

    public List<Card> getFalling() {
        return falling;
    }
}
