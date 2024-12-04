package com.shiba1302.timkiemvieclam.entity;

public class demotest {
    private String ho;
    private String ten;

    public demotest(String ho, String ten) {
        this.ho = ho;
        this.ten = ten;
    }

    public demotest() {

    }

    public String getHo() {
        return ho;
    }

    public void setHo(String h0) {
        this.ho = h0;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "demotest [h0=" + ho + ", ten=" + ten + "]";
    }

}
