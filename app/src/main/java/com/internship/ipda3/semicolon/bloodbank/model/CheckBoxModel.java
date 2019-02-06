package com.internship.ipda3.semicolon.bloodbank.model;

public class CheckBoxModel {
    private String data;
    private int pos;
    private boolean isChecked;

    public CheckBoxModel(String data, int pos, boolean isChecked) {
        this.data = data;
        this.pos = pos;
        this.isChecked = isChecked;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "CheckBoxModel{" +
                "data='" + data + '\'' +
                ", pos=" + pos +
                ", isChecked=" + isChecked +
                '}';
    }
}
