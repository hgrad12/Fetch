package com.example.fetch.model;

import androidx.annotation.NonNull;

public class Item implements Comparable<Item> {
    private int id;
    private int listId;
    private String name;

    public Item(){}

    public Item(int id, int listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getListId() {
        return listId;
    }

    public String getName() {
        if (name == null) return "NULL";
        return name;
    }

    public int getNameNumber() {
        String num = name.replace("Item", "").trim();
        return Integer.parseInt(num);
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: " + id + "\tListID: " + listId + "\tNAME: " + name;
    }

    @Override
    public int compareTo(Item o) {
        int listIdValue = Integer.compare(listId, o.getListId());

        if (listIdValue == 0)
            return Integer.compare(getNameNumber(), o.getNameNumber());

        return listIdValue;
    }
}
