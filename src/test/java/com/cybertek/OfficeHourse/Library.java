package com.cybertek.OfficeHourse;

public class Library {
    public static void sleep(double a) {
        try {
            Thread.sleep((long) a * 1000);
        } catch (InterruptedException e) {

        }
    }
}
