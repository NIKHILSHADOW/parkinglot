package com.example.thirdparty;

public class OnlinePayment {

    public Boolean makePayment(Integer amount, Integer bill) {

        return amount >= bill;
    }
}
