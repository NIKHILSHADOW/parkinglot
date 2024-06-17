package com.example.thirdparty;

public class CreditCard {

    public Boolean payNow(Integer amount, Integer bill) {

        return amount >= bill;
    }
}
