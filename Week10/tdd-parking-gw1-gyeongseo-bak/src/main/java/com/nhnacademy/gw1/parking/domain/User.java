package com.nhnacademy.gw1.parking.domain;

public class User {

    private String userName;
    private long money;
    private Payco payco;

    public User(String userName, long money) {
        this.userName = userName;
        this.money = money;
    }

    public User(String userName, long money, Payco payco) {
        this.userName = userName;
        this.money = money;
        this.payco = payco;
    }

    public long getMoney() {
        return money;
    }

    public Payco getPayco() {
        return payco;
    }

    public void subtractMoney(long fee) {
        this.money -= fee;
    }
}
