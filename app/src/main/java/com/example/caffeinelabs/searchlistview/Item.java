package com.example.caffeinelabs.searchlistview;

/**
 * Created by caffeineLabs on 31/08/16.
 */
public class Item {

    String text_item;

    //constructor
    public Item(String text_item) {
        this.text_item = text_item;
    }

    //setters and getters
    public String getText_item() {
        return text_item;
    }

    public void setText_item(String text_item) {
        this.text_item = text_item;
    }
}
