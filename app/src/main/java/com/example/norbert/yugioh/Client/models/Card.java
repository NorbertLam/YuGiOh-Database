package com.example.norbert.yugioh.Client.models;

import java.io.Serializable;

public class Card implements Serializable {

    private String name;
    private String text;
    private String print_tag;
    private String rarity;
    private String card_type;
    private String type;
    private String family;
    private String atk;
    private String def;
    private String level;
    private String property;
    private String price;
    private String price_shift;
    private String card_number;
    private String card_set;

    public String getName() {
        return name;
    }

    public String getPrint_Tag() {
        return print_tag;
    }

    public String getRarity() {
        return rarity;
    }

    public String getText() {
        return text;
    }

    public String getCard_type() {
        return card_type;
    }

    public String getType() {
        if (type == null) {

        } else {
            return "/" + type;
        }
        return "";
    }

    public String getFamily() {
        if (family == null) {

        } else {
            return family;
        }
        return "N/A";
    }

    public String getAtk() {
        if (def == null) {

        } else {
            return atk;
        }
        return "N/A";
    }

    public String getDef() {
        if (def == null) {

        } else {
            return "/" + def;
        }
        return "";
    }

    public String getLevel() {
        if (level == null) {

        } else {
            return level;
        }
        return "N/A";
    }

    public String getProperty() {
        if (property == null) {

        } else {
            return property;
        }
        return "N/A";
    }

    public String getPrice() {
        return price;
    }

    public String getPrice_shift() {
        return price_shift;
    }

    public String getCard_number() {
        return card_number;
    }

    public String getCard_set() {
        return card_set;
    }
}
