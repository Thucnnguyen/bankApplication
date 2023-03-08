package com.example.BankBalance.Ultils;

import java.math.BigInteger;
import java.security.SecureRandom;

public class createVerificationCode {
    private static SecureRandom random = new SecureRandom();
    public static String createVerificationCode(int length){

        return new BigInteger( length * 4,random).toString(32);
    }

//    public static void main(String[] args) {
//        System.out.println(createVerificationCode(6));
//    }
}
