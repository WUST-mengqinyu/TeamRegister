package com.webutil.badcw.utils;

public class giveBack {
    private boolean val = false;
    private String info = "empty";

    public void makeText(String x, boolean temp) {
        val = temp;
        info = x;
    }

    public void makeText(String x) {
        val = false;
        info = x;
    }

    public String getInfo() { return info; }

    public boolean getres() { return val; }
}