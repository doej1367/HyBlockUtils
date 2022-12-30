package com.doej.hyblockutils.item;

public class SkyblockProfile {
    private int id;
    private String title;

    public SkyblockProfile(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
